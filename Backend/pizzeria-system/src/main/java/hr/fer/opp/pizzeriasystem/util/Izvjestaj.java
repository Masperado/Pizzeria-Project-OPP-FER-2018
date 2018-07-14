package hr.fer.opp.pizzeriasystem.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


public class Izvjestaj {

    Date pocetniDatum;
    Date zavrsniDatum;
    Integer totalniBrojNaruceniPizza;
    Double najvecaNarudzba;
    Double najmanjaNaruzba;
    Double totalnoNovcaZaradeno;
    Integer brojKorisnikaKojiSuNarucivali;
    Integer brojNarudbi;

    public Izvjestaj(Date pocetniDatum, Date zavrsniDatum, Integer totalniBrojNaruceniPizza,
                     Double najvecaNarudzba, Double najmanjaNaruzba, Double totalnoNovcaZaradeno,
                     Integer brojKorisnikaKojiSuNarucivali, Integer brojNarudbi) {

        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.totalniBrojNaruceniPizza = totalniBrojNaruceniPizza;
        this.najvecaNarudzba = najvecaNarudzba;
        this.najmanjaNaruzba = najmanjaNaruzba;
        this.totalnoNovcaZaradeno = totalnoNovcaZaradeno;
        this.brojKorisnikaKojiSuNarucivali = brojKorisnikaKojiSuNarucivali;
        this.brojNarudbi = brojNarudbi;
    }



    public Document getPdf(String pathName) {

        String fileText="";

        fileText += "\n";
        fileText += "\n";
        fileText += "Izvjestaj za period: " + pocetniDatum.toString() + " - " + zavrsniDatum.toString()+"\n";
        fileText += "\n";
        fileText += "\n";
        fileText += "Totalni broj naručeni pizza " + totalniBrojNaruceniPizza + ".\n";
        fileText += "\n";
        fileText += "\n";
        fileText += "Najveca narudžba u kunama: " + najvecaNarudzba + ", a najmanja narudžba u kunama je: " + najmanjaNaruzba + ". Broj narudzbi je: " + brojNarudbi + "\n";
        fileText += "\n";
        fileText += "\n";
        fileText += "Broj korisnika koji su naručili pizzu je " + brojKorisnikaKojiSuNarucivali + ".\n";
        fileText += "\n";
        fileText += "\n";
        fileText += "Totalno je zarađeno " + totalnoNovcaZaradeno + " kuna.\n";



        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(pathName)));

            //open
            document.open();

            /// naslov

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(20);

            Paragraph n = new Paragraph("Izvjestaj", f);
            n.setAlignment(Element.ALIGN_CENTER);

            document.add(n);

            ///tekst

            Paragraph p = new Paragraph();
            p.add(fileText);
            p.setAlignment(Element.ALIGN_LEFT);

            document.add(p);


            //close
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return document;
    }


    public HSSFWorkbook getXlxs(String pathName) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet s = workbook.createSheet("Izvjestaj");


        Row r1 = s.createRow(0);
        r1.createCell(0).setCellValue("Pocetni datum");
        r1.createCell(1).setCellValue(pocetniDatum);

        Row r2 = s.createRow(1);
        r2.createCell(0).setCellValue("Zavrsni datum");
        r2.createCell(1).setCellValue(zavrsniDatum);

        Row r3 = s.createRow(2);
        r3.createCell(0).setCellValue("Totalni broj naručeni pizza");
        r3.createCell(1).setCellValue(totalniBrojNaruceniPizza);

        Row r4 = s.createRow(3);
        r4.createCell(0).setCellValue("Najveca narudžba");
        r4.createCell(1).setCellValue(najvecaNarudzba);

        Row r5 = s.createRow(4);
        r5.createCell(0).setCellValue("Najmanja narudžba");
        r5.createCell(1).setCellValue(najmanjaNaruzba);

        Row r6 = s.createRow(5);
        r6.createCell(0).setCellValue("Broj korisnika koji su naručivali");
        r6.createCell(1).setCellValue(brojKorisnikaKojiSuNarucivali);

        Row r7 = s.createRow(6);
        r7.createCell(0).setCellValue("Broj narudzbi");
        r7.createCell(1).setCellValue(brojNarudbi);

        Row r8 = s.createRow(7);
        r8.createCell(0).setCellValue("Totalno zarađeno");
        r8.createCell(1).setCellValue(totalnoNovcaZaradeno);

       return workbook;
    }

}
