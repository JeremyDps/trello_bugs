package tr.demo.route;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.text.SimpleDateFormat;
import tr.demo.enums.Bugs_priority;
import tr.demo.enums.Bugs_progress;

import tr.demo.exception.ResourceNotFoundException;
import tr.demo.models.CreateBugs;
import tr.demo.models.Bugs;
import tr.demo.models.CreateDevelopers;
import tr.demo.models.Developers;
import tr.demo.models.CreateCommentaire;
import tr.demo.models.Commentaire;
import tr.demo.payload.UploadFileResponse;
import tr.demo.service.BugsService;

import tr.demo.repositories.BugsRepository;
import tr.demo.repositories.CommentairesRepository;
import tr.demo.repositories.DevelopersRepository;

@RestController
public class BugsRoute {

    private static final Logger logger = LoggerFactory.getLogger(BugsRoute.class);

    @Autowired
    private BugsService bugsService;

    @Autowired
    BugsRepository bugsRepository;

    @Autowired
    CommentairesRepository commentairesRepository;

    @Autowired
    DevelopersRepository developersRepository;

    //Routes sur les bugs
    @GetMapping("bugs/{id}")
    public Bugs getBugs(@PathVariable("id") Integer id) {
        return bugsRepository.findById(id).orElse(null);
    }

    @GetMapping("bugs/search")
    public List<Bugs> findByName(@RequestParam (required = true) String name) {
        return bugsRepository.findAll().stream()
                .filter(b -> b.getName().equals(name))
                .collect(Collectors.toList());
    }

    @GetMapping("bugs")
    public List<Bugs> getAllBugs() {
        return bugsRepository.findAll();
    }

    @PostMapping("bugs")
    public Bugs createBugs(@Validated @RequestBody CreateBugs bug) {
        Date date = new Date();
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = simpleDateFormat.format(date);*/
        return bugsRepository.save(
                Bugs
                .builder()
                .name(bug.getName())
                .description(bug.getDescription())
                .priority(bug.getPriority())
                .progress(bug.getProgress())
                .creation_date(date)
                .developers(bug.getDevelopers())
                .build()
        );

    }

    @PutMapping("bugs/{id}")
    public ResponseEntity<?> updateBugs(@PathVariable("id") Integer id, @Validated @RequestBody CreateBugs bug) throws ResourceNotFoundException {
        if(!bugsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bug not found with id = " + id);
        }

        Bugs existBug = bugsRepository.findById(id).orElse(null);

        existBug.setName(bug.getName());
        existBug.setDescription(bug.getDescription());
        existBug.setPriority(bug.getPriority());
        existBug.setProgress(bug.getProgress());
        existBug.setDevelopers(bug.getDevelopers());

        bugsRepository.save(existBug);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("developers/{id}")
    public ResponseEntity<?> deleteDevelopers(@PathVariable("id") Integer id) {
        if(!developersRepository.existsById(id)) {
            throw new ResourceNotFoundException("Developer not found with id = " + id);
        }

        //List<Bugs> tab = new ArrayList<>();

        Developers existDev = developersRepository.findById(id).orElse(null);
        /*existDev.setBugs(tab);
        developersRepository.save(existDev);*/

        for(Bugs bugs: existDev.getBugs()) {
            bugs.setDevelopers(null);
        }

        return developersRepository.findById(id)
                .map(dev -> {
                    developersRepository.delete(dev);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Le développeur n'a pas été trouvé avec l'id = " + id));

    }

    @DeleteMapping("bugs/{id}")
    public ResponseEntity<?> deleteBugs(@PathVariable("id") Integer id) {
        if(!bugsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bug not found with id = " + id);
        }

        return bugsRepository.findById(id)
                .map(bugs -> {
                    bugsRepository.delete(bugs);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Le bug n'a pas été trouvé avec l'id = " + id));
    }


    //Routes sur les Developpeurs
    @GetMapping("developers/{id}")
    public Developers getDevelopers(@PathVariable("id") Integer id) {
        return developersRepository.findById(id).orElse(null);
    }

    @GetMapping("developers")
    public List<Developers> getAllDevelopers() {
        return developersRepository.findAll();
    }

    @PostMapping("developers")
    public Developers createDevelopers(@Validated @RequestBody CreateDevelopers dev) {
        return developersRepository.save(
                Developers
                .builder()
                .nom(dev.getNom())
                .prenom(dev.getPrenom())
                .avatar(dev.getAvatar())
                .build()
        );
    }



    //Routes sur les Commentaires
    @GetMapping("commentaires/{id}")
    public Commentaire getCommentaire(@PathVariable("id") Integer id) {
        return commentairesRepository.findById(id).orElse(null);
    }

    @GetMapping("commentaires")
    public List<Commentaire> getAllCommentaires() {
        return commentairesRepository.findAll();
    }

    @PostMapping("commentaires/{idBug}")
    public Commentaire createDevelopers(@PathVariable("idBug") Integer idBug, @Validated @RequestBody CreateCommentaire commentaire) {
        if(!bugsRepository.existsById(idBug)) {
            throw new ResourceNotFoundException("Bug not found with id = " + idBug);
        }

        //Bugs existBug = bugsRepository.findById(idBug).orElse(null);
        return commentairesRepository.save(
            Commentaire
            .builder()
            .texte(commentaire.getTexte())
            .bug(commentaire.getBug())
            .developers(commentaire.getDevelopers())
            .build()
        );
    }

    @DeleteMapping("commentaires/{id}")
    public ResponseEntity<?> deleteCommentaires(@PathVariable("id") Integer id) {
        if(!commentairesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with id = " + id);
        }

        return commentairesRepository.findById(id)
                .map(c -> {
                    commentairesRepository.delete(c);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Le commentaire n'a pas été trouvé avec l'id = " + id));
    }

    //images
    @PostMapping("/developers/{id}/uploadFile")
    public String uploadFile(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) {
        String fileName = bugsService.storeFile(file);

        if(!developersRepository.existsById(id)) {
            throw new ResourceNotFoundException("Developer not found with id = " + id);
        }

        Developers existDev = developersRepository.findById(id).orElse(null);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/" + id + "/")
                .path(fileName)
                .toUriString();

        UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

        existDev.setAvatar(uploadFileResponse.getFileDownloadUri());

        developersRepository.save(existDev);

        return "avatar updated : id = " + id + ", avatar link = " + existDev.getAvatar();
    }

    @GetMapping("/downloadFile/{id}/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Integer id, @PathVariable("fileName") String fileName, HttpServletRequest request) {
        if(!developersRepository.existsById(id)) {
            throw new ResourceNotFoundException("Developer not found with id = " + id);
        }

        Developers existDev = developersRepository.findById(id).orElse(null);

        if(!existDev.getAvatar().equals("http://localhost:8080/downloadFile/" + id + "/" + fileName)) {
            throw new ResourceNotFoundException("Avatar not found with avatar = " + existDev.getAvatar());
        }

        //Load file as Resource
        Resource resource = bugsService.loadFileAsResource(fileName);

        //try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type");
        }

        //Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}