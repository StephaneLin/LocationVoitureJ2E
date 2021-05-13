package BLV.DAO;

public class ConstantSQL {

    protected static final String INSERT_CAR = "INSERT INTO vehicule(marque, modele, annee, kilometrage, photo, prix, reservationFK, maintenanceFK, parkingFK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String DELETE_CAR = "DELETE FROM vehicule WHERE vehiculeId = ?";
    protected static final String UPDATE_CAR = "UPDATE vehicule SET marque = ?, modele = ?, annee = ?, kilometrage = ?, photo = ?, prix = ? WHERE vehiculeId = ?";
    protected static final String FIND_CAR_BY_ID = "SELECT * FROM vehicule WHERE vehiculeId = ?";
    protected static final String FIND_ALL_CAR = "SELECT * FROM vehicule";
    protected static final String FIND_AVAILABLE_CAR = "SELECT * FROM vehicule LEFT JOIN reservation ON vehicule.reservationFK = reservation.reservationId " +
            "WHERE vehicule.parkingFK = ? " +
            "AND ( reservation.debutDate > ? OR reservation.finDate < ? OR vehicule.reservationFK IS NULL )";

    protected static final String INSERT_PARKING = "INSERT INTO parking( name, address, nbrCar, phoneNumber)  VALUES (?, ?, ?, ?)";
    protected static final String DELETE_PARKING = "DELETE FROM parking WHERE parkingId = ?";
    protected static final String UPDATE_PARKING = "UPDATE parking SET name = ?, address = ?, nbrCar = ?, phoneNumber = ? WHERE parkingId = ?";
    protected static final String FIND_PARKING_BY_ID = "SELECT * FROM parking WHERE parkingId =  ?";
    protected static final String FIND_ALL_PARKING = "SELECT * FROM parking";

    protected static final String INSERT_USER_ENTERPRISE = "INSERT INTO utilisateur(entitee, connexionDate, companyName, companyPhone, siret, email, password) VALUES (1, ?, ?, ?, ?, ?, ?)";
    protected static final String INSERT_USER_CLIENT = "INSERT INTO utilisateur(entitee, connexionDate, userLastName, userFirstName, userPhone, email, userYearOld, password, accessRightsFK, paymentCardFK, licenceFK) VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String DELETE_USER = "DELETE FROM utilisateur WHERE userId = ?";
    protected static final String UPDATE_USER_ENTREPRISE = "UPDATE utilisateur SET connexionDate = ?, companyPhone = ?, email = ?, password = ? WHERE userId = ?";
    protected static final String UPDATE_USER_CLIENT = "UPDATE utilisateur SET connexionDate = ?, userPhone = ?, email = ?, userYearOld = ?, password = ? WHERE userId = ?";
    protected static final String FIND_USER_BY_ID = "SELECT * FROM utilisateur WHERE userId = ?";
    protected static final String FIND_USER_BY_EMAIL = "SELECT * FROM utilisateur WHERE email = ?";
    protected static final String FIND_USER_ENTREPRISE = "SELECT * FROM utilisateur WHERE entitee = 1";
    protected static final String FIND_USER_CLIENT = "SELECT * FROM utilisateur WHERE entitee = 0";
    protected static final String FIND_ALL_USER = "SELECT * FROM utilisateur ORDER BY userID";

    protected static final String INSERT_MAINTENANCE = "INSERT INTO maintenance(pneu, carosserie, electronique, moteur, conforme, vehiculeFK) VALUES (?, ?, ?, ?, ?, ?)";
    protected static final String DELETE_MAINTENANCE = "DELETE FROM maintenance WHERE maintenanceId = ?";
    protected static final String UPDATE_MAINTENANCE = "UPDATE maintenance SET pneu = ?, carosserie = ?, electronique = ?, moteur = ?, conforme = ? WHERE maintenanceId = ?";
    protected static final String FIND_MAINTENANCE_BY_ID = "SELECT * FROM maintenance WHERE maintenanceId = ?";
    protected static final String FIND_ALL_MAINTENANCE = "SELECT * FROM maintenance";

    protected static final String INSERT_MEETING = "INSERT INTO rendezvous(date) VALUES (?)";
    protected static final String DELETE_MEETING = "DELETE FROM rendezvous WHERE rendezvous_id = ?";
    protected static final String UPDATE_MEETING = "UPDATE rendezvous SET date = ? WHERE rendezvous_id = ?";
    protected static final String FIND_MEETING_BY_ID = "SELECT * FROM rendezvous WHERE rendezvous_id = ?";
    protected static final String FIND_ALL_MEETING_BY_DAY = "SELECT * FROM rendezvous WHERE date = ? ";
    protected static final String FIND_ALL_MEETING = "SELECT * FROM rendezvous";

    protected static final String INSERT_BOOKING = "INSERT INTO reservation(debutDate, finDate, status, reservationDate, vehiculeFK, rendezvousFK, utilisateurFK, paiementFK) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String DELETE_BOOKING = "DELETE FROM reservation WHERE reservationId = ?";
    protected static final String UPDATE_BOOKING = "UPDATE reservation SET debutDate = ?, finDate = ?, status = ?, reservationDate = ? WHERE reservationId = ?";
    protected static final String FIND_BOOKING_BY_ID = "SELECT * FROM reservation WHERE reservationId = ?";
    protected static final String FIND_ALL_BOOKING = "SELECT * FROM reservation";
    protected static final String FIND_BOOKING_BY_USER = "SELECT * FROM reservation WHERE utilisateurFK = ? ";

    protected static final String Global_Search_Parking = "SELECT * FROM parking WHERE name LIKE ? OR address LIKE ? ";
    protected static final String Global_Search_Car = "SELECT * FROM vehicule WHERE marque LIKE ? OR modele LIKE ? ";
    protected static final String Global_Search_User = "SELECT * FROM utilisateur WHERE companyName LIKE ? OR userLastName LIKE ? OR userFirstName LIKE ?";

}
