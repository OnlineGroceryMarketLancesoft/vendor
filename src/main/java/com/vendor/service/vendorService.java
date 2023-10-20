package com.vendor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vendor.entity.vendorModel;

public interface vendorService {
	
	public ResponseEntity<String> createVendor(vendorModel model);
	public String updateVendor(vendorModel model);
	public String deleteVendor(int vendorId);
	public ResponseEntity<vendorModel> getvendor(int vendorId);
	List<vendorModel> getAllVendors();

}
