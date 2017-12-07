package com.SEtrack.Hotel;

import com.SEtrack.Hotel.controllers.RoomController;
import com.SEtrack.Hotel.models.RoomSize;
import com.SEtrack.Hotel.models.RoomType;
import com.SEtrack.Hotel.models.Room;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class HotelApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRoomController(){
		// Make new roomcontroller
		RoomController roomController = new RoomController();

		// Make new room
		Room room = new Room();

		// Test the addition of the rooms
		roomController.addRoom(room);

	}

}
