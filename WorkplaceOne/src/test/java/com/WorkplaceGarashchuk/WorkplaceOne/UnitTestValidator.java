package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.Validator;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.ValidatorError;

public class UnitTestValidator {

	@Test
	public void testValidator() {
		Triangle triangleOne=new Triangle(0,-6,-1);
		Triangle triangleSecond=new Triangle(1,6,3);
		
		Validator valid = new Validator();
		
		ValidatorError response = valid.validateTriagle(triangleOne);
		assertEquals("Треугольник не может существовать",response.getError());
		
		response = valid.validateTriagle(triangleSecond);
		assertEquals("Треугольник не может существовать",response.getError());
	}
}
