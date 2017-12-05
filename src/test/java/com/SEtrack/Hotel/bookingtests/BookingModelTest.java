package com.SEtrack.Hotel.bookingtests;

import com.SEtrack.Hotel.models.Booking;
import com.SEtrack.Hotel.models.Guest;
import com.SEtrack.Hotel.models.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

/**
 * Tests the Booking Model
 * @author Daniel_de_Beste
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingModelTest {

    // Declaring the class objects
    private Booking booking;
    private Guest guest;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Initializes the class objects
     */
    @Before
    public void init(){
        booking = new Booking();
        guest = new Guest();
        room = new Room();
        startDate = LocalDate.of(2017,11,1);
        endDate = LocalDate.of(2017,11,7);
    }

    /**
     * Tests the guest object
     */
    @Test
    public void testGuest(){
        booking.setGuest(guest);
        Assert.assertEquals(guest, booking.getGuest());
    }

    /**
     * Tests the room object and checked-in state
     */
    @Test
    public void testRoom(){
        booking.setCheckIn(true);
        booking.setRoom(room);

        Assert.assertTrue(booking.isCheckIn());
        Assert.assertEquals(room, booking.getRoom());

    }

    /**
     * Tests the start and end date
     */
    @Test
    public void testDates(){
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        Assert.assertEquals(startDate, booking.getStartDate());
        Assert.assertEquals(endDate, booking.getEndDate());
    }



}
