package tr.demo.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    /*@PostMapping("bugs")
    public Bugs createBugs(@Validated @RequestBody CreateBugs bug) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return BugsRepository.save(
                Bugs
                .builder()
                .name(bug.getName())
                .description(bug.getDescription())
                .priority(bug.getPriority())
                .progress(bug.getProgress())
                .creation_date(format.format(date))
                .developers_id(bug.getDevelopers_id())
                .build()
        );
    }*/

    /*@DeleteMapping("bugs/{id}")
    public ResponseEntity<?> createBugs(@PathVariable("id") Integer id) {
        if(!BugsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bug not found with id = " + id);
        }

        return bugsRepository.findById(id)
                .map(bugs -> {
                    bugsRepository.delete(bugs);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> ResourceNotFoundException("Le bug n'a pas été trouvé avec l'id = " + id));
    }*/
}