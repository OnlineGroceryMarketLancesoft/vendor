package com.vendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.entity.vendorModel;
import com.vendor.service.vendorService;

import jakarta.validation.Valid;



@RestController
public class vendorController {
	

	@Autowired
	vendorService vendorService;

	@PostMapping("/addvendor")
	public ResponseEntity<String> addVendor(@RequestBody @Valid vendorModel model)
	{
		return vendorService.createVendor(model);
	}
	@GetMapping("/getvendor/{vendorId}")
	public ResponseEntity<vendorModel> getvendor(@PathVariable int vendorId)
	{
		//
		return vendorService.getvendor(vendorId);
	}
	@PutMapping("/updatevendor")
	public String updateVendor(@RequestBody vendorModel model)
	{
		return vendorService.updateVendor(model);
	}
	@GetMapping("/getallvendors")
	public List<vendorModel> getAllVendors()
	{

		return vendorService.getAllVendors();
	}
	@DeleteMapping("/deletevendor/{vendorId}")
	public  String deleteVendorId(@PathVariable int vendorId)
	{
		return vendorService.deleteVendor(vendorId);
	}
	
	
	
}
