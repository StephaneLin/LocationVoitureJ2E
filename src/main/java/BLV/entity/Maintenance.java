package BLV.entity;

public class Maintenance {

    private int maintenanceId;
    private boolean pneu;
    private boolean carosserie;
    private boolean electronique;
    private boolean moteur;
    private boolean conforme;
    private int vehiculeFK;

    public Maintenance(int maintenanceId, boolean pneu, boolean carosserie, boolean electronique, boolean moteur, boolean conforme, int vehiculeFK) {
        this.maintenanceId = maintenanceId;
        this.pneu = pneu;
        this.carosserie = carosserie;
        this.electronique = electronique;
        this.moteur = moteur;
        this.conforme = conforme;
        this.vehiculeFK = vehiculeFK;
    }

    public Maintenance(boolean pneu, boolean carosserie, boolean electronique, boolean moteur, boolean conforme, int vehiculeFK) {
        this.pneu = pneu;
        this.carosserie = carosserie;
        this.electronique = electronique;
        this.moteur = moteur;
        this.conforme = conforme;
        this.vehiculeFK = vehiculeFK;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public boolean isPneu() {
        return pneu;
    }

    public void setPneu(boolean pneu) {
        this.pneu = pneu;
    }

    public boolean isCarosserie() {
        return carosserie;
    }

    public void setCarosserie(boolean carosserie) {
        this.carosserie = carosserie;
    }

    public boolean isElectronique() {
        return electronique;
    }

    public void setElectronique(boolean electronique) {
        this.electronique = electronique;
    }

    public boolean isMoteur() {
        return moteur;
    }

    public void setMoteur(boolean moteur) {
        this.moteur = moteur;
    }

    public boolean isConforme() {
        return conforme;
    }

    public void setConforme(boolean conforme) {
        this.conforme = conforme;
    }

    public int getVehiculeFK() {
        return vehiculeFK;
    }

    public void setVehiculeFK(int vehiculeFK) {
        this.vehiculeFK = vehiculeFK;
    }
}
