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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    public void getAllBookingTest() throws Exception{

        List<Booking> bookings = new ArrayList<>();


        LocalDate startDate = LocalDate.of(2014, Month.APRIL,10);
        LocalDate endDate = LocalDate.of(2014,Month.MAY,10);

        Booking booking_1 = new Booking();
        Booking booking_2 = new Booking();
        Guest guest_1 = new Guest();
        Room room_1 = new Room();

        booking_1.setRoom(room_1);
        booking_1.setGuest(guest_1);
        booking_1.setStartDate(startDate);
        booking_1.setEndDate(endDate);
        bookings.add(booking_1);

        booking_2.setRoom(room_1);
        booking_2.setGuest(guest_1);
        booking_2.setStartDate(startDate.plusDays(2));
        booking_2.setEndDate(endDate.plusDays(2));
        bookings.add(booking_2);

        when(bookingRepository.findAll()).thenReturn(bookings);

        this.mockMvc.perform(get("/api/hotel/booking/all"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(bookings.get(0).getId()))
                .andExpect(jsonPath("$.[0].guest.id").value(bookings.get(0).getGuest().getId()))
                .andExpect(jsonPath("$.[0].room.id").value(bookings.get(0).getRoom().getId()))
                .andExpect(jsonPath("$.[0].startDate").value(bookings.get(0).getStartDate().toString()))
                .andExpect(jsonPath("$.[0].endDate").value(bookings.get(0).getEndDate().toString()))
                .andExpect(jsonPath("$.[0].checkIn").value(bookings.get(0).isCheckIn()))
                .andExpect(jsonPath("$.[1].id").value(bookings.get(1).getId()))
                .andExpect(jsonPath("$.[1].guest.id").value(bookings.get(1).getGuest().getId()))
                .andExpect(jsonPath("$.[1].room.id").value(bookings.get(1).getRoom().getId()))
                .andExpect(jsonPath("$.[1].startDate").value(bookings.get(1).getStartDate().toString()))
                .andExpect(jsonPath("$.[1].endDate").value(bookings.get(1).getEndDate().toString()))
                .andExpect(jsonPath("$.[1].checkIn").value(bookings.get(1).isCheckIn()))
                .andExpect(status().isOk());

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
    public void updateBookingTest() throws Exception{
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

        when(bookingRepository.findOne(any())).thenReturn(booking);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        this.mockMvc.perform(put("/api/hotel/booking/update")
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
    public void deleteBookingTest(){}
}
