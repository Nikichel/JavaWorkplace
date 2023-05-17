package com.WorkplaceGarashchuk.WorkplaceOne.triangle;

import java.util.Objects;

public class Triangle {
	float firstSide;
	float secondSide;
	float thirdSide;
	double area;
	String property;

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Triangle guest = (Triangle) obj;
		return this.firstSide == guest.firstSide && this.secondSide == guest.secondSide
				&& this.thirdSide == guest.thirdSide && this.property.equals(guest.property) && this.area == guest.area;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(firstSide, secondSide, thirdSide);
	}
	
	public Triangle() {
		this.firstSide = this.secondSide = this.thirdSide = 0;
		this.property = "";
	}

	public Triangle(float firstSide, float secondSide, float thirdSide) {
		this.firstSide = firstSide;
		this.secondSide = secondSide;
		this.thirdSide = thirdSide;
		this.property = "";
	}

	public Triangle(float firstSide, float secondSide, float thirdSide, String property) {
		this.firstSide = firstSide;
		this.secondSide = secondSide;
		this.thirdSide = thirdSide;
		this.property = property;
	}
	
	public double getArea() {
		return this.area;
	}

	public float getFirstSide() {
		return this.firstSide;
	}

	public float getSecondSide() {
		return this.secondSide;
	}

	public float getThirdSide() {
		return this.thirdSide;
	}

	public String getProperty() {
		return this.property;
	}
	
	public void setArea(double area) {
		this.area=area;
	}
	
	public void setFirstSide(float side) {
		this.firstSide = side;
	}

	public void setSecondSide(float side) {
		this.secondSide = side;
	}

	public void setThirdSide(float side) {
		this.thirdSide = side;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
