package com.SEtrack.Hotel;


import com.SEtrack.Hotel.controllers.GuestController;
import com.SEtrack.Hotel.models.DocumentType;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.repositories.GuestRepositoryIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author kgriffio
 * Test for the guestController.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GuestControllerTest {

    @InjectMocks
    private GuestController guestController;

    @Mock
    private GuestRepositoryIn guestRepositoryIn;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();
    }

    @Test
    /**
     * Test the addition of a guest
     */
    public void addingGuestAPITest() throws Exception{
        // Make a guest
        Guest guest = getGuest();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(guest);

        when(guestRepositoryIn.save(Mockito.any(Guest.class))).thenReturn(guest);

        this.mockMvc.perform(post("/api/hotel/guests/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is((int)guest.getId())))
                .andExpect(jsonPath("$.firstName", is(guest.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(guest.getLastName())))
                .andExpect(jsonPath("$.streetName", is(guest.getStreetName())))
                .andExpect(jsonPath("$.zipCode", is(guest.getZipCode())))
                .andExpect(jsonPath("$.city", is(guest.getCity())))
                .andExpect(jsonPath("$.country", is(guest.getCountry())))
                .andExpect(jsonPath("$.emailAddress", is(guest.getEmailAddress())))
                .andExpect(jsonPath("$.houseNumber", is(guest.getHouseNumber())))
                .andExpect(jsonPath("$.phoneNumber", is(guest.getPhoneNumber())))
                .andExpect(status().isOk());
    }

    /**
     * Test the guest update function
     * @throws Exception
     */
    @Test
    public void updateGuestAPITest() throws Exception{
        // Make a guest
        Guest guest = getGuest();
        Guest guest_edit = getGuest();
        // Edit some values
        guest_edit.setFirstName("Update");
        guest_edit.setLastName("Name");
        guest_edit.setStreetName("anotherRandomStreet");
        // check validity
        assertTrue("Update".equals(guest_edit.getFirstName()));
        assertTrue("Name".equals(guest_edit.getLastName()));
        assertTrue("anotherRandomStreet".equals(guest_edit.getStreetName()));

        // Make json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(guest_edit);

        // Make the repository return the edited guest on save.
        when(guestRepositoryIn.save(Mockito.any(Guest.class))).thenReturn(guest_edit);
        // Return the original guest on findOne.
        when(guestRepositoryIn.findOne(Mockito.any(Long.class))).thenReturn(guest);

        // Send the new guest to the update function and perform some checks
        this.mockMvc.perform(put("/api/hotel/guests/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is((int)guest_edit.getId())))
                .andExpect(jsonPath("$.firstName", is(guest_edit.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(guest_edit.getLastName())))
                .andExpect(jsonPath("$.streetName", is(guest_edit.getStreetName())))
                .andExpect(status().isOk());

        // Make guest null for the negative case
        when(guestRepositoryIn.findOne(Mockito.any(Long.class))).thenReturn(null);
        this.mockMvc.perform(put("/api/hotel/guests/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Get All guests test.
     * @throws Exception
     */
    @Test
    public void getGuestAPITest() throws  Exception{

        Guest guest = getGuest();
        Guest guest_2 = getGuest();
        List<Guest> guestList = new ArrayList<Guest>();
        guestList.add(guest_2);
        guestList.add(guest);

        when(guestRepositoryIn.findAll()).thenReturn(guestList);

        // Perform get
        this.mockMvc.perform(get("/api/hotel/guests/all")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is((int)guest.getId())))
                .andExpect(jsonPath("$[0].firstName", is(guest.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(guest.getLastName())))
                .andExpect(jsonPath("$[0].streetName", is(guest.getStreetName())))
                .andExpect(jsonPath("$[0].zipCode", is(guest.getZipCode())))
                .andExpect(jsonPath("$[0].city", is(guest.getCity())))
                .andExpect(jsonPath("$[0].country", is(guest.getCountry())))
                .andExpect(jsonPath("$[0].emailAddress", is(guest.getEmailAddress())))
                .andExpect(jsonPath("$[0].houseNumber", is(guest.getHouseNumber())))
                .andExpect(jsonPath("$[0].phoneNumber", is(guest.getPhoneNumber())))
                .andExpect(status().isOk());
    }

    /**
     * Test the removal of a guest
     */
    @Test
    public void deleteGuestAPITest() throws Exception{

        Guest guest = getGuest();

        // Make json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(guest);

        when(guestRepositoryIn.findOne(Mockito.any(Long.class))).thenReturn(guest);

        // Test deleting an existing guest
        this.mockMvc.perform(delete("/api/hotel/guests/delete")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andExpect(status().isOk());

        when(guestRepositoryIn.findOne(Mockito.any(Long.class))).thenReturn(null);

        // Not existing case
        this.mockMvc.perform(delete("/api/hotel/guests/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());

        
    }

    private Guest getGuest(){
        return new Guest(0, "Koen", "Griffioen", "Randomstreet", "2811NZ", "blalba", "NL", 20, "123456", "jeMoeder@gmail.com", "2398KS23", DocumentType.DriversLicense);
    }

}
