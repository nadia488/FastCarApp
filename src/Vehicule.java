public class Vehicule {

    private String matricule;
    private String marque;
    private String modele;
    private double prixJour;
    private String etat;
    private int kilometrage;

    public Vehicule(String matricule, String marque, String modele, double prixJour,
                    String etat, int kilometrage) {

        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.prixJour = prixJour;
        this.etat = etat;
        this.kilometrage = kilometrage;
    }

    public String getMatricule() { return matricule; }
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public double getPrixJour() { return prixJour; }
    public String getEtat() { return etat; }
    public int getKilometrage() { return kilometrage; }

    public void setMarque(String m) { this.marque = m; }
    public void setModele(String m) { this.modele = m; }
    public void setPrixJour(double p) { this.prixJour = p; }
    public void setEtat(String e) { this.etat = e; }
    public void setKilometrage(int k) { this.kilometrage = k; }
}
