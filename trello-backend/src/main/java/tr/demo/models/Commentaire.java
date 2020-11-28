package fr.demo.models;
import fr.demo.models.Developers;
import tr.demo.enums.Bugs_priority;
import tr.demo.enums.Bugs_progress;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// JPA
@Entity // Indique que la classe sera utilisée pour des requêtes
public class Commentaire {
    @Id
    private int id;
    private String texte;
    @OneToMany
       git  private List<Developers>developers;
    }