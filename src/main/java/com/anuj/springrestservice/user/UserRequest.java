package com.anuj.springrestservice.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel //Adding this annotation for Swagger
public class UserRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Size(min=2, message = "must have first name and last name")  // This is javax validation
	@ApiModelProperty(notes="Name should have atleast 2 characters") // this is for Swagger
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date dob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
}
