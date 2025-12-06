import javax.swing.*;
import java.awt.*;

public class ContratModule extends JPanel {

    private ContratDAO dao = new ContratDAO();

    public ContratModule() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("LISTE DES CONTRATS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        dao.getAllContrats().forEach(c -> {
            area.append(c.getContratId() + " | " + c.getClientCin() + " | " +
                    c.getVehiculeMatricule() + " | " + c.getDateDebut() + " → " + c.getDateFin() +
                    " | " + c.getMontantTotal() + " MAD\n");
        });

        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton newBtn = new JButton("Nouveau Contrat");
        add(newBtn, BorderLayout.SOUTH);

        newBtn.addActionListener(e -> new ContratDialog(null).setVisible(true));
    }
}
