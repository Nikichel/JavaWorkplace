package com.WorkplaceGarashchuk.WorkplaceOne.responses;

import java.util.Objects;

import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.ValidatorError;

public class GetResponse {
	Triangle triangle;
	ValidatorError errors;
	
	public GetResponse() {
		this.triangle=new Triangle();
		this.errors=new ValidatorError();
	}
	
	public GetResponse(Triangle triangle, ValidatorError errors) {
		this.triangle=triangle;
		this.errors=errors;
	}
	
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    GetResponse that = (GetResponse) o;
	    return Objects.equals(triangle, that.triangle) &&
	            Objects.equals(errors, that.errors);
	}
	
	public void setTriagnle(Triangle triangle) {
		this.triangle=triangle;
	}
	
	public void setErrors(ValidatorError errors) {
		this.errors=errors;
	}
	
	public Triangle getTriangle() {
		return triangle;
	}
	
	public ValidatorError getErrors() {
		return errors;
	}
}
