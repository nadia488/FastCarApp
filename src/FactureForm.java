import javax.swing.*;
import java.awt.*;

public class FactureForm extends JPanel {

    private JTextField contratField;
    private JButton generateBtn;

    public FactureForm() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("IMPRESSION D'UNE FACTURE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3, 1, 10, 10));

        center.add(new JLabel("Numéro du Contrat :", SwingConstants.CENTER));

        contratField = new JTextField();
        center.add(contratField);

        generateBtn = new JButton("Générer la Facture PDF");
        center.add(generateBtn);

        add(center, BorderLayout.CENTER);

        // Action
        generateBtn.addActionListener(e -> generer());
    }

    private void generer() {
        String id = contratField.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de contrat !");
            return;
        }

        ContratDAO cdao = new ContratDAO();
        ClientDAO cldao = new ClientDAO();
        VehiculeDAO vdao = new VehiculeDAO();
        AgentDAO adao = new AgentDAO();

        // récupérer contrat
        Contrat c = cdao.getContratById(id);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Contrat introuvable !");
            return;
        }

        // récupérer client
        Client cl = cldao.getByCin(c.getClientCin());

        // récupérer véhicule
        Vehicule v = vdao.rechercher(c.getVehiculeMatricule()).get(0);

        // récupérer agent
        Agent a = adao.getById(c.getAgentId());

        // générer la facture avec 4 objets
        FactureGenerator.genererFacture(c, cl, v, a);

        JOptionPane.showMessageDialog(this,
                "Facture générée avec succès : Facture_" + c.getContratId() + ".pdf");
    }

}

