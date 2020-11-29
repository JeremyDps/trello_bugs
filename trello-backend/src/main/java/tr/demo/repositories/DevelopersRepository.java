package tr.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.demo.models.Developers;

public interface DevelopersRepository extends JpaRepository<Developers, Integer> {
    Optional<Developers> findById(Integer id);
}