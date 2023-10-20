package com.vendor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendor.entity.vendorModel;
@Repository
public interface vendorRepository extends JpaRepository<vendorModel, Integer>{

	Optional<vendorModel> findByVendorName(String vendorName);


	
}
