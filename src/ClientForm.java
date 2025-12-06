import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientForm extends JPanel {

    private JTable table;
    private ClientTableModel tableModel;
    private ClientDAO clientDAO = new ClientDAO();

    public ClientForm() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("GESTION DES CLIENTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Table
        tableModel = new ClientTableModel(clientDAO.getAllClients());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // BARRE DE RECHERCHE
        JPanel searchPanel = new JPanel();
        JLabel lbl = new JLabel("Recherche:");
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Rechercher");

        searchPanel.add(lbl);
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


        // ---- Actions ----

        addBtn.addActionListener(e -> {
            new ClientDialog(null, null).setVisible(true);
            refresh();
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez un client !");
                return;
            }
            Client c = tableModel.getClientAt(row);
            new ClientDialog(null, c).setVisible(true);
            refresh();
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            Client c = tableModel.getClientAt(row);
            clientDAO.supprimerClient(c.getCin());
            refresh();
        });

        // Recherche action
        searchBtn.addActionListener(e -> {
            String mot = searchField.getText();
            List<Client> result = clientDAO.rechercherClients(mot);
            tableModel.setClients(result);
            tableModel.fireTableDataChanged();
        });
    }

    private void refresh() {
        tableModel.setClients(clientDAO.getAllClients());
        tableModel.fireTableDataChanged();
    }
}
