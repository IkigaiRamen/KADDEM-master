package tn.espritSpring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.espritSpring.DAO.entites.Etudiant;
import tn.espritSpring.DAO.entites.Option;

import java.util.List;

public interface IEtudiantRepository extends CrudRepository<Etudiant, Integer> {

    @Query("select e from Etudiant e where e.opt = :option")
    List<Etudiant> retriveEtudiantByOption(@Param("option") Option op);


   @Query("select e from Etudiant e where e.departement.nomDepart =: nomDepart")
   List<Etudiant> retriveEtudiantByDepartement (@Param("nomDepart") String nomDepart);



   @Query("select e from Etudiant e inner join e.departement ee where ee.nomDepart = :name")
    List<Etudiant> retriveEtudiantByDepartementName (@Param("name") String nomDepartement);

}
