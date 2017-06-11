package com.example.Transportation.web.rest;

import com.example.Transportation.TransportationApplication;
import com.example.Transportation.domain.Vehicle;
import com.example.Transportation.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Michael on 6/11/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleControllerTest {


    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    VehicleService vehicleService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    private EnhancedRandom enhancedRandom;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    public void showAllVehicles() throws Exception {
        int amountOfvehicles = 10;
        List<Vehicle> vehicles = enhancedRandom.randomListOf(amountOfvehicles,Vehicle.class);
        given(vehicleService.findAllVehicles()).willReturn(vehicles);
        MvcResult result = mockMvc.perform(get("/vehicle" )).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(result.getResponse().getContentAsString());
        assertNotNull(jsonArray);
        for (int i=0;i<jsonArray.length();i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            assertNotNull(jsonObject);
        }


    }

    @Test
    public void addVehicle() throws Exception {
    }

    @Test
    public void deleteVehicleByName() throws Exception {
    }

}