package com.ty.rentshield.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.rentshield.Entity.Property;
import com.ty.rentshield.Entity.User;
import com.ty.rentshield.enumclass.Role;
import com.ty.rentshield.repository.PropertyRepository;
import com.ty.rentshield.repository.UserRepository;

@Service
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;
    
    /////////////////////////////// Add Property by Landlord  //////////////////////////////////////////
    @Override
    public Property saveProperty(Long landlordId, Property property) {

        User landlord = userRepository.findById(landlordId)
                .orElseThrow(() -> new RuntimeException("Landlord not found"));

        if (landlord.getRole() != Role.LANDLORD) {
            throw new RuntimeException("Only LANDLORD can add property");
        }

        property.setLandlord(landlord);

        return propertyRepository.save(property);
    }
    
    ////////////////////////////////////////////// get Property by Id ///////////////////////////////////////////////////
    @Override
    public Property getPropertyById(Long id) {

        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }
   

    //////////////////////////////////////////////  Get All Properties  //////////////////////////////////////////////////////////
    @Override
    public List<Property> getAllProperties() {

        return propertyRepository.findAll();
    }
    
    
    /////////////////////////////////////////////////  Update Property  //////////////////////////////////////////////////////////////
    @Override
    public Property updateProperty(Long id, Property updatedProperty) {

        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        existingProperty.setAddress(updatedProperty.getAddress());
        existingProperty.setRentAmount(updatedProperty.getRentAmount());
        existingProperty.setDueDate(updatedProperty.getDueDate());

        return propertyRepository.save(existingProperty);
    }
       
    ////////////////////////////////////////////////////  Delete Property  ////////////////////////////////////////////////////////////
    @Override
    public String deleteProperty(Long id) {

        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        propertyRepository.delete(property);

        return "Property Deleted Successfully";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    
    
    
}
