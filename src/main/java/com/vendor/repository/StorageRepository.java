package com.vendor.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendor.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}