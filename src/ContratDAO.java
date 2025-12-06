import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratDAO {

    // Vérifier si véhicule disponible
    public boolean vehiculeDisponible(String matricule, String debut, String fin) {

        String sql = """
            SELECT COUNT(*) AS nbr
            FROM contrats
            WHERE veh_matricule = ?
              AND (date_debut <= ? AND date_fin >= ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricule);
            stmt.setString(2, fin);
            stmt.setString(3, debut);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("nbr") == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Ajouter contrat
    public void ajouterContrat(Contrat c) {

        String sql = """
            INSERT INTO contrats
            (contrat_id, date_debut, date_fin, montant_total, mode_paiement,
             client_cin, veh_matricule, agent_id, km_depart)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getContratId());
            stmt.setString(2, c.getDateDebut());
            stmt.setString(3, c.getDateFin());
            stmt.setDouble(4, c.getMontantTotal());
            stmt.setString(5, c.getModePaiement());
            stmt.setString(6, c.getClientCin());
            stmt.setString(7, c.getVehiculeMatricule());
            stmt.setString(8, c.getAgentId());
            stmt.setInt(9, c.getKmDepart());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Liste des contrats
    public List<Contrat> getAllContrats() {
        List<Contrat> list = new ArrayList<>();

        String sql = "SELECT * FROM contrats";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                list.add(new Contrat(
                        rs.getString("contrat_id"),
                        rs.getString("date_debut"),
                        rs.getString("date_fin"),
                        rs.getDouble("montant_total"),
                        rs.getString("mode_paiement"),
                        rs.getString("client_cin"),
                        rs.getString("veh_matricule"),
                        rs.getString("agent_id"),
                        rs.getInt("km_depart")
                ));

            }

        } catch (SQLException e) { e.printStackTrace(); }

        return list;
    }

    public Contrat getContratById(String id) {
        String sql = "SELECT * FROM contrats WHERE contrat_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contrat(
                        rs.getString("contrat_id"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getDouble("montant_total"),
                        rs.getString("mode_paiement"),
                        rs.getString("client_cin"),
                        rs.getString("veh_matricule"),
                        rs.getString("agent_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
