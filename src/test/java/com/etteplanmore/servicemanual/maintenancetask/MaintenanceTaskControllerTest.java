package com.etteplanmore.servicemanual.maintenancetask;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MaintenanceTaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMaintenanceTasks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/maintenancetasks/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void getMaintenanceTaskNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/maintenancetasks/9999").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
    @Test
    public void createMaintenanceTask() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.post("maintenancetasks?deviceId=1&criticality=critical&desc=rikki").accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());
    }
    
    @Test
    public void updateMaintenanceTask() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.put("maintenancetasks/9999").accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());
    }
    
    @Test
    public void deleteMaintenanceTask() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.put("maintenancetasks/9999?compeleted=true").accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());
    }
    
    @Test
    public void getMaintenanceTaskByDevice() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.put("maintenancetasks?deviceId=1").accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());
    }
    
}