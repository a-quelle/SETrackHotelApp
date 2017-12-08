package com.SEtrack.Hotel.bookingtests;

import com.SEtrack.Hotel.controllers.BookingController;
import com.SEtrack.Hotel.controllers.GuestController;
import com.SEtrack.Hotel.controllers.RoomController;
import com.SEtrack.Hotel.models.bookable.Booking;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.repositories.GuestRepositoryIn;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
import com.SEtrack.Hotel.repositories.bookable.BookingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingControllerTest {


    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private RoomRepositoryIn roomRepositoryIn;
    @Mock
    private GuestRepositoryIn guestRepositoryIn;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }


    @Test
    public void getAll(){

    }


    @Test
    public void addBookingTest() throws Exception {

        LocalDate startDate = LocalDate.of(2014, Month.APRIL,10);
        LocalDate endDate = LocalDate.of(2014,Month.MAY,10);

        Booking booking = new Booking();
        Guest guest_1 = new Guest();
        Room room_1 = new Room();

        booking.setRoom(room_1);
        booking.setGuest(guest_1);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        ObjectMapper objectMapper = new ObjectMapper();

        // set object mapper to correctly serialize dates
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(booking);
        System.out.println(json);

        when(guestRepositoryIn.findOne(any())).thenReturn(guest_1);
        when(roomRepositoryIn.findOne(any())).thenReturn(room_1);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        this.mockMvc.perform(post("/api/hotel/booking/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(booking.getId()))
                .andExpect(jsonPath("$.guest.id").value(booking.getGuest().getId()))
                .andExpect(jsonPath("$.room.id").value(booking.getRoom().getId()))
                .andExpect(jsonPath("$.startDate").value(booking.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(booking.getEndDate().toString()))
                .andExpect(jsonPath("$.checkIn").value(booking.isCheckIn()))
                .andExpect(status().isOk());

    }

    @Test
    public void updateBooking(){

    }
}
