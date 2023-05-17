package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;

public class TriangleTest {
	
	@Test
	public void setTriagleTest() {
		Triangle triangle=new Triangle();
		triangle.setFirstSide(1);
		triangle.setSecondSide(10);
		triangle.setThirdSide(15);
		
		assertEquals(triangle,new Triangle(1,10,15));
		
	}
}
