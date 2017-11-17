package com.SEtrack.Hotel;

import com.SEtrack.Hotel.controllers.RoomController;
import com.SEtrack.Hotel.controllers.RoomSize;
import com.SEtrack.Hotel.controllers.RoomType;
import com.SEtrack.Hotel.models.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
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
		Room room = new Room(1, RoomType.Budget, RoomSize.FiveSixPerson, LocalDate.of(2017, 11, 16));
		Room room_ghost = new Room(1, RoomType.Budget, RoomSize.FiveSixPerson, LocalDate.of(2017, 11, 16));
		Room room3 = new Room(3, RoomType.Budget, RoomSize.FiveSixPerson, LocalDate.of(2017, 11, 16));
		Room update_room_1 = new Room(1, RoomType.Luxurious, RoomSize.OnePerson, LocalDate.of(2018, 11, 16));

		// Test the addition of the rooms
		roomController.addRoom(room);

		// Test:
		assertTrue(roomController.getRooms().size() == 1);

		// Test removal
		roomController.removeRoom(room);
		assertTrue(roomController.getRooms().size() == 0);

		// Remove something that does not exist
		roomController.removeRoom(room);
		assertTrue(roomController.getRooms().size() == 0);

		// Add room thrice
		roomController.addRoom(room);
		roomController.addRoom(room_ghost);
		roomController.addRoom(room);

		// Test:
		assertTrue(roomController.getRooms().size() == 1);

		// Update room
		roomController.updateRoom(update_room_1);
		assertTrue(roomController.getRooms().get(0) == update_room_1);

	}

}
