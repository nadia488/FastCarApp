import javax.swing.*;
import java.awt.*;

public class VehiculeDialog extends JDialog {

    private JTextField matField, marqueField, modeleField, prixField, kmField;
    private JComboBox<String> etatBox;
    private VehiculeDAO vehiculeDAO = new VehiculeDAO();
    private Vehicule vehicule;

    // <-- On accepte maintenant Window comme parent
    public VehiculeDialog(Window parent, Vehicule vehicule) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.vehicule = vehicule;

        setTitle(vehicule == null ? "Ajouter Véhicule" : "Modifier Véhicule");
        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 5, 5));

        matField = new JTextField();
        marqueField = new JTextField();
        modeleField = new JTextField();
        prixField = new JTextField();
        kmField = new JTextField();

        String[] etats = {"Disponible", "Loue", "Maintenance"};
        etatBox = new JComboBox<>(etats);

        add(new JLabel("Matricule:")); add(matField);
        add(new JLabel("Marque:")); add(marqueField);
        add(new JLabel("Modèle:")); add(modeleField);
        add(new JLabel("Prix/Jour:")); add(prixField);
        add(new JLabel("État:")); add(etatBox);
        add(new JLabel("Kilométrage:")); add(kmField);

        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        add(saveBtn); add(cancelBtn);

        if (vehicule != null) {
            matField.setText(vehicule.getMatricule());
            matField.setEditable(false);
            marqueField.setText(vehicule.getMarque());
            modeleField.setText(vehicule.getModele());
            prixField.setText(String.valueOf(vehicule.getPrixJour()));
            etatBox.setSelectedItem(vehicule.getEtat());
            kmField.setText(String.valueOf(vehicule.getKilometrage()));
        }

        saveBtn.addActionListener(e -> save());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void save() {
        String mat = matField.getText();
        String mar = marqueField.getText();
        String mod = modeleField.getText();
        double prix = Double.parseDouble(prixField.getText());
        int km = Integer.parseInt(kmField.getText());
        String etat = etatBox.getSelectedItem().toString();

        Vehicule v = new Vehicule(mat, mar, mod, prix, etat, km);

        if (vehicule == null) vehiculeDAO.ajouterVehicule(v);
        else vehiculeDAO.modifierVehicule(v);

        dispose();
    }
}
