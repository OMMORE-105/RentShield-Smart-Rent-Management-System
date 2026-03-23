package com.ty.rentshield.service;

import java.util.List;

import com.ty.rentshield.Entity.Property;



public interface PropertyService {
	
	Property saveProperty(Long landlordId , Property property);

    Property getPropertyById(Long propertyId);

    List<Property> getAllProperties();

    Property updateProperty(Long propertyId, Property property);

    String deleteProperty(Long propertyId);
	
}
