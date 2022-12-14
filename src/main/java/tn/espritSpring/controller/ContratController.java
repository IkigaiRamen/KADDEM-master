package tn.espritSpring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.espritSpring.services.IContratService;
import tn.espritSpring.DAO.entites.Contrat;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/Contrat")
@RequiredArgsConstructor
public class ContratController {

   public final IContratService iContratService ;

    @GetMapping("/getAllContrat")
    public List<Contrat> getAllContrat() {return iContratService.getAllContrat();}

    @PostMapping("addContrat")
    public Contrat addContrat(@RequestBody Contrat c) {return iContratService.addContrat(c);}

    @PutMapping("/updateContrat")
    public Contrat updateContrat(@RequestBody Contrat c) {
        return iContratService.updateContrat(c);
    }

    @DeleteMapping("/deleteContrat/{idContart}")
    public void deleteContrat(@PathVariable("idContart") int idContart) {iContratService.deleteContrat(idContart);}

    @GetMapping("/getbyidContrat/{idContart}")
    public Contrat getContratById(@PathVariable("idContart") int idContart) {
        return iContratService.getContratById(idContart);
    }

    @GetMapping("/getnbcon/{dDeb}/{dFin}")
    @ResponseBody
    public Integer getContart(@PathVariable("dDeb") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dDeb, @PathVariable("dFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dFin){
        return iContratService.nbContratsValides( dDeb, dFin);
    }


  /*  @GetMapping("/affaire/{startDate}/{endDate}")
    public  void calculChiffreAffaire(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        iContratService.getChiffreAffaireEntreDeuxDate(startDate,endDate);
    }
*/


}
