package com.vendor.vendorServiceImple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vendor.entity.vendorModel;
import com.vendor.exception.vendorNotFound;
import com.vendor.repository.vendorRepository;
@ExtendWith(MockitoExtension.class)
class vendorServiceImplTest {
	
	@Mock
    private	vendorRepository repository;
	@InjectMocks
	private vendorServiceImpl service;
	AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable=MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testCreateVendor() {
	     vendorModel model = new vendorModel(1, "pavan", "Hyd", "12312433");

	        when(repository.findByVendorName(model.getVendorName())).thenReturn(java.util.Optional.empty());
	        
	        ResponseEntity<String> response = service.createVendor(model);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Vendor created successfully.", response.getBody());
	        verify(repository, times(1)).findByVendorName(model.getVendorName());
	        verify(repository, times(1)).save(model);
	}
	
	
	  @Test
	    void testCreateVendorFailure() {
	        vendorModel model = new vendorModel(1, "pavan", "Hyd", "12312433");

	        when(repository.findByVendorName(model.getVendorName())).thenReturn(java.util.Optional.of(model));

	        ResponseEntity<String> response = service.createVendor(model);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	       assertEquals("this user already there,please try different one", response.getBody());
	        verify(repository, times(1)).findByVendorName(model.getVendorName());
	        verify(repository, never()).save(Mockito.any());

	     
	    }

	  @Test
	    void testUpdateVendor() {
	        // Create a vendorModel for testing
	        vendorModel model = new vendorModel(1, "VendorName", "VendorAddress", "VendorPhoneNo");
	        vendorModel model2 = new vendorModel(1, "VendorName", "Hyd", "23452535");

	        // Mock the findById method to return a vendorModel when called with model.getVendorId()
	        when(repository.findById(model2.getVendorId())).thenReturn(java.util.Optional.of(model));

	        // Call the updateVendor method
	        String result = service.updateVendor(model2);

	        // Verify that the save method is called with the updated vendorModel
	        verify(repository, times(1)).save(model2);

	        // Verify that the result is as expected
	        assertEquals("updated", result);
	    }
	  @Test
	    void testDeleteVendorSuccess() {
	        int vendorId = 1;

	        // Mock the findById method to return a vendorModel when called with vendorId
	        vendorModel model = new vendorModel(vendorId, "VendorName", "VendorAddress", "VendorPhoneNo");
	        when(repository.findById(vendorId)).thenReturn(java.util.Optional.of(model));

	        // Call the deleteVendor method
	        String result = service.deleteVendor(vendorId);

	        // Verify that the delete method is called with the vendorModel
	        verify(repository, times(1)).delete(model);

	        // Verify that the result is "deleted"
	        assertEquals("deleted", result);
	    }

	    @Test
	    void testDeleteVendorNotFound() {
	        int vendorId = 1;

	        // Mock the findById method to return null when called with vendorId
	        when(repository.findById(vendorId)).thenReturn(java.util.Optional.empty());

	        // Call the deleteVendor method
	        String result = service.deleteVendor(vendorId);

	        // Verify that the delete method is not called
	        verify(repository, never()).delete(Mockito.any());

	        // Verify that the result is "data is not present"
	        assertEquals("data is not present", result);
	    }
	@Test
	void testGetvendor() {
		vendorModel v=new vendorModel(1, "pavan", "Hyd", "1324343");
		
		int vendorId=1;
		   // Mock the findById method to return the vendorModel when called with vendorId
        when(repository.findById(vendorId)).thenReturn(java.util.Optional.of(v));

        // Call the getvendor method
        ResponseEntity<vendorModel> response = service.getvendor(vendorId);

        // Verify that the response is a ResponseEntity with HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the vendorModel in the response matches the expected model
        assertEquals(v, response.getBody());	
	}

    @Test
    void testGetVendorNotFound() {
        int vendorId = 1;

        // Mock the findById method to return an empty Optional when called with vendorId
        when(repository.findById(vendorId)).thenReturn(java.util.Optional.empty());

        // Call the getvendor method and expect an exception
        vendorNotFound exception = assertThrows(vendorNotFound.class, () -> service.getvendor(vendorId));

        // Verify that the exception message matches "User not found" and HttpStatus.NOT_FOUND
        assertEquals("User not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

	@Test
	void testGetAllVendors() {

		List<vendorModel> vlist=new ArrayList<>();
		vlist.add(new vendorModel(1, "pavan", "Hyd", "34313441"));
		vlist.add(new vendorModel(2, "sudheer", "chennai", "342354354"));
        // Mock the findAll method to return the list of vendorModels
        when(repository.findAll()).thenReturn(vlist);

        // Call the getAllVendors method
        List<vendorModel> result = service.getAllVendors();

        // Verify that the result list matches the expected list
        assertEquals(vlist, result);
	}

}
