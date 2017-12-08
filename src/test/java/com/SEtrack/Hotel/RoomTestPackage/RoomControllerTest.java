package com.SEtrack.Hotel.RoomTestPackage;

import com.SEtrack.Hotel.controllers.BookableController;
import com.SEtrack.Hotel.controllers.BookingController;
import com.SEtrack.Hotel.controllers.GuestController;
import com.SEtrack.Hotel.controllers.RoomController;
import com.SEtrack.Hotel.models.*;
import com.SEtrack.Hotel.models.bookable.Bookable;
import com.SEtrack.Hotel.models.bookable.Booking;
import com.SEtrack.Hotel.repositories.GuestRepositoryIn;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
import com.SEtrack.Hotel.repositories.bookable.BookableRepository;
import com.SEtrack.Hotel.repositories.bookable.BookingRepository;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomControllerTest {

    Room newRoom;

    @InjectMocks
    public RoomController roomController;
    @InjectMocks
    public BookableController bookableController;

    @Mock
    private RoomRepositoryIn roomRepositoryIn;
    @Mock
    private BookableRepository bookableRepository;



    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
        this.newRoom = new Room( "room1", RoomType.Budget, RoomSize.FiveSixPerson);
        this.newRoom.setRoomNumber("123blabla");
        this.newRoom.setRoomSize(RoomSize.FiveSixPerson);
        this.newRoom.setRoomType(RoomType.Budget);
    }

    @Test
    public void addingRoomAPITest() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newRoom);

        when(roomRepositoryIn.save(Mockito.any(Room.class))).thenReturn(newRoom);

        this.mockMvc.perform(post("/api/hotel/room/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(newRoom.getId()))
                .andExpect(jsonPath("$.roomNumber", is(newRoom.getRoomNumber())))
                .andExpect(jsonPath("$.roomSize", is(newRoom.getRoomSize().name())))
                .andExpect(jsonPath("$.roomType", is(newRoom.getRoomType().name())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void findAllRoomAPITest() throws Exception{
        List<Room> rooms = new ArrayList<>();

        Room one = new Room( "one", RoomType.Budget, RoomSize.FiveSixPerson);
        Room two = new Room( "five", RoomType.Budget, RoomSize.FiveSixPerson);

        rooms.add(one);
        rooms.add(two);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newRoom);

        //Mock of the List
        when(roomRepositoryIn.findAll()).thenReturn(rooms);

        this.mockMvc.perform(get("/api/hotel/room/all"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id", is((int)rooms.get(0).getId())))
                .andExpect(jsonPath("$.[0].roomNumber", is(rooms.get(0).getRoomNumber())))
                .andExpect(jsonPath("$.[0].roomSize", is(rooms.get(0).getRoomSize().name())))
                .andExpect(jsonPath("$.[0].roomType", is(rooms.get(0).getRoomType().name())))

                .andExpect(jsonPath("$.[1].id", is((int)rooms.get(1).getId())))
                .andExpect(jsonPath("$.[1].roomNumber", is(rooms.get(1).getRoomNumber())))
                .andExpect(jsonPath("$.[1].roomSize", is(rooms.get(1).getRoomSize().name())))
                .andExpect(jsonPath("$.[1].roomType", is(rooms.get(1).getRoomType().name())))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRoomApiTest() throws  Exception {
        Room one = new Room( "one", RoomType.Budget, RoomSize.FiveSixPerson);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(one.getId());

        this.mockMvc.perform(delete("/api/hotel/room/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRoomApiTest() throws Exception {
        Room one = new Room( "one", RoomType.Budget, RoomSize.FiveSixPerson);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(one);
        System.out.println(json);

        when(roomRepositoryIn.findOne(any())).thenReturn(one);
        when(roomRepositoryIn.save(Mockito.any(Room.class))).thenReturn(one);

        this.mockMvc.perform(put("/api/hotel/room/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.roomNumber", is(one.getRoomNumber())))
                .andExpect(jsonPath("$.roomType", is(one.getRoomType().toString())))
                .andExpect(jsonPath("$.roomSize", is(one.getRoomSize().toString())))
                .andExpect(status().isOk());

    }

    @Test
    public void getAvailableRoomsApiTest() throws Exception {

        Guest guest = new Guest(1, "firstName", "lastName", "streetName", "zipCode", "city", "country", 1, "phoneNumber", "emailAddress", "documentNumber", DocumentType.Passport);

        Room room1 = new Room( "room1", RoomType.Budget, RoomSize.FiveSixPerson);
        Room room2 = new Room( "room2", RoomType.Budget, RoomSize.FiveSixPerson);
        Room room3 = new Room( "room3", RoomType.Budget, RoomSize.FiveSixPerson);
        Room room4 = new Room( "room4", RoomType.Budget, RoomSize.FiveSixPerson);

        Bookable booking1= new Booking();
        Bookable booking2= new Booking();

        DateInterval interval = new DateInterval();
        interval.setStartDate(LocalDate.of(2001, Month.JANUARY,1));
        interval.setEndDate(LocalDate.of(2001, Month.DECEMBER,31));

        ((Booking)booking1).setGuest(guest);
        booking1.setRoom(room2);
        booking1.setStartDate(LocalDate.of(2000, Month.JANUARY,1));
        booking1.setEndDate(LocalDate.of(2000, Month.DECEMBER,31));
        ((Booking)booking2).setGuest(guest);
        booking2.setRoom(room3);
        booking2.setStartDate(LocalDate.of(2001, Month.JANUARY,1));
        booking2.setEndDate(LocalDate.of(2001, Month.DECEMBER,31));

        when(roomRepositoryIn.findAll()).thenReturn(new ArrayList<Room>(Arrays.asList(new Room[]{room1,room2,room3,room4})));
        when(bookableRepository.findAll()).thenReturn(new ArrayList<Bookable>(Arrays.asList(new Bookable[]{booking1,booking2})));

        ObjectMapper objectMapper = new ObjectMapper();

        // set object mapper to correctly serialize dates
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(interval);

        this.mockMvc.perform(post("/api/hotel/room/available")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(status().isOk());

    }

}
