package com.SEtrack.Hotel.roomtests;

import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.RoomSize;
import com.SEtrack.Hotel.models.RoomType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomModelTests {

    private Room testRoom;

    @Before
    public void initialize(){
        testRoom = new Room();
    }

    @Test
    public void roomNumberTest(){

        String roomNumber = "testroom 1";

        testRoom.setRoomNumber(roomNumber);

        Assert.assertEquals(testRoom.getRoomNumber(), roomNumber);
    }

    @Test
    public void roomTypeTest(){

        RoomType roomType = RoomType.Normal;

        testRoom.setRoomType(roomType);

        Assert.assertEquals(testRoom.getRoomType(), roomType);
    }

    @Test
    public void roomSizeTest(){

        RoomSize roomSize = RoomSize.TwoPerson;

        testRoom.setRoomSize(roomSize);

        Assert.assertEquals(testRoom.getRoomSize(), roomSize);
    }
    
}
