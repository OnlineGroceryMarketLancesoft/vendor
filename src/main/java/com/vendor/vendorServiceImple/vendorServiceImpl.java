package com.vendor.vendorServiceImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vendor.controller.vendorController;
import com.vendor.entity.vendorModel;
import com.vendor.exception.vendorExceptionHandler;
import com.vendor.exception.vendorNotFound;
import com.vendor.repository.vendorRepository;
import com.vendor.service.vendorService;

@Service
public class vendorServiceImpl implements vendorService{
    private static final Logger logger = LoggerFactory.getLogger(vendorController.class);

	@Autowired
	vendorRepository vendorRepository;
	
	@Override
	public ResponseEntity<String> createVendor(vendorModel model) {
		
	vendorModel data = vendorRepository.findByVendorName(model.getVendorName()).orElse(null);
     
       if(data==null)
       {
   		vendorRepository.save(model);
		
		 String successMessage = "Vendor created successfully.";
		    return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
       }else {
    	   return new ResponseEntity<>("this user already there,please try different one",HttpStatus.BAD_REQUEST);
       }
	}

	@Override
	public String updateVendor(vendorModel model) {
     
	vendorModel vendorobj=	vendorRepository.findById(model.getVendorId()).get();
	
	vendorobj.setVendorAddress(model.getVendorAddress());
	vendorobj.setVendorPhoneNo(model.getVendorPhoneNo());
		vendorRepository.save(vendorobj);
		
		return "updated";
	}

	public String deleteVendor(int vendorId) {
      
	vendorModel model=	vendorRepository.findById(vendorId).orElse(null);
	if(model!=null)
	{
		vendorRepository.delete(model);
	     return "deleted";}
	else {
		return "data is not present";}
	}

	@Override
	public ResponseEntity<vendorModel> getvendor(int vendorId) {
	    vendorModel vendorModel = vendorRepository.findById(vendorId).orElse(null);
	    
	    if (vendorModel != null) {
	        return new ResponseEntity<>(vendorModel, HttpStatus.OK);
	    } else {
	        throw new vendorNotFound("User not found", HttpStatus.NOT_FOUND);
	    }
	}

	@Override
	public List<vendorModel> getAllVendors() {
		logger.info("data successfully retrieved");

		return vendorRepository.findAll();
	}



}
