public class Agent {
    private String agentId;
    private String nom;
    private String prenom;

    public Agent(String agentId, String nom, String prenom) {
        this.agentId = agentId;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
