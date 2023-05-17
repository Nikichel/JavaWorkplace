package com.WorkplaceGarashchuk.WorkplaceOne;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.services.CheckingService;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;

public class UnitTestService {
	private CheckingService service = new CheckingService();
	
	@Test
	public void testEquilateral(){
		Triangle triangleOne=new Triangle(6,6,6);
		triangleOne=service.typeTriangle(triangleOne);
		assertEquals("равносторонний", triangleOne.getProperty());
	}
	
	@Test
	public void testIsosceles(){
		Triangle triangleSecond=new Triangle(3,6,6);
		triangleSecond=service.typeTriangle(triangleSecond);
		assertEquals("равнобедренный", triangleSecond.getProperty());
	}
	
	@Test
	public void testRectangular(){
		Triangle triangleThird=new Triangle(3,4,5);
		triangleThird=service.typeTriangle(triangleThird);
		assertEquals("прямоугольный", triangleThird.getProperty());
	}
	
	@Test
	public void testException() throws UnknownError{
		Triangle triangleFourth = new Triangle(1,1,1);
		Throwable thrown = assertThrows(NullPointerException.class,() ->{
				service.typeTriangle(triangleFourth);
		});
		assertNotNull(thrown.getMessage());
	}
	
	@Test
	public void testOther(){
		Triangle triangleFourth = new Triangle(3,6,5);
		triangleFourth=service.typeTriangle(triangleFourth);
		assertEquals("разносторонний", triangleFourth.getProperty());
	}
}

