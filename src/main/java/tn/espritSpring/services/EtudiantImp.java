package tn.espritSpring.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.sf.jasperreports.engine.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tn.espritSpring.DAO.entites.*;
import tn.espritSpring.repositories.IContratRepository;
import tn.espritSpring.repositories.IEquipeRepository;
import tn.espritSpring.repositories.IEtudiantRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class EtudiantImp implements IEtudiantService{


    private final IEtudiantRepository etudiantRepository ;
    private final IEquipeRepository equipeRepository ;
    private final IContratRepository contartRepository ;

    @Autowired
    private final Environment environment ;

    @Override
    public void assignContratToEtudiant(Integer idEtudiant , Integer idContrat) {
        Contrat contrat =contartRepository.findById(idContrat).orElse(null);
        Etudiant etudiant =getEtudiantById(idEtudiant);
    //    etudiant.setContrat(contart);
        etudiantRepository.save(etudiant);
    }

    @Override
    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Integer idEtudiant, Integer idContrat, Integer idEquipe) {
        //1. Sauvgarder l'objet etudiant et le récupérer en mémoire via l'affectation

        Contrat contrat = contartRepository.findById(idContrat).orElse(null);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        etudiant.setContrats((List<Contrat>) contrat);
        etudiant = etudiantRepository.save(etudiant);

        //Il faut ajouter une condution sur la collection equipes

        Set<Etudiant> listE = (Set<Etudiant>) equipe.getEtudiants();

        listE.add(etudiant);
        equipe.setEtudiants((List<Etudiant>) listE);
        equipeRepository.save(equipe);

        return etudiant ;
    }


    public String generecarteetudpdf(Integer idEtudiant){

        try {

            // Get your data source
            Connection conn=null;
            String url = environment.getProperty("spring.datasource.url");
            String user = environment.getProperty("spring.datasource.username");
            String password = environment.getProperty("spring.datasource.password");
            conn = DriverManager.getConnection(url,user,password);

            String reportPath = "C:\\Carte";

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager
                    .compileReport(new ClassPathResource("carte_etudiant.jrxml")
                            .getInputStream());




            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("id_etudiant", idEtudiant);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    conn);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\carte.pdf");

            return "Report successfully generated @path= " + reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error--> check the console log";
        }

        }



    public void getlistetudiantExcel() {
        try {
            LocalDate now = LocalDate.now();
            File myFile = new File("C://Users/ziedm/etudiants_" + now.toString() + ".xlsx");
            FileOutputStream os = new FileOutputStream(myFile);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Listes Etudiants");
            Row row1 = sheet.createRow(0);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16.0);
            style.setFont(font);
            this.createCell(row1, 0, "ID", style);
            this.createCell(row1, 1, "Nom", style);
            this.createCell(row1, 2, "Prénom", style);
            this.createCell(row1, 3, "Date Naissance", style);
            this.createCell(row1, 4, "Genre", style);
            AtomicInteger rowCount = new AtomicInteger(1);
            CellStyle style2 = workbook.createCellStyle();
            XSSFFont font2 = workbook.createFont();
            font2.setFontHeight(14.0);
            style2.setFont(font2);
            this.etudiantRepository.findAll().forEach((etudiant) -> {
                Row row = sheet.createRow(rowCount.getAndIncrement());
                int columnCount = 0;
                this.createCell(row, columnCount++, etudiant.getIdEtudiant(), style);
                this.createCell(row, columnCount++, etudiant.getNomE(), style);
                this.createCell(row, columnCount++, etudiant.getPrenomE(), style);
            });
            workbook.write(os);
        } catch (FileNotFoundException var12) {
            throw new RuntimeException(var12);
        } catch (IOException var13) {
            throw new RuntimeException(var13);
        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((double)(Integer)value);
        } else if (value instanceof Long) {
            cell.setCellValue((double)(Long)value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean)value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue((LocalDate)value);
        } else {
            cell.setCellValue((String)value);
        }

        cell.setCellStyle(style);
    }


   @Override
   public void assignEtudiantToEquipe(Integer idEtudiant, Integer idEquipe){

        Equipe equipe=equipeRepository.findById(idEquipe).orElse(null);
        Etudiant etudiant=etudiantRepository.findById(idEtudiant).orElse(null);
        etudiant.getEquipes().add(equipe);
        etudiantRepository.save(etudiant);

    }



    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        Etudiant e = etudiantRepository.findById(idEtudiant).orElse(null);
        return  e;
    }


    @Override
    public List<Etudiant> getAllEtudiant() {
        return (List<Etudiant>) etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiant(Integer idEtudiant) { etudiantRepository.deleteById(idEtudiant);}

    @Override
    public Etudiant getEtudiantById(Integer idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse( null);
    }

  @Override
    public List<Etudiant> retriveEtudiantByDepartementName(String nomDepart) {
        return etudiantRepository.retriveEtudiantByDepartementName(nomDepart);
    }
}
