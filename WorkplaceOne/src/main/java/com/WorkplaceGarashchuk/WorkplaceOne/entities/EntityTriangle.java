package com.WorkplaceGarashchuk.WorkplaceOne.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="good_triangle_table")
public class EntityTriangle {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "future_id")
	Integer futureId;
	
	@Column(name = "first_side")
	float firstSide;
	
	@Column(name = "second_side")
	float secondSide;
	
	@Column(name = "third_side")
	float thirdSide;
	
	@Column(name = "property")
	String property;
	
	public EntityTriangle() {}

	public EntityTriangle(float firstSide, float secondSide, float thirdSide, String property) {
	   this.firstSide = firstSide;
	   this.secondSide = secondSide;
	   this.thirdSide = thirdSide;
	   this.property = property;
	}

	public Integer getId() {
	   return id;
	}

	public void setId(Integer id) {
	   this.id = id;
	}

	public Integer getFutureId() {
		return futureId;
	}
	
	public void setFutureId(Integer futureId){
		this.futureId=futureId;
	}
	public float getFirstSide() {
	   return firstSide;
	}

	public void setFirstSide(float firstSide) {
	   this.firstSide = firstSide;
	}

	public float getSecondSide() {
	   return secondSide;
	}

	public void setSecondSide(float secondSide) {
	   this.secondSide = secondSide;
	}

	public float getThirdSide() {
	   return thirdSide;
	}

	public void setThirdSide(float thirdSide) {
	   this.thirdSide = thirdSide;
	}

	public String getProperty() {
	   return property;
	}

	public void setProperty(String property) {
	   this.property = property;
	}
	
}
