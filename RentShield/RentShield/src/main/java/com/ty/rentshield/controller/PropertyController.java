package com.ty.rentshield.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.rentshield.Entity.Property;
import com.ty.rentshield.response.ResponseStructure;
import com.ty.rentshield.service.PropertyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/properties")
@Validated
public class PropertyController {
	
	@Autowired
    private PropertyService propertyService;
	
	/////////////////////////////////////////  Add Property by Landlord  /////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/add/{landlordId}")
    public ResponseEntity<ResponseStructure<Property>> addProperty(@PathVariable Long landlordId, @RequestBody @Valid Property property) {

        Property savedProperty = propertyService.saveProperty(landlordId, property);

        ResponseStructure<Property> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Property Added Successfully");
        structure.setData(savedProperty);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }
	
	////////////////////////////////////// get Property by Id  /////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/find/{id}")
    public ResponseEntity<ResponseStructure<Property>> getPropertyById(@PathVariable Long id) {

        Property property = propertyService.getPropertyById(id);

        ResponseStructure<Property> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Property Found Successfully");
        structure.setData(property);

        return ResponseEntity.ok(structure);
    }
	
	////////////////////////////////////////////// get All Properties  ////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Property>>> getAllProperties() {

        List<Property> properties = propertyService.getAllProperties();

        ResponseStructure<List<Property>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("All Properties Retrieved Successfully");
        structure.setData(properties);

        return ResponseEntity.ok(structure);
    }
	
	//////////////////////////////////////////////////  Update Property  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Property>> updateProperty(@PathVariable Long id, @RequestBody @Valid Property property) {

        Property updatedProperty = propertyService.updateProperty(id, property);

        ResponseStructure<Property> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Property Updated Successfully");
        structure.setData(updatedProperty);

        return ResponseEntity.ok(structure);
    }
	
	//////////////////////////////////////////////////////////  Delete Property  ////////////////////////////////////////////////////////////////////////////////////////////////////
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProperty(
            @PathVariable Long id) {

        propertyService.deleteProperty(id);

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Property Deleted Successfully");
        structure.setData(null);

        return ResponseEntity.ok(structure);
    }
	
}
