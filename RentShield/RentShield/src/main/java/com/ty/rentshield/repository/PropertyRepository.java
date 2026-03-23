package com.ty.rentshield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.rentshield.Entity.Property;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
