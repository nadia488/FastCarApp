import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/fastcar?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "nadia"; // ton mot de passe MySQL

    // Méthode pour obtenir la connexion
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver MySQL
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données !");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : Driver JDBC non trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur : Impossible de se connecter à la base de données.");
            e.printStackTrace();
        }
        return conn;
    }

    // Pour tester rapidement
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connexion testée et OK !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
