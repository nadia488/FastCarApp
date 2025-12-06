import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ContratForm extends JPanel {

    private JTable table;
    private ContratTableModel tableModel;
    private ContratDAO dao = new ContratDAO();

    public ContratForm() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("GESTION DES CONTRATS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        tableModel = new ContratTableModel(dao.getAllContrats());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Nouveau Contrat");
        btnPanel.add(addBtn);

        JButton factBtn = new JButton("Imprimer Facture");
        btnPanel.add(factBtn);

        add(btnPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            new ContratDialog(null).setVisible(true);
            refresh();
        });

        factBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez un contrat !");
                return;
            }

            Contrat c = tableModel.getContratAt(row);

            // récupérer objets
            Client cl = new ClientDAO().getByCin(c.getClientCin());
            Vehicule v = new VehiculeDAO().rechercher(c.getVehiculeMatricule()).get(0);
            Agent a = new AgentDAO().getById(c.getAgentId());

            FactureGenerator.genererFacture(c, cl, v, a);
            JOptionPane.showMessageDialog(this, "Facture générée !");
        });

    }

    private void refresh() {
        tableModel.setContrats(dao.getAllContrats());
        tableModel.fireTableDataChanged();
    }
}

