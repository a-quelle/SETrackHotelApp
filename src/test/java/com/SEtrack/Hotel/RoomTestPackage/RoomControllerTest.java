package com.SEtrack.Hotel.RoomTestPackage;

import com.SEtrack.Hotel.controllers.RoomController;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.RoomSize;
import com.SEtrack.Hotel.models.RoomType;
import com.SEtrack.Hotel.models.bookable.Booking;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
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

import java.util.ArrayList;
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

    @Mock
    private RoomRepositoryIn roomRepositoryIn;

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
    public void

}
