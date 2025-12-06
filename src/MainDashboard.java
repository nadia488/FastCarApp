import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    private JPanel menuPanel;
    private JPanel contentPanel;

    public MainDashboard() {

        setTitle("FASTCAR LOCATION – Gestion Centrale");
        setSize(1100, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ================= MENU GAUCHE =================
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(240, 240, 240));
        menuPanel.setPreferredSize(new Dimension(220, 600));
        menuPanel.setLayout(new GridLayout(10, 1, 0, 10));

        JLabel title = new JLabel("MENU PRINCIPAL", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnVehicules = new JButton("Gérer les Véhicules");
        JButton btnClients = new JButton("Gérer les Clients");
        JButton btnAgents = new JButton("Gérer les Agents");
        JButton btnContrats = new JButton("Gérer les Contrats");
        JButton btnFacture = new JButton("Imprimer une Facture");
        JButton btnLogout = new JButton("Déconnexion");

        menuPanel.add(title);
        menuPanel.add(btnVehicules);
        menuPanel.add(btnClients);
        menuPanel.add(btnAgents);
        menuPanel.add(btnContrats);
        menuPanel.add(btnFacture);
        menuPanel.add(new JLabel(""));
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.WEST);

        // =============== ZONE CENTRALE ===============
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel accueil = new JLabel("Bienvenue dans FASTCAR LOCATION", SwingConstants.CENTER);
        accueil.setFont(new Font("Arial", Font.BOLD, 22));
        contentPanel.add(accueil, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);


        // ================= ACTIONS =================

        // Module Vehicules
        btnVehicules.addActionListener(e -> {
            afficherModule(new VehiculeModule());
        });

        // Module Clients
        btnClients.addActionListener(e -> {
            afficherModule(new ClientForm());
        });

        // Module Agents
        btnAgents.addActionListener(e -> {
            afficherModule(new AgentForm());
        });

        // Contrats
        btnContrats.addActionListener(e -> {
            afficherModule(new ContratForm());
        });

        //Facture
        btnFacture.addActionListener(e -> {
            afficherModule(new ContratForm()); // facture depuis contrat
        });


        btnLogout.addActionListener(e -> dispose());
    }


    private void afficherModule(Component comp) {
        contentPanel.removeAll();
        contentPanel.add(comp, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    public static void main(String[] args) {
        new MainDashboard().setVisible(true);
    }
}
