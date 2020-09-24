package com.example.monprojet;

public class medicament_model {
    int id;
    String nom,nom_labo;
    double Ci,CMa,CMi,volume,prix;
    int stabilite;

    public medicament_model(int id, String nom, String nom_labo, double ci, double CMa, double CMi, int stabilite, double volume, double prix) {
        this.id = id;
        this.nom = nom;
        this.nom_labo = nom_labo;
        this.Ci = ci;
        this.CMa = CMa;
        this.CMi = CMi;
        this.stabilite = stabilite;
        this.volume=volume;
        this.prix=prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom_labo() {
        return nom_labo;
    }

    public void setNom_labo(String nom_labo) {
        this.nom_labo = nom_labo;
    }

    public double getCi() {
        return Ci;
    }

    public void setCi(double ci) {
        Ci = ci;
    }

    public double getCMa() {
        return CMa;
    }

    public void setCMa(double CMa) {
        this.CMa = CMa;
    }

    public double getCMi() {
        return CMi;
    }

    public void setCMi(double CMi) {
        this.CMi = CMi;
    }

    public int getStabilite() {
        return stabilite;
    }

    public void setStabilite(int stabilite) { this.stabilite = stabilite; }

    public double getvolume() { return volume; }

    public void setvolume(double volume) {
        this.volume = volume;
    }

    public double getprix() {
        return prix;
    }

    public void setprix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "medicament_model{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nom_labo='" + nom_labo + '\'' +
                ", Ci=" + Ci +
                ", CMa=" + CMa +
                ", CMi=" + CMi +
                ", stabilite=" + stabilite +
                ", volume=" + volume +
                ", prix=" + prix +
                '}';
    }
}
