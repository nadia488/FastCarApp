import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VehiculeTableModel extends AbstractTableModel {

    private List<Vehicule> vehicules;
    private final String[] columns = {"Matricule", "Marque", "Modèle", "Prix/Jour", "État", "Kilométrage"};

    public VehiculeTableModel(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public void setVehicules(List<Vehicule> vs) {
        this.vehicules = vs;
    }

    public Vehicule getVehiculeAt(int row) {
        return vehicules.get(row);
    }

    @Override
    public int getRowCount() { return vehicules.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int row, int col) {
        Vehicule v = vehicules.get(row);
        return switch (col) {
            case 0 -> v.getMatricule();
            case 1 -> v.getMarque();
            case 2 -> v.getModele();
            case 3 -> v.getPrixJour();
            case 4 -> v.getEtat();
            case 5 -> v.getKilometrage();
            default -> null;
        };
    }
}
