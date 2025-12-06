import javax.swing.*;
import java.awt.*;

public class AgentDialog extends JDialog {

    private JTextField idField, nomField, prenomField;
    private JButton saveBtn, cancelBtn;
    private AgentDAO dao = new AgentDAO();
    private Agent agent;

    public AgentDialog(Frame parent, Agent agent) {
        super(parent, true);
        this.agent = agent;

        setTitle(agent == null ? "Ajouter Agent" : "Modifier Agent");
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 5, 5));

        idField = new JTextField();
        nomField = new JTextField();
        prenomField = new JTextField();

        add(new JLabel("ID Agent:")); add(idField);
        add(new JLabel("Nom:")); add(nomField);
        add(new JLabel("Prénom:")); add(prenomField);

        saveBtn = new JButton("Enregistrer");
        cancelBtn = new JButton("Annuler");

        add(saveBtn); add(cancelBtn);

        if (agent != null) {
            idField.setText(agent.getAgentId());
            idField.setEditable(false);
            nomField.setText(agent.getNom());
            prenomField.setText(agent.getPrenom());
        }

        saveBtn.addActionListener(e -> save());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void save() {
        String id = idField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();

        if (id.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires !");
            return;
        }

        Agent a = new Agent(id, nom, prenom);

        if (agent == null)
            dao.ajouterAgent(a);
        else
            dao.modifierAgent(a);

        dispose();
    }
}
