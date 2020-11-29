package tr.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.demo.models.Bugs;

public interface BugsRepository extends JpaRepository<Bugs, Integer> {
    Optional<Bugs> findById(Integer id);
}