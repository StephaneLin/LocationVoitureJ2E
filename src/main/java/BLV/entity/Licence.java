package BLV.entity;

import java.sql.Date;

public class Licence {

    private int licenceId;
    private Date expireDate;
    private String name;
    private int type;
    private boolean validate;

    public Licence(int licenceId, Date expireDate, String name, int type, boolean validate) {
        this.licenceId = licenceId;
        this.expireDate = expireDate;
        this.name = name;
        this.type = type;
        this.validate = validate;
    }

    public Licence(Date dateExpiration, String nom, int type) {
        this.expireDate = dateExpiration;
        this.name = nom;
        this.type = type;
        this.validate = false;
    }

    public int getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(int licenceId) {
        this.licenceId = licenceId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

}
