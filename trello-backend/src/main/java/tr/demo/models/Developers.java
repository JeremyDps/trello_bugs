package fr.demo.models;

import javax.persistence.*;

import fr.demo.models.Bugs;

import java.util.*;

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

public class Developers {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nom, prenom, avatar;

    @ManyToMany(mappedBy="developers")
    private List<Bugs> bugs;
}