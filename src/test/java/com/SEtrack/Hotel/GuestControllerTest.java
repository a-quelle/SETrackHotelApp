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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        Guest guest = new Guest(0, "Koen", "Griffioen", "Randomstreet", "2811NZ", "blalba", "NL", 20, "123456", "jeMoeder@gmail.com", "2398KS23", DocumentType.DriversLicense);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(guest);

        when(guestRepositoryIn.save(Mockito.any(Guest.class))).thenReturn(guest);

        this.mockMvc.perform(post("/api/hotel/guests/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(guest.getId().intValue())))
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

    @Test
    public void updateGuestAPITest() throws Exception{
        // Make a guest
        Guest guest = new Guest(0, "Koen", "Griffioen", "Randomstreet", "2811NZ", "blalba", "NL", 20, "123456", "jeMoeder@gmail.com", "2398KS23", DocumentType.DriversLicense);
        Guest guest_edit = new Guest(0, "Update", "Name", "anotherRandomStreet", "2811NZ", "blalba", "NL", 20, "123456", "jeMoeder@gmail.com", "2398KS23", DocumentType.DriversLicense);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(guest_edit);

        // Make the repository return the edited guest on save.
        when(guestRepositoryIn.save(Mockito.any(Guest.class))).thenReturn(guest_edit);
        // Return the original guest on findOne.
        when(guestRepositoryIn.findOne(Mockito.any(Long.class))).thenReturn(guest);

        this.mockMvc.perform(put("/api/hotel/guests/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(guest_edit.getId().intValue())))
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

}
