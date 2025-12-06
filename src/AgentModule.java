import javax.swing.*;
import java.awt.*;

public class AgentModule extends JPanel {

    private AgentDAO dao = new AgentDAO();
    private JTable table;
    private AgentTableModel model;

    public AgentModule() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("GESTION DES AGENTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        model = new AgentTableModel(dao.getAllAgents());
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton addBtn = new JButton("Ajouter");
        JButton editBtn = new JButton("Modifier");
        JButton deleteBtn = new JButton("Supprimer");

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);

        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            new AgentDialog(null, null).setVisible(true);
            refresh();
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            Agent a = model.getAgentAt(row);
            new AgentDialog(null, a).setVisible(true);
            refresh();
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            Agent a = model.getAgentAt(row);
            dao.supprimerAgent(a.getAgentId());
            refresh();
        });
    }

    private void refresh() {
        model.setAgents(dao.getAllAgents());
        model.fireTableDataChanged();
    }
}

