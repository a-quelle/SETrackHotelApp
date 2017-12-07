package com.SEtrack.Hotel.RoomTestPackage;

import com.SEtrack.Hotel.controllers.RoomController;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.RoomSize;
import com.SEtrack.Hotel.models.RoomType;
import com.SEtrack.Hotel.repositories.RoomRepositoryIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.binding.When;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
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
        this.newRoom = new Room();
        this.newRoom.setId(1);
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
                .andExpect(jsonPath("$.id", is((int)newRoom.getId())))
                .andExpect(jsonPath("$.roomNumber", is(newRoom.getRoomNumber())))
                .andExpect(jsonPath("$.roomSize", is(newRoom.getRoomSize().name())))
                .andExpect(jsonPath("$.roomType", is(newRoom.getRoomType().name())))
                .andExpect(status().isOk());
    }

    @Test
    public void gettingRoomAPITest() throws Exception{
        List<Room> rooms = new ArrayList<>();

        Room one = new Room(4L, "one", RoomType.Budget, RoomSize.FiveSixPerson);
        Room two = new Room(5L, "five", RoomType.Budget, RoomSize.FiveSixPerson);

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
                .andExpect(jsonPath("$.[0].roomNumber", is(newRoom.getRoomNumber())))
                .andExpect(jsonPath("$.roomSize", is(newRoom.getRoomSize().name())))
                .andExpect(jsonPath("$.roomType", is(newRoom.getRoomType().name())))
                .andExpect(status().isOk());
    }

    @Test
    public void deletingRoomAPITest () throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newRoom);

        when(roomRepositoryIn.delete(Mockito.any(Room.class)));

        this.mockMvc.perform(post("/api/hotel/room/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id", is((int)newRoom.getId())))
                .andExpect(jsonPath("$.roomNumber", is(newRoom.getRoomNumber())))
                .andExpect(jsonPath("$.roomSize", is(newRoom.getRoomSize().name())))
                .andExpect(jsonPath("$.roomType", is(newRoom.getRoomType().name())))
                .andExpect(status().isOk());
    }



}
