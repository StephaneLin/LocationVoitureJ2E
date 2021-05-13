package BLV.entity;

import BLV.tools.PasswordTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;

public class User {

    private int userId;
    private int entitee;
    private Date connexionDate;
    private String companyName;
    private int companyPhone;
    private int siret;
    private String userLastName;
    private String userFirstName;
    private int userPhone;
    private int userYearOld;
    private String email;
    private String password;
    private int accessRightsFK;
    private int paymentCardFK;
    private byte[] licenceFK;

    public User(int userId, int entitee, Date connexionDate, String companyName, int companyPhone, int siret, String userLastName, String userFirstName, int userPhone, int userYearOld, String email, String password, int accessRightsFK, int paymentCardFK, byte[] licenceFK) {
        this.userId = userId;
        this.entitee = entitee;
        this.connexionDate = connexionDate;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.siret = siret;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userPhone = userPhone;
        this.userYearOld = userYearOld;
        this.email = email;
        this.password = password;
        this.accessRightsFK = accessRightsFK;
        this.paymentCardFK = paymentCardFK;
        this.licenceFK = licenceFK;
    }

    public User(int userId, int entitee, Date connexionDate, String companyName, int companyPhone, int siret, String userLastName, String userFirstName, int userPhone, int userYearOld, String email, int accessRightsFK, int paymentCardFK, byte[] licenceFK) {
        this.userId = userId;
        this.entitee = entitee;
        this.connexionDate = connexionDate;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.siret = siret;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userPhone = userPhone;
        this.userYearOld = userYearOld;
        this.email = email;
        this.accessRightsFK = accessRightsFK;
        this.paymentCardFK = paymentCardFK;
        this.licenceFK = licenceFK;
    }

    public User(String nameInput, String firstNameInput, String emailInput, String passwordInput, byte[] license) {
        this.userLastName = nameInput;
        this.userFirstName = firstNameInput;
        this.email = emailInput;
        this.password = PasswordTools.generatePassword(passwordInput);
        this.licenceFK = license;
    }

    public User(String nameInput, String firstNameInput, String emailInput, String passwordInput, byte[] license, int userPhone, int accessRightsFK) {
        this.userLastName = nameInput;
        this.userFirstName = firstNameInput;
        this.email = emailInput;
        this.password = PasswordTools.generatePassword(passwordInput);
        this.licenceFK = license;
        this.userPhone = userPhone;
        this.accessRightsFK = accessRightsFK;
    }

    public User(String companyName, int siret, int companyPhone, String emailInput, String passwordInput) {
        this.companyName = companyName;
        this.siret = siret;
        this.email = emailInput;
        this.password = PasswordTools.generatePassword(passwordInput);
        this.companyPhone = companyPhone;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public void setPasswordWithoutEncoding(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEntitee() {
        return entitee;
    }

    public void setEntitee(int entitee) {
        this.entitee = entitee;
    }

    public Date getConnexionDate() {
        return connexionDate;
    }

    public void setConnexionDate(Date connexionDate) {
        this.connexionDate = connexionDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(int companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSiret() {
        return siret;
    }

    public void setSiret(int siret) {
        this.siret = siret;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserYearOld() {
        return userYearOld;
    }

    public void setUserYearOld(int userYearOld) {
        this.userYearOld = userYearOld;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordTools.generatePassword(password);
    }

    public int getAccessRightsFK() {
        return accessRightsFK;
    }

    public void setAccessRightsFK(int accessRightsFK) {
        this.accessRightsFK = accessRightsFK;
    }

    public int getPaymentCardFK() {
        return paymentCardFK;
    }

    public void setPaymentCardFK(int paymentCardFK) {
        this.paymentCardFK = paymentCardFK;
    }

    public byte[] getLicenceFK() {
        return licenceFK;
    }

    public void setLicenceFK(byte[] licenceFK) {
        this.licenceFK = licenceFK;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", entitee=" + entitee +
                ", connexionDate=" + connexionDate +
                ", companyName='" + companyName + '\'' +
                ", companyPhone=" + companyPhone +
                ", siret=" + siret +
                ", userLastName='" + userLastName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userPhone=" + userPhone +
                ", userYearOld=" + userYearOld +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accessRightsFK=" + accessRightsFK +
                ", paymentCardFK=" + paymentCardFK +
                ", licenceFK=" + licenceFK +
                '}';
    }
}
