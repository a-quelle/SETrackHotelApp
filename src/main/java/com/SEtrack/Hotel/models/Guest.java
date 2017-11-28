package com.SEtrack.Hotel.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Guest {

    //Definition of all instance variables
    //Generates automatically an unique id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NotNull
    private int guestNr;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastActiveDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    //Constructor

    public Guest() {

    }

    public Guest(int guestNr, String firstName, String lastName, String streetName, String zipCode, String city, String country,
                 int houseNumber, String phoneNumber, String emailAddress, LocalDate birthDate, long id ) {

        this.guestNr=guestNr;
        this.firstName=firstName;
        this.lastName=lastName;
        this.streetName=streetName;
        this.zipCode=zipCode;
        this.city=city;
        this.country=country;
        this.houseNumber=houseNumber;
        this.phoneNumber=phoneNumber;
        this.emailAddress =emailAddress;
        this.birthDate = birthDate;
        this.id = id;
    }

    //Getters and Setters for all variables

    public int getGuestNr() {
        return guestNr;
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

    public String getZipcode() {
        return zipCode;
    }

    public void setZipcode(String zipcode) {
        this.zipCode = zipcode;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGuestNr(int guestNr) {
        this.guestNr = guestNr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
