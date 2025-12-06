import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VehiculeModule extends JPanel {

    private VehiculeDAO dao = new VehiculeDAO();
    private JTable table;
    private VehiculeTableModel model;

    public VehiculeModule() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Module LISTE DES VEHICULES");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        model = new VehiculeTableModel(dao.getAll());
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();

        JButton addBtn = new JButton("+ Ajouter");
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

        addBtn.addActionListener(e -> {
            new VehiculeDialog(null, null).setVisible(true);
            refresh();
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            Vehicule v = model.getVehiculeAt(row);
            new VehiculeDialog(null, v).setVisible(true);
            refresh();
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            Vehicule v = model.getVehiculeAt(row);
            dao.supprimerVehicule(v.getMatricule());
            refresh();
        });

        searchBtn.addActionListener(e -> {
            String mot = searchField.getText();
            List<Vehicule> list = dao.rechercher(mot);
            model.setVehicules(list);
            model.fireTableDataChanged();
        });
    }

    private void refresh() {
        model.setVehicules(dao.getAll());
        model.fireTableDataChanged();
    }
}
