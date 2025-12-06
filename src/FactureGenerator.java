import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class FactureGenerator {

    public static void genererFacture(Contrat c, Client cl, Vehicule v, Agent a) {
        try {
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream("Facture_" + c.getContratId() + ".pdf"));

            doc.open();

            // =================== LOGO + TITRE ===================
            PdfPTable header = new PdfPTable(1);
            header.setWidthPercentage(100);

            PdfPCell cell1 = new PdfPCell(new Phrase("FASTCAR LOCATION",
                    new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, BaseColor.BLACK)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell2 = new PdfPCell(new Phrase("FACTURE DE LOCATION\n\n",
                    new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY)));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);

            header.addCell(cell1);
            header.addCell(cell2);
            doc.add(header);


            // =================== INFORMATIONS CONTRAT ===================
            PdfPTable tbContrat = new PdfPTable(2);
            tbContrat.setWidthPercentage(100);
            tbContrat.setSpacingBefore(10);

            tbContrat.addCell(cell("N° Contrat", true));
            tbContrat.addCell(cell(c.getContratId(), false));

            tbContrat.addCell(cell("Date Début", true));
            tbContrat.addCell(cell(c.getDateDebut(), false));

            tbContrat.addCell(cell("Date Fin", true));
            tbContrat.addCell(cell(c.getDateFin(), false));

            tbContrat.addCell(cell("Montant Total", true));
            tbContrat.addCell(cell(c.getMontantTotal() + " MAD", false));

            tbContrat.addCell(cell("Mode de Paiement", true));
            tbContrat.addCell(cell(c.getModePaiement(), false));

            doc.add(tbContrat);


            // =================== INFORMATIONS CLIENT ===================
            Paragraph sectionClient = new Paragraph("\nCLIENT",
                    new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
            doc.add(sectionClient);

            PdfPTable tbClient = new PdfPTable(2);
            tbClient.setWidthPercentage(100);
            tbClient.setSpacingBefore(5);

            tbClient.addCell(cell("CIN", true));
            tbClient.addCell(cell(cl.getCin(), false));

            tbClient.addCell(cell("Nom et Prénom", true));
            tbClient.addCell(cell(cl.getNom() + " " + cl.getPrenom(), false));

            tbClient.addCell(cell("Adresse", true));
            tbClient.addCell(cell(cl.getAdresse(), false));

            tbClient.addCell(cell("Téléphone", true));
            tbClient.addCell(cell(cl.getTelephone(), false));

            tbClient.addCell(cell("Email", true));
            tbClient.addCell(cell(cl.getEmail(), false));

            doc.add(tbClient);


            // =================== INFORMATIONS VEHICULE ===================
            Paragraph sectionVeh = new Paragraph("\nVÉHICULE",
                    new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
            doc.add(sectionVeh);

            PdfPTable tbVeh = new PdfPTable(2);
            tbVeh.setWidthPercentage(100);
            tbVeh.setSpacingBefore(5);

            tbVeh.addCell(cell("Matricule", true));
            tbVeh.addCell(cell(v.getMatricule(), false));

            tbVeh.addCell(cell("Marque", true));
            tbVeh.addCell(cell(v.getMarque(), false));

            tbVeh.addCell(cell("Modèle", true));
            tbVeh.addCell(cell(v.getModele(), false));

            tbVeh.addCell(cell("Prix Journalier", true));
            tbVeh.addCell(cell(v.getPrixJour() + " MAD", false));

            tbVeh.addCell(cell("Kilométrage Départ", true));
            tbVeh.addCell(cell(c.getKmDepart() + " KM", false));

            doc.add(tbVeh);


            // =================== AGENT ===================
            Paragraph sectionAgent = new Paragraph("\nAGENT",
                    new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
            doc.add(sectionAgent);

            PdfPTable tbAgent = new PdfPTable(2);
            tbAgent.setWidthPercentage(100);
            tbAgent.setSpacingBefore(5);

            tbAgent.addCell(cell("ID Agent", true));
            tbAgent.addCell(cell(a.getAgentId(), false));

            tbAgent.addCell(cell("Nom", true));
            tbAgent.addCell(cell(a.getNom() + " " + a.getPrenom(), false));

            doc.add(tbAgent);

            // =================== FOOTER ===================
            Paragraph foot = new Paragraph("\n\nMerci pour votre confiance !\nFASTCAR – Marrakech\nTél : 05 24 00 00 00\n\n",
                    new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GRAY));
            doc.add(foot);

            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour créer des cases de tableau
    private static PdfPCell cell(String txt, boolean isHeader) {
        Font f = isHeader ?
                new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
                : new Font(Font.FontFamily.HELVETICA, 12);

        PdfPCell c = new PdfPCell(new Phrase(txt, f));
        c.setPadding(8);
        c.setHorizontalAlignment(Element.ALIGN_LEFT);

        if (isHeader) {
            c.setBackgroundColor(new BaseColor(230, 230, 230));
        }

        return c;
    }
}
