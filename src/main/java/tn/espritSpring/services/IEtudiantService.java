package tn.espritSpring.services;

import tn.espritSpring.DAO.entites.Etudiant;

import java.util.List;

public interface IEtudiantService {


    void assignContratToEtudiant (Integer idEtudiant ,Integer idContart  );

  public  Etudiant addAndAssignEtudiantToEquipeAndContract(Integer idEtudiant, Integer idContart, Integer idEquipe);

   public List<Etudiant> getAllEtudiant() ;
   public Etudiant addEtudiant(Etudiant etudiant);

   public Etudiant updateEtudiant(Etudiant etudiant);

    void deleteEtudiant(Integer idEtudiant);

    Etudiant retrieveEtudiant(Integer idEtudiant);

   public Etudiant getEtudiantById(Integer idEtudiant) ;


   List<Etudiant> retriveEtudiantByDepartementName (String nomDepart);

   void assignEtudiantToEquipe(Integer idEtudiant, Integer idEquipe);

    public String generecarteetudpdf(Integer idEtudiant);

 void getlistetudiantExcel();

}
