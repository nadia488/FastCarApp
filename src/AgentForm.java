import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgentForm extends JPanel {

    private JTable table;
    private AgentTableModel tableModel;
    private AgentDAO dao = new AgentDAO();

    public AgentForm() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("GESTION DES AGENTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Table
        tableModel = new AgentTableModel(dao.getAllAgents());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // BARRE RECHERCHE
        JPanel searchPanel = new JPanel();
        JLabel l = new JLabel("Recherche:");
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Rechercher");

        searchPanel.add(l);
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        add(searchPanel, BorderLayout.NORTH);

        // Boutons CRUD
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Ajouter");
        JButton editBtn = new JButton("Modifier");
        JButton deleteBtn = new JButton("Supprimer");

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // Actions CRUD
        addBtn.addActionListener(e -> {
            new AgentDialog(null, null).setVisible(true);
            refresh();
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            Agent a = tableModel.getAgentAt(row);
            new AgentDialog(null, a).setVisible(true);
            refresh();
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            Agent a = tableModel.getAgentAt(row);
            dao.supprimerAgent(a.getAgentId());
            refresh();
        });

        // Action Recherche
        searchBtn.addActionListener(e -> {
            String mot = searchField.getText();
            List<Agent> result = dao.rechercherAgents(mot);
            tableModel.setAgents(result);
            tableModel.fireTableDataChanged();
        });
    }

    private void refresh() {
        tableModel.setAgents(dao.getAllAgents());
        tableModel.fireTableDataChanged();
    }
}
