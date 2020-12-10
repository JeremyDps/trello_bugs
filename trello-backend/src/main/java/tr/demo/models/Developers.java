package tr.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Developers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String avatar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "developers")
	@JsonIgnoreProperties({"developers", "commentaire"})
	private List<Bugs> bugs;
		
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "developers")
	@JsonIgnoreProperties({"bug", "developers"})
	private List<Commentaire> commentaire;
}