import javax.swing.*;
import java.awt.*;

public class ClientDialog extends JDialog {

    private JTextField cinField, nomField, prenomField, adresseField, telField, emailField;
    private ClientDAO clientDAO = new ClientDAO();
    private Client client;

    public ClientDialog(Frame parent, Client client) {
        super(parent, true);
        this.client = client;

        setTitle(client == null ? "Ajouter Client" : "Modifier Client");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 5, 5));

        cinField = new JTextField();
        nomField = new JTextField();
        prenomField = new JTextField();
        adresseField = new JTextField();
        telField = new JTextField();
        emailField = new JTextField();

        add(new JLabel("CIN:")); add(cinField);
        add(new JLabel("Nom:")); add(nomField);
        add(new JLabel("Prénom:")); add(prenomField);
        add(new JLabel("Adresse:")); add(adresseField);
        add(new JLabel("Téléphone:")); add(telField);
        add(new JLabel("Email:")); add(emailField);

        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        add(saveBtn);
        add(cancelBtn);

        if (client != null) {
            cinField.setText(client.getCin());
            cinField.setEditable(false);
            nomField.setText(client.getNom());
            prenomField.setText(client.getPrenom());
            adresseField.setText(client.getAdresse());
            telField.setText(client.getTelephone());
            emailField.setText(client.getEmail());
        }

        saveBtn.addActionListener(e -> saveClient());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void saveClient() {
        if (cinField.getText().isEmpty() || nomField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CIN et Nom sont obligatoires !");
            return;
        }

        Client c = new Client(
                cinField.getText(),
                nomField.getText(),
                prenomField.getText(),
                adresseField.getText(),
                telField.getText(),
                emailField.getText()
        );

        if (client == null)
            clientDAO.ajouterClient(c);
        else
            clientDAO.modifierClient(c);

        dispose();
    }
}
