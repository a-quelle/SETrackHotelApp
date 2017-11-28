package com.SEtrack.Hotel.models;


/**
 * @author kgriffio and others
 * Guest model. Simple POJO.
 */

public class Guest {

    //Definition of all instance variables
    private int guestNr;
    private String firstName, lastName;
    private String streetName, zipCode, city, country;
    private int houseNumber;
    private String phoneNumber;
    private String emailAddress;
    private DocumentType documentType;
    private String documentNumber;

    /**
     * Constructor for Spring
     */
    //Constructor
    public Guest() {

    }

    /**
     * Guest Constructor
     * @param guestNr
     * @param firstName
     * @param lastName
     * @param streetName
     * @param zipCode
     * @param city
     * @param country
     * @param houseNumber
     * @param phoneNumber
     * @param emailAddress
     * @param documentType
     * @param documentNumber
     */
    public Guest(int guestNr, String firstName, String lastName, String streetName, String zipCode, String city, String country, int houseNumber, String phoneNumber, String emailAddress, DocumentType documentType, String documentNumber) {
        this.guestNr = guestNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    /**
     *
     * @return Full name, so first and last name
     */
    public String getFullName(){
        return firstName + " " + lastName;
    }

    //Getters and Setters for all variables
    public int getGuestNr() {
        return guestNr;
    }

    public void setGuestNr(int guestNr) {
        this.guestNr = guestNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
