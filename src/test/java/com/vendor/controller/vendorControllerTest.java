package com.vendor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendor.entity.vendorModel;
import com.vendor.service.vendorService;
@SpringBootTest
@AutoConfigureMockMvc
class vendorControllerTest {
	
	
	@Autowired
    private MockMvc mockMvc;
	
    @InjectMocks
    private vendorController vendorController;

    @Mock
    private vendorService service;
	
//	@MockBean
//	vendorService service;
//	
//	vendorModel onemodel;
//	vendorModel twomodel;
//	List<vendorModel> vlist=new ArrayList<>();

//	@BeforeEach
//	void setUp() throws Exception {
//		
//		onemodel=new vendorModel(1, "pavan", "Hyd", "12313324");
//		twomodel=new vendorModel(2, "sudheer", "chennai", "2124124");
//		vlist.add(onemodel); 
//		vlist.add(twomodel);
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

//	@Test
//	void testAddVendor() {
//		fail("Not yet implemented");

    @Test
    void testAddVendor() throws Exception {
        // Create a vendorModel for testing
        vendorModel model = new vendorModel(1, "pavan1", "VendorAddress", "VendorPhoneNo");

        // Perform a POST request to the /addvendor endpoint with the vendorModel as JSON
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addvendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(model))) // Convert the model to JSON
                .andReturn();

        // Assert the HTTP status code
        assertEquals(201, result.getResponse().getStatus());
    }

    // Utility method to convert an object to JSON
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testGetVendor() throws Exception {
        int vendorId = 2;
        vendorModel vendorModel = new vendorModel(1, "pavan", "Hyd", "134134");

        // Set up the behavior of the vendorService mock
        when(service.getvendor(vendorId)).thenReturn(new ResponseEntity<>(vendorModel, HttpStatus.OK));

        // Initialize the MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();

        // Perform the GET request to /getvendor/{vendorId}
        mockMvc.perform(get("/getvendor/{vendorId}", vendorId))
                .andExpect(status().isOk()); // Expecting a 200 OK response
    }
    
    @Test
    public void testUpdateVendorSuccess() {
        // Create a sample vendor model for testing
        vendorModel vendorModel = new vendorModel(1, "pavan", "Hyd", "4321342");

        // Set up the behavior of the vendorService mock for a successful update
        when(service.updateVendor(vendorModel)).thenReturn("updated");

        // Call the updateVendor method and store the result
        String result = vendorController.updateVendor(vendorModel);

        // Assert that the result is as expected
        assertEquals("updated", result);
    }
    @Test
    public void testGetAllVendorsSuccess() throws Exception {
    	
    	List<vendorModel> vlist=Arrays.asList(
    			new vendorModel(1, "pavan", "Hyd", "21445324"),
                 new vendorModel(2, "kumar", "chennai", "34232323"),
                 new vendorModel(3, "kamal", "delhi", "23423255")
    			);
    	when(service.getAllVendors()).thenReturn(vlist);
    	this.mockMvc.perform(get("/getallvendors")).andDo(print()).andExpect(status().isOk());
    }
	@Test
	public void testDeleteVendorSuccess() {
		
		int vid=1;
		
		when(service.deleteVendor(vid)).thenReturn("deleted");
		
		String result=vendorController.deleteVendorId(vid);
		
		 assertEquals("deleted", result);
		
		
		
	}
}
