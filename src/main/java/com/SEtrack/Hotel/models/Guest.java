package com.SEtrack.Hotel.models;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author kgriffio and others
 * Guest model. Simple POJO.
 */


@Entity
public class Guest {


    /**
     * This instantiates a new booking
     * @param id guest identification number
     * @param firstName of the guest
     * @param lastName of the guest
     * @param streetName for the guest's residency
     * @param zipCode for the guest's residency
     * @param city of residence
     * @param country of residence
     * @param houseNumber of the guest's house
     * @param phoneNumber of the guest
     * @param emailAddress of the guest
     * @param documentNumber of the guest's identity document
     * @param documentType of the guest's identity document
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NotNull
    private String firstName, lastName;
    @NotNull
    private String streetName, zipCode, city, country;
    @NotNull
    private int houseNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String emailAddress;
    private String documentNumber;
    private DocumentType documentType;


    /**
     *
     * @return Full name, so first and last name
     */
    public String getFullName(){
        return firstName + " " + lastName;
    }

    //Getters and Setters for all variables

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
