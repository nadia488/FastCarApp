public class Contrat {

    private String contratId;
    private String dateDebut;
    private String dateFin;
    private double montantTotal;
    private String modePaiement;
    private String clientCin;
    private String vehiculeMatricule;
    private String agentId;
    private int kmDepart;

    // Constructeur complet
    public Contrat(String contratId, String dateDebut, String dateFin,
                   double montantTotal, String modePaiement,
                   String clientCin, String vehiculeMatricule,
                   String agentId, int kmDepart) {

        this.contratId = contratId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montantTotal = montantTotal;
        this.modePaiement = modePaiement;
        this.clientCin = clientCin;
        this.vehiculeMatricule = vehiculeMatricule;
        this.agentId = agentId;
        this.kmDepart = kmDepart;
    }

    // Constructeur utilisé par getContratById (avec Date)
    public Contrat(String contratId, java.sql.Date d1, java.sql.Date d2,
                   double montantTotal, String modePaiement,
                   String clientCin, String vehiculeMatricule,
                   String agentId) {

        this.contratId = contratId;
        this.dateDebut = d1.toString();
        this.dateFin = d2.toString();
        this.montantTotal = montantTotal;
        this.modePaiement = modePaiement;
        this.clientCin = clientCin;
        this.vehiculeMatricule = vehiculeMatricule;
        this.agentId = agentId;
    }

    // ======== GETTERS ========
    public String getContratId() { return contratId; }
    public String getDateDebut() { return dateDebut; }
    public String getDateFin() { return dateFin; }
    public double getMontantTotal() { return montantTotal; }
    public String getModePaiement() { return modePaiement; }
    public String getClientCin() { return clientCin; }
    public String getVehiculeMatricule() { return vehiculeMatricule; }
    public String getAgentId() { return agentId; }
    public int getKmDepart() { return kmDepart; }

    // ======== SETTERS ========
    public void setContratId(String contratId) { this.contratId = contratId; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
    public void setClientCin(String clientCin) { this.clientCin = clientCin; }
    public void setVehiculeMatricule(String vehiculeMatricule) { this.vehiculeMatricule = vehiculeMatricule; }
    public void setAgentId(String agentId) { this.agentId = agentId; }
    public void setKmDepart(int kmDepart) { this.kmDepart = kmDepart; }
}
