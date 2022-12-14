package tn.espritSpring.DAO.entites;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Equipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEquipe;
	private String nomEquipe;
	@Enumerated(EnumType.STRING)
	private Niveau niveau;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Etudiant> etudiants;

	@OneToOne(mappedBy="equipe")
	private DetailEquipe detailEquipe;
}
