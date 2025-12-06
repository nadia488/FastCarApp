import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {

    public void ajouterAgent(Agent a) {
        String sql = "INSERT INTO agents (agent_id, nom, prenom) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getAgentId());
            stmt.setString(2, a.getNom());
            stmt.setString(3, a.getPrenom());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur ajout agent : " + e.getMessage());
        }
    }

    public List<Agent> getAllAgents() {
        List<Agent> list = new ArrayList<>();
        String sql = "SELECT * FROM agents";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Agent(
                        rs.getString("agent_id"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur récupération agents");
        }

        return list;
    }

    public void supprimerAgent(String id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM agents WHERE agent_id=?")) {

            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur suppression agent");
        }
    }

    public void modifierAgent(Agent a) {
        String sql = "UPDATE agents SET nom=?, prenom=? WHERE agent_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getNom());
            stmt.setString(2, a.getPrenom());
            stmt.setString(3, a.getAgentId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur modification agent");
        }
    }

    public List<Agent> rechercherAgents(String mot) {
        List<Agent> list = new ArrayList<>();

        String sql = "SELECT * FROM agents WHERE agent_id LIKE ? OR nom LIKE ? OR prenom LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String m = "%" + mot + "%";
            stmt.setString(1, m);
            stmt.setString(2, m);
            stmt.setString(3, m);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Agent(
                        rs.getString("agent_id"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public Agent getById(String id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM agents WHERE agent_id=?")) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Agent(
                        rs.getString("agent_id"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
