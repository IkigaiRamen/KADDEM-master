package tn.espritSpring.DAO.entites;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DetailEquipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salle;
	private String thematique;

	@OneToOne
	private Equipe equipe;
}



