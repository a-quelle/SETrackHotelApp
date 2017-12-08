package com.SEtrack.Hotel.guesttests;

import com.SEtrack.Hotel.models.DocumentType;
import com.SEtrack.Hotel.models.Guest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests the Guest Model
 * @author Daniel_de_Beste
 */
@SpringBootTest
public class GuestModelTest {

    // Create Guest object to use throughout the test
    private Guest guest;


    /**
     * Instantiates the Guest object
     */
    @Before
    public void init(){
        guest = new Guest();
    }


    /**
     * Tests the address details of a Guest
     */
    @Test
    public void testAddress() {
        // Initialize the test variables
        String firstname = "Donald";
        String lastname = "Trump";
        String streetname = "5th avenue";
        int houseNum = 15;
        String zipcode = "7695 HR";
        String city = "Compton";
        String country = "USA";

        // Set the variables
        guest.setFirstName(firstname);
        guest.setLastName(lastname);
        guest.setStreetName(streetname);
        guest.setHouseNumber(houseNum);
        guest.setZipCode(zipcode);
        guest.setCity(city);
        guest.setCountry(country);

        // Test the variables
        Assert.assertEquals(firstname, guest.getFirstName());
        Assert.assertEquals(lastname, guest.getLastName());
        Assert.assertEquals(streetname, guest.getStreetName());
        Assert.assertEquals(houseNum, guest.getHouseNumber());
        Assert.assertEquals(zipcode, guest.getZipCode());
        Assert.assertEquals(city, guest.getCity());
        Assert.assertEquals(country, guest.getCountry());

    }


    /**
     * Tests the contact info of a Guest
     */
    @Test
    public void testContactInfo(){
        // Initialize the test variables
        String docNum = "5PA1N";
        DocumentType docType = DocumentType.DriversLicense;
        String email = "test@gmail.com";
        String phonenr = "76983737457";

        // Set the variables
        guest.setDocumentNumber(docNum);
        guest.setDocumentType(docType);
        guest.setEmailAddress(email);
        guest.setPhoneNumber(phonenr);

        // Test the variables
        Assert.assertEquals(docNum, guest.getDocumentNumber());
        Assert.assertEquals(docType, guest.getDocumentType());
        Assert.assertEquals(email, guest.getEmailAddress());
        Assert.assertEquals(phonenr, guest.getPhoneNumber());
    }

}
