package tr.demo.route;

import java.util.List;

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

import tr.demo.repositories.BugsRepository;
import tr.demo.repositories.CommentairesRepository;
import tr.demo.repositories.DevelopersRepository;

@RestController
public class BugsRoute {

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

    @GetMapping("bugs")
    public List<Bugs> getAllBugs() {
        return bugsRepository.findAll();
    }

    @PostMapping("bugs")
    public String createBugs(@Validated @RequestBody CreateBugs bug) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = simpleDateFormat.format(date);
        /*bugsRepository.save(
                Bugs
                .builder()
                .name(bug.getName())
                .description(bug.getDescription())
                .priority(bug.getPriority())
                .progress(bug.getProgress())
                .creation_date(date)
                //.developers(bug.getDevelopers())
                .build()
        );
        developersRepository.save(bugsRepository.getDevelopers());*/
        return "post ok";
    }

    /*@PutMapping("bugs/{id}")
    public String updateBugs(@PathVariable("id") Integer id, @Validated @RequestBody CreateBugs bug) throws ResourceNotFoundException {
        if(!bugsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bug not found with id = " + id);
        }


    }*/

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

    @DeleteMapping("developers/{id}")
    public ResponseEntity<?> deleteDevelopers(@PathVariable("id") Integer id) {
        if(!developersRepository.existsById(id)) {
            throw new ResourceNotFoundException("Developer not found with id = " + id);
        }

        return developersRepository.findById(id)
                .map(dev -> {
                    developersRepository.delete(dev);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Le développeur n'a pas été trouvé avec l'id = " + id));
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

    /*@PostMapping("commentaire")
    public Developers createDevelopers(@Validated @RequestBody CreateDevelopers dev) {
        return developersRepository.save(
                Developers
                        .builder()
                        .nom(dev.getNom())
                        .prenom(dev.getPrenom())
                        .avatar(dev.getAvatar())
                        .build()
        );
    }*/

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
}