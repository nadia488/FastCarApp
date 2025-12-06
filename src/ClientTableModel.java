import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {

    private List<Client> clients;
    private final String[] columns = {"CIN", "Nom", "Prénom", "Adresse", "Téléphone", "Email"};

    public ClientTableModel(List<Client> clients) {
        this.clients = clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Client getClientAt(int row) {
        return clients.get(row);
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Client c = clients.get(row);

        return switch (col) {
            case 0 -> c.getCin();
            case 1 -> c.getNom();
            case 2 -> c.getPrenom();
            case 3 -> c.getAdresse();
            case 4 -> c.getTelephone();
            case 5 -> c.getEmail();
            default -> null;
        };
    }
}
