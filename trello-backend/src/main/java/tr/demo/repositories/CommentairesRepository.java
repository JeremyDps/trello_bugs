package tr.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.demo.models.Commentaire;

public interface CommentairesRepository extends JpaRepository<Commentaire, Integer> {
    Optional<Commentaire> findById(Integer id);
}