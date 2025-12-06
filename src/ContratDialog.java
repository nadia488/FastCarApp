import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ContratDialog extends JDialog {

    private JComboBox<String> clientBox, vehiculeBox, agentBox, paiementBox;
    private JTextField debutField, finField, kmField, montantField;

    private ContratDAO contratDAO = new ContratDAO();
    private ClientDAO clientDAO = new ClientDAO();
    private VehiculeDAO vehiculeDAO = new VehiculeDAO();
    private AgentDAO agentDAO = new AgentDAO();

    public ContratDialog(Frame parent) {
        super(parent, true);
        setTitle("Nouveau Contrat");
        setSize(450, 450);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(10, 2, 5, 5));

        // Chargement des données
        clientBox = new JComboBox<>();
        for (Client c : clientDAO.getAllClients()) clientBox.addItem(c.getCin());

        vehiculeBox = new JComboBox<>();
        for (Vehicule v : vehiculeDAO.getAll()) vehiculeBox.addItem(v.getMatricule());

        agentBox = new JComboBox<>();
        for (Agent a : agentDAO.getAllAgents()) agentBox.addItem(a.getAgentId());

        paiementBox = new JComboBox<>(new String[]{"Especes", "Carte", "Virement"});

        debutField = new JTextField();
        finField = new JTextField();
        kmField = new JTextField();
        montantField = new JTextField();
        montantField.setEditable(false);

        add(new JLabel("Client CIN:")); add(clientBox);
        add(new JLabel("Véhicule:")); add(vehiculeBox);
        add(new JLabel("Agent:")); add(agentBox);

        add(new JLabel("Date Début (YYYY-MM-DD):")); add(debutField);
        add(new JLabel("Date Fin (YYYY-MM-DD):")); add(finField);

        add(new JLabel("Kilométrage départ:")); add(kmField);
        add(new JLabel("Paiement:")); add(paiementBox);

        add(new JLabel("Montant Total:")); add(montantField);

        JButton calcBtn = new JButton("Calculer montant");
        JButton saveBtn = new JButton("Enregistrer");

        add(calcBtn); add(saveBtn);


        // === ACTION CALCUL MONTANT ===
        calcBtn.addActionListener(e -> calculerMontant());

        // === ACTION SAUVEGARDE ===
        saveBtn.addActionListener(e -> enregistrerContrat());
    }

    private void calculerMontant() {

        String mat = (String) vehiculeBox.getSelectedItem();
        Vehicule v = vehiculeDAO.rechercher(mat).get(0);

        LocalDate d1 = LocalDate.parse(debutField.getText());
        LocalDate d2 = LocalDate.parse(finField.getText());

        long days = ChronoUnit.DAYS.between(d1, d2);
        if (days <= 0) {
            JOptionPane.showMessageDialog(this, "Dates invalides !");
            return;
        }

        double montant = days * v.getPrixJour();
        montantField.setText(String.valueOf(montant));
    }

    private void enregistrerContrat() {

        String client = (String) clientBox.getSelectedItem();
        String veh = (String) vehiculeBox.getSelectedItem();
        String agent = (String) agentBox.getSelectedItem();

        String d1 = debutField.getText();
        String d2 = finField.getText();
        int km = Integer.parseInt(kmField.getText());
        double montant = Double.parseDouble(montantField.getText());
        String paiement = (String) paiementBox.getSelectedItem();

        // Vérification disponibilité
        if (!contratDAO.vehiculeDisponible(veh, d1, d2)) {
            JOptionPane.showMessageDialog(this, "Véhicule NON Disponible !");
            return;
        }

        String id = "LOC-" + System.currentTimeMillis();

        Contrat c = new Contrat(id, d1, d2, montant, paiement, client, veh, agent, km);
        contratDAO.ajouterContrat(c);

        JOptionPane.showMessageDialog(this, "Contrat enregistré !");
        dispose();
    }
}
