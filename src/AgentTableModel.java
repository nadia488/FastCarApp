import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AgentTableModel extends AbstractTableModel {

    private List<Agent> agents;
    private final String[] columns = {"ID Agent", "Nom", "Prénom"};

    public AgentTableModel(List<Agent> agents) {
        this.agents = agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public Agent getAgentAt(int row) {
        return agents.get(row);
    }

    @Override
    public int getRowCount() {
        return agents.size();
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
        Agent a = agents.get(row);
        return switch (col) {
            case 0 -> a.getAgentId();
            case 1 -> a.getNom();
            case 2 -> a.getPrenom();
            default -> null;
        };
    }
}
