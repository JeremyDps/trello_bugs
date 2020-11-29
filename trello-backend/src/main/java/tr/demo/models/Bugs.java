package tr.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.demo.enums.Bugs_priority;
import tr.demo.enums.Bugs_progress;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Bugs {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private Bugs_priority priority;
    @Enumerated(EnumType.STRING)
    private Bugs_progress progress;
    
    @Temporal(TemporalType.DATE)
    private Date creation_date;

    @ManyToOne
    private Developers developers;
    
    @OneToMany(mappedBy="bug")
    private List<Commentaire> commentaires;

}