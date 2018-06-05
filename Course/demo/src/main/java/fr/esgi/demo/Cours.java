package fr.esgi.demo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Bug : when using Date for files, there is no valueof method for java.sql.date, 
 * so Spark is not happy...
 * @author formation
 */
public class Cours implements Serializable {

    /**
     * isin : code of the share
     */
    private String isin;
    /**
     * date for the cours. 
     * We store it as a string, because encoders are stupid and cannot make
     * data from sql.dates. 
     */
    private String date;
    /**
     * opening value
     */
    private double ouverture;
    /**
     * lowest value
     */
    private double plushaut;
    /**
     * highest value
     */
    private double plusbas;
    /**
     * closing value
     */
    private double cloture;
    /**
     * volume intraday
     */
    private double volume;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOuverture() {
        return ouverture;
    }

    public void setOuverture(double ouverture) {
        this.ouverture = ouverture;
    }

    public double getPlushaut() {
        return plushaut;
    }

    public void setPlushaut(double plushaut) {
        this.plushaut = plushaut;
    }

    public double getPlusbas() {
        return plusbas;
    }

    public void setPlusbas(double plusbas) {
        this.plusbas = plusbas;
    }

    public double getCloture() {
        return cloture;
    }

    public void setCloture(double cloture) {
        this.cloture = cloture;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
