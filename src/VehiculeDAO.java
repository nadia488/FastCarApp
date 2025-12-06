import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDAO {

    public void ajouterVehicule(Vehicule v) {
        String sql = "INSERT INTO vehicules VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getMatricule());
            stmt.setString(2, v.getMarque());
            stmt.setString(3, v.getModele());
            stmt.setDouble(4, v.getPrixJour());
            stmt.setString(5, v.getEtat());
            stmt.setInt(6, v.getKilometrage());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur ajout véhicule : " + e.getMessage());
        }
    }

    public List<Vehicule> getAll() {
        List<Vehicule> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicules";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Vehicule(
                        rs.getString("matricule"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getDouble("prix_jour"),
                        rs.getString("etat"),
                        rs.getInt("kilometrage")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur récupération véhicules");
        }

        return list;
    }

    public void supprimerVehicule(String matricule) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM vehicules WHERE matricule=?")) {

            stmt.setString(1, matricule);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur suppression véhicule");
        }
    }

    public void modifierVehicule(Vehicule v) {
        String sql = "UPDATE vehicules SET marque=?, modele=?, prix_jour=?, etat=?, kilometrage=? WHERE matricule=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getMarque());
            stmt.setString(2, v.getModele());
            stmt.setDouble(3, v.getPrixJour());
            stmt.setString(4, v.getEtat());
            stmt.setInt(5, v.getKilometrage());
            stmt.setString(6, v.getMatricule());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur modification véhicule");
        }
    }

    // RECHERCHE
    public List<Vehicule> rechercher(String mot) {
        List<Vehicule> list = new ArrayList<>();

        String sql = "SELECT * FROM vehicules WHERE matricule LIKE ? OR marque LIKE ? OR modele LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String r = "%" + mot + "%";
            stmt.setString(1, r);
            stmt.setString(2, r);
            stmt.setString(3, r);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Vehicule(
                        rs.getString("matricule"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getDouble("prix_jour"),
                        rs.getString("etat"),
                        rs.getInt("kilometrage")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur recherche véhicule");
        }

        return list;
    }
}
