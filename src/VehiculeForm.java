import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VehiculeForm extends JPanel {

    private VehiculeDAO dao = new VehiculeDAO();
    private JTable table;
    private VehiculeTableModel model;

    public VehiculeForm() {
        setLayout(new BorderLayout());

        // ====== TITRE ======
        JLabel title = new JLabel("GESTION DES VEHICULES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // ====== TABLEAU ======
        model = new VehiculeTableModel(dao.getAll());
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ====== PANEL BOUTONS ======
        JPanel btnPanel = new JPanel();

        JButton addBtn = new JButton("Ajouter");
        JButton editBtn = new JButton("Modifier");
        JButton delBtn = new JButton("Supprimer");
        JButton searchBtn = new JButton("Rechercher");

        JTextField searchField = new JTextField(10);

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(delBtn);
        btnPanel.add(searchField);
        btnPanel.add(searchBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ====== ACTIONS ======
        addBtn.addActionListener(e -> {
            new VehiculeDialog(SwingUtilities.getWindowAncestor(this), null).setVisible(true);
            refreshTable();
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            Vehicule v = model.getVehiculeAt(row);
            new VehiculeDialog(SwingUtilities.getWindowAncestor(this), v).setVisible(true);
            refreshTable();
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            Vehicule v = model.getVehiculeAt(row);
            dao.supprimerVehicule(v.getMatricule());
            refreshTable();
        });

        searchBtn.addActionListener(e -> {
            String mot = searchField.getText();
            List<Vehicule> list = dao.rechercher(mot);
            model.setVehicules(list);
            model.fireTableDataChanged();
        });
    }

    private void refreshTable() {
        model.setVehicules(dao.getAll());
        model.fireTableDataChanged();
    }

    // Pour tester indépendamment (facultatif)
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test VehiculeForm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new VehiculeForm());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
