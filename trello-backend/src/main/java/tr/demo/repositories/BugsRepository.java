package tr.demo.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.JpaRepository;

import tr.demo.models.Bugs;

public interface BugsRepository extends JpaRepository<Bugs, Integer> {
    Optional<Bugs> findById(Integer id);

    /*@Query("select * from bugs where and priority = :priority")
    Optional<Bugs> findByPriority(@RequestParam("priority") String priority);*/
}