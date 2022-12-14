package tn.espritSpring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.espritSpring.DAO.entites.Contrat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IContratRepository extends CrudRepository<Contrat, Integer> {



    @Query(value = "SELECT count(*) FROM contart c WHERE (c.date_debut_contrat > :startDate and  c.date_fin_contrat < :endDate) and c.archive=0 ",
            nativeQuery = true)
    Integer nbContratsValides(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    List<Contrat> findContratsByArchiveIsFalseAndDateDebutContratBetween(Date startDate , Date endDate);


    @Query("select c from Contrat c where c.dateFinContrat between ?1 and ?2")
    List<Contrat> findByDateFinContratBetween(LocalDate dateFinContratStart, LocalDate dateFinContratEnd);


}
