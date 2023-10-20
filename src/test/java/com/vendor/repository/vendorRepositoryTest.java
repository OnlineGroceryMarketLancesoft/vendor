package com.vendor.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.vendor.entity.vendorModel;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class vendorRepositoryTest {
	
	@Autowired
	vendorRepository repository;
	vendorModel model;

	@BeforeEach
	void setUp() {
		
		model=new vendorModel(1, "pavan", "Hyd", "123456789");
		repository.save(model);
	}

@AfterEach
void tearDown() {
	model=null;
	repository.deleteAll();
}

	@Test
	void testFindByVendorName_Found() {
	vendorModel m=	repository.findByVendorName(model.getVendorName()).orElse(null);	
	assertEquals(model, m);	
	}
	
	@Test
	void testFindByVendorName_NotFound() {
		vendorModel f=repository.findByVendorName("sda").orElse(null);
		assertThat(f==null).isTrue();
	}

}
