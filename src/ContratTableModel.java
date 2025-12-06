import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContratTableModel extends AbstractTableModel {

    private List<Contrat> contrats;
    private final String[] columns = {
            "ID Contrat", "Client", "Véhicule", "Agent",
            "Date début", "Date fin", "Montant (MAD)", "Paiement"
    };

    public ContratTableModel(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public void setContrats(List<Contrat> list) {
        this.contrats = list;
    }

    public Contrat getContratAt(int row) {
        return contrats.get(row);
    }

    @Override
    public int getRowCount() { return contrats.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int col) { return columns[col]; }

    @Override
    public Object getValueAt(int row, int col) {

        Contrat c = contrats.get(row);

        return switch (col) {
            case 0 -> c.getContratId();
            case 1 -> c.getClientCin();
            case 2 -> c.getVehiculeMatricule();
            case 3 -> c.getAgentId();
            case 4 -> c.getDateDebut();
            case 5 -> c.getDateFin();
            case 6 -> c.getMontantTotal();
            case 7 -> c.getModePaiement();
            default -> null;
        };
    }
}

