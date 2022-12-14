package tn.espritSpring.DAO.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDepart;
	private String nomDepart;





	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departement")
	private List<Etudiant> etudiants;


}