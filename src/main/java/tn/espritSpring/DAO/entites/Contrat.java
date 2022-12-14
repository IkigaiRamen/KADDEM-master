package tn.espritSpring.DAO.entites;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Contrat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContrat;
	@Temporal(TemporalType.DATE)
	private Date dateDebutContrat;
	@Temporal(TemporalType.DATE)
	private Date dateFinContrat;
	@Enumerated(EnumType.STRING)
	private Specialite specialite  ;
	private Boolean archive ;
	@ManyToOne
	Etudiant etudiant;
}

	
	
	

