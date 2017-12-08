package com.SEtrack.Hotel.bookingtests;

import com.SEtrack.Hotel.controllers.MaintenanceController;
import com.SEtrack.Hotel.models.Room;
import com.SEtrack.Hotel.models.RoomSize;
import com.SEtrack.Hotel.models.RoomType;
import com.SEtrack.Hotel.models.bookable.Maintenance;
import com.SEtrack.Hotel.repositories.bookable.MaintenanceRepository;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class represents the maintenance controller tests
 * @author cgilbers
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MaintenanceControllerTests {

    @InjectMocks
    private MaintenanceController maintenanceController;

    @Mock
    private MaintenanceRepository maintenanceRepository;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(maintenanceController).build();
    }

    /**
     * Test of the add api endpoint
     * @throws Exception
     */
    @Test
    public void addMaintenanceTest() throws Exception{

        Maintenance maintenance = new Maintenance();
        maintenance.setRoom(new Room());
        maintenance.setStartDate(LocalDate.parse("2017-01-01"));
        maintenance.setEndDate(LocalDate.parse("2017-01-02"));
        maintenance.setReason("Hoi");

        ObjectMapper mapper = new ObjectMapper();

        // set object mapper to correctly serialize dates
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = mapper.writeValueAsString(maintenance);

        System.out.println(json);

        when(maintenanceRepository.save(Mockito.any(Maintenance.class))).thenReturn(maintenance);

        this.mockMvc.perform(post("/api/hotel/maintenance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(maintenance.getId()))
                .andExpect(jsonPath("$.startDate").value(maintenance.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(maintenance.getEndDate().toString()))
                .andExpect(jsonPath("$.room.id").value(maintenance.getRoom().getId()))
                .andExpect(jsonPath("$.reason").value(maintenance.getReason()))
                .andExpect(jsonPath("$.message").value(maintenance.getMessage()))
                .andExpect(status().isOk());
    }

    /**
     * Test of the findall api endpoint
     * @throws Exception
     */
    @Test
    public void allMaintenanceTest() throws Exception{
        List<Maintenance> list = new ArrayList<>();
        list.add(new Maintenance(new Room(), LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-02"), "reason", "message"));
        list.add(new Maintenance(new Room(), LocalDate.parse("2017-02-01"), LocalDate.parse("2017-02-01"), "reason2", "message2"));

        when(maintenanceRepository.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/api/hotel/maintenance/all"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(list.get(0).getId()))
                .andExpect(jsonPath("$.[0].room.id").value(list.get(0).getRoom().getId()))
                .andExpect(jsonPath("$.[0].startDate").value(list.get(0).getStartDate().toString()))
                .andExpect(jsonPath("$.[0].endDate").value(list.get(0).getEndDate().toString()))
                .andExpect(jsonPath("$.[0].reason").value(list.get(0).getReason()))
                .andExpect(jsonPath("$.[0].message").value(list.get(0).getMessage()))
                .andExpect(jsonPath("$.[1].id").value(list.get(1).getId()))
                .andExpect(jsonPath("$.[1].room.id").value(list.get(1).getRoom().getId()))
                .andExpect(jsonPath("$.[1].startDate").value(list.get(1).getStartDate().toString()))
                .andExpect(jsonPath("$.[1].endDate").value(list.get(1).getEndDate().toString()))
                .andExpect(jsonPath("$.[1].reason").value(list.get(1).getReason()))
                .andExpect(jsonPath("$.[1].message").value(list.get(1).getMessage()))
                .andExpect(status().isOk());
    }
}
