package tr.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@Entity
// JPA
// Indique que la classe sera utilisée pour des requêtes
public class Commentaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String texte;
	
	@ManyToOne
    private Bugs bug;

    @ManyToOne
    private Developers developers;
}