package com.SEtrack.Hotel.roomtests;

import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.RoomSize;
import com.SEtrack.Hotel.models.RoomType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for the getters and setters for the room model
 * @author cgilbers
 */
@SpringBootTest
public class RoomModelTests {

    private Room testRoom;

    /**
     * Create a new room object for each test
     */
    @Before
    public void initialize(){
        testRoom = new Room();
    }

    /**
     * Checks if the get returns the value specified in the set
     */
    @Test
    public void roomNumberTest(){

        String roomNumber = "testroom 1";

        testRoom.setRoomNumber(roomNumber);

        Assert.assertEquals(testRoom.getRoomNumber(), roomNumber);
    }

    /**
     * Checks if the get returns the value specified in the set
     */
    @Test
    public void roomTypeTest(){

        RoomType roomType = RoomType.Normal;

        testRoom.setRoomType(roomType);

        Assert.assertEquals(testRoom.getRoomType(), roomType);
    }

    /**
     * Checks if the get returns the value specified in the set
     */
    @Test
    public void roomSizeTest(){

        RoomSize roomSize = RoomSize.TwoPerson;

        testRoom.setRoomSize(roomSize);

        Assert.assertEquals(testRoom.getRoomSize(), roomSize);
    }

}
