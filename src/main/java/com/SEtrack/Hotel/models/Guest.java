package com.SEtrack.Hotel.models;


import java.time.LocalDate;

public class Guest {

    //Definition of all instance variables
    private int guestNr;
    private String firstName, lastName;
    private String streetName, zipCode, city, country;
    private int houseNumber;
    private String phoneNumber;
    private String emailAddress;
    private String lastActiveDate;
    private String birthDate;

    //Constructor
    public Guest(int guestNr, String firstName, String lastName, String streetName, String zipCode, String city, String country,
                 int houseNumber, String phoneNumber, String emailAddress, String birthDate) {
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
    }

    public Guest(){

    }

    //Getters and Setters for all variables


    public String getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(String lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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
}
