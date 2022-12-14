package tn.espritSpring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.espritSpring.DAO.entites.Etudiant;
import tn.espritSpring.services.IEtudiantService;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/Etudiant")
public class EtudiantController {

    public final IEtudiantService iEtudiantService;

    @GetMapping("/getAllEtudaints")
    public List<Etudiant> getAllDepartement() {
        return iEtudiantService.getAllEtudiant();
    }


    @PostMapping("/addEtudaint")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return iEtudiantService.addEtudiant(e);
    }


    //Affectation Equipe au etudiant

    @PutMapping("/assignEtudiantEquipe/{idEtudiant}/{idEquipe}")
    @ResponseBody
    public void assignetu(@PathVariable("idEtudiant") Integer idEtudiant , @PathVariable("idEquipe") Integer idEquipe)
    {
        iEtudiantService.assignEtudiantToEquipe(idEtudiant,idEquipe);
    }

    //Affectation Etudiant Contrat
    @PutMapping("/updateEtutiant/{idEtudiant}/{idContart}")
    public void updateEtudiant(@PathVariable ("idEtudiant") Integer idEtudiant ,@PathVariable ("idContart") Integer idContart )
    { iEtudiantService.assignContratToEtudiant(idEtudiant,idContart);}

    //Affectation Etudiant Contrat Equipe
    @PutMapping("/assignEtudiant/{idEtudiant}/{idContart}/{idEquipe}")
    public  Etudiant addAndAssignEtudiantToEquipeAndContract
    (@PathVariable("idEtudiant") Integer idEtudiant,@PathVariable("idContart") Integer idContart, @PathVariable("idEquipe") Integer idEquipe){
        return iEtudiantService.addAndAssignEtudiantToEquipeAndContract(idEtudiant,idContart,idEquipe);
    }


    @DeleteMapping("/delete/{idEtudiant}")
    public void deleteEtudiant(@PathVariable("idEtudiant") int idEtudiant) {
        iEtudiantService.deleteEtudiant(idEtudiant);
    }

    @GetMapping("/getbyid/{idEtudiant}")
    public Etudiant getEtudiantById(@PathVariable("idEtudiant") int idEtudiant) {
        return iEtudiantService.getEtudiantById(idEtudiant);
    }


    @GetMapping("/retrieve-etudiant/{idUniv}")
    @ResponseBody
    public Etudiant retrieveEtudiant(@PathVariable("idUniv")Integer idContart){
        return iEtudiantService.retrieveEtudiant(idContart);
    }


    @GetMapping("/getByDep/{name}")
    public List<Etudiant> getByname(@PathVariable("name") String name){
        return iEtudiantService.retriveEtudiantByDepartementName(name);
    }


    @GetMapping("/carteetudiant/{idEtudiant}")

    public String generecarteetudpdf (@PathVariable("idEtudiant") Integer idEtudiant){
        return iEtudiantService.generecarteetudpdf(idEtudiant) ;
    }

    @GetMapping({"/listetudiantexcel"})
    public void getlistetudiantExcel() {
        this.iEtudiantService.getlistetudiantExcel();
    }


}

