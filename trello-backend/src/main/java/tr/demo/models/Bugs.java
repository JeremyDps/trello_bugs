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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Bugs {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name, description;
    private Bugs_priority priority;
    private Bugs_progress progress;
    private LocalDate creation_date;

    @ManyToMany
    @JoinTable(
            name = "bugs_developers",
            joinColumns = @JoinColumn(name = "bug_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developers> developers;
    @OneToMany(mappedBy="commentaire")
    private List<Commentaire>Commentaires;
}