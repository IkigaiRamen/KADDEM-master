package tn.espritSpring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.espritSpring.DAO.entites.Contrat;

import tn.espritSpring.repositories.IContratRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor


 public class ContratImpl implements IContratService{


    @Autowired
    private final IContratRepository contartRepository ;

    @Override
    public List<Contrat> getAllContrat() {
        return (List<Contrat>) contartRepository.findAll();
    }
    @Override
    public Contrat addContrat(Contrat c) {
        return contartRepository.save(c);
    }
    @Override
    public Contrat updateContrat(Contrat c) {
        return contartRepository.save(c);
    }
    @Override
    public void deleteContrat(Integer idContart) {
        contartRepository.deleteById(idContart);
    }

    @Override
    public Contrat getContratById(Integer idContart) {
        return contartRepository.findById(idContart).orElse( null);
    }

    @Override
    public Integer nbContratsValides(Date startDate, Date endDate) {
        return contartRepository.nbContratsValides(startDate,endDate);
    }

   /* @Override
    public String getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {

        List<Contrat> lc =contartRepository.findContratsByArchiveIsFalseAndDateDebutContratBetween(startDate,endDate);

        float chiffre=0;
        float chiffreIA=0;
        float chiffreRESAUX=0;
        float chiffreCLOUD=0;
        float chiffreSECURITE=0;
        for (Contrat c : lc){

            if(c.getSpecialite().toString().equals("IA")){
                chiffreIA = chiffreIA + c.getMontnatContrat();
                System.out.println("******** Ia ="+chiffreIA);
            }
            if(c.getSpecialite().toString().equals("CLOUD")){
                chiffreCLOUD = chiffreCLOUD + c.getMontnatContrat();
                System.out.println("******** Cloud ="+chiffreCLOUD);
            }
            if(c.getSpecialite().toString().equals("SECURITE")){
                chiffreSECURITE = chiffreSECURITE + c.getMontnatContrat();
                System.out.println("******** Securite ="+chiffreSECURITE);
            }
            if(c.getSpecialite().toString().equals("RESEAUX")){
                chiffreRESAUX = chiffreRESAUX + c.getMontnatContrat();
                System.out.println("******** Réseau ="+chiffreRESAUX);
            }

            chiffre += c.getMontnatContrat();

        }

        String ch =
                "Pour un contrat dont la spécialité est IA: "+chiffreIA+"Dt/mois\n" +
                "Pour un contrat dont la spécialité est RESEAUX: "+chiffreRESAUX+"Dt/mois\n" +
                "Pour un contrat dont la spécialité est CLOUD: "+chiffreCLOUD+"Dt/mois\n" +
                "Pour un contrat dont la spécialité est SECURITE: "+chiffreSECURITE+"Dt/mois\n";
        System.out.println(ch);
        return ch;
    }*/





    @Scheduled(cron = "*/1000 * * * * *")
    void bonjour(){
        log.info("hello");
    }


    @Scheduled(cron ="0/15 * * * * *")
    public String retrieveStatusContrat(){
        Calendar cal = Calendar.getInstance();
        LocalDate today = LocalDate.now();
        cal.add(Calendar.DAY_OF_MONTH, 15);

        String res=contartRepository.findByDateFinContratBetween(today,LocalDate.now().plusDays(15)).toString();

        System.out.println("scheduled job runned !");
        return res;
    }




}
