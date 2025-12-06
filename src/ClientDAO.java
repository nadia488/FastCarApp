import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void ajouterClient(Client client) {
        String sql = "INSERT INTO clients VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getCin());
            stmt.setString(2, client.getNom());
            stmt.setString(3, client.getPrenom());
            stmt.setString(4, client.getAdresse());
            stmt.setString(5, client.getTelephone());
            stmt.setString(6, client.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur ajout client : " + e.getMessage());
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getString("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur chargement clients : " + e.getMessage());
        }

        return clients;
    }

    public void modifierClient(Client client) {
        String sql = "UPDATE clients SET nom=?, prenom=?, adresse=?, telephone=?, email=? WHERE cin=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getAdresse());
            stmt.setString(4, client.getTelephone());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getCin());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur modification client : " + e.getMessage());
        }
    }

    public void supprimerClient(String cin) {
        String sql = "DELETE FROM clients WHERE cin=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cin);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur suppression client : " + e.getMessage());
        }
    }

    public List<Client> rechercherClients(String mot) {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE cin LIKE ? OR nom LIKE ? OR prenom LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String m = "%" + mot + "%";
            stmt.setString(1, m);
            stmt.setString(2, m);
            stmt.setString(3, m);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Client(
                        rs.getString("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public Client getByCin(String cin) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients WHERE cin=?")) {

            ps.setString(1, cin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Client(
                        rs.getString("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
