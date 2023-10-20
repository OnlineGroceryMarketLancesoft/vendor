package com.vendor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class vendorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int vendorId;
    @NotBlank (message = "name should not be null")
     String vendorName;
    @NotBlank (message = "Address should not be null")
    String vendorAddress;
	
	String vendorPhoneNo;
	
}
