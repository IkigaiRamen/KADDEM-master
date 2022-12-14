package tn.espritSpring.services;

import tn.espritSpring.DAO.entites.Contrat;

import java.util.Date;
import java.util.List;

public interface IContratService {



   public List<Contrat> getAllContrat() ;
  public Contrat addContrat(Contrat c);
  public Contrat updateContrat (Contrat c );
    void deleteContrat(Integer idContart);
  public Contrat getContratById(Integer idContart) ;

  public Integer nbContratsValides(Date startDate, Date endDate);

 // public String getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate);


}
