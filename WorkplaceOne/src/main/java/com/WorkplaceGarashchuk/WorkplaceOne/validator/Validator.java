package com.WorkplaceGarashchuk.WorkplaceOne.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.triangleController.MainController;

import ch.qos.logback.classic.Logger;

@Component
public class Validator{
	private static final Logger logger=(Logger) LoggerFactory.getLogger(MainController.class);
	public ValidatorError validateTriagle(Triangle triangle) {
		
		ValidatorError errors=new ValidatorError();
		if (triangle.getFirstSide() <= 0) {
			logger.error("Неккоретная 1-ая сторона");
			errors.setError("Треугольник не может существовать");
			errors.addExplanation("1-ая сторона меньше либо равна 0");
		}
		if (triangle.getSecondSide() <= 0) {
			logger.error("Неккоретная 2-ая сторона");
			errors.setError("Треугольник не может существовать");
			errors.addExplanation("2-ая сторона меньше либо равна 0");
		}
		if (triangle.getThirdSide() <= 0) {
			logger.error("Неккоретная 3-ая сторона");
			errors.setError("Треугольник не может существовать");
			errors.addExplanation("3-ая сторона меньше либо равна 0");
		}
		if(triangle.getFirstSide() + triangle.getSecondSide() <= triangle.getThirdSide()) {
			logger.error("Неккоретны стороны");
			errors.setError("Треугольник не может существовать");
			errors.addExplanation(triangle.getFirstSide()+ " + " + triangle.getSecondSide() +" <= " + triangle.getThirdSide());
		}
		if(triangle.getThirdSide() + triangle.getSecondSide() <= triangle.getFirstSide()) {
			logger.error("Неккоретны стороны");
			errors.setError("Треугольник не может существовать");
			errors.addExplanation(triangle.getThirdSide()+ " + " + triangle.getSecondSide() +" <= " + triangle.getFirstSide());
		}
		if(triangle.getThirdSide() + triangle.getFirstSide() <= triangle.getSecondSide()) {
			logger.error("Неккоретны стороны");
			errors.setError("Треугольник не может существовать");
			errors.addExplanation(triangle.getThirdSide() + " + " + triangle.getFirstSide() +" <= " + triangle.getSecondSide());
		}
				
		return errors;
	}
	
	public List<ValidatorError> validateListTriagles(List<Triangle> triangles) {
		List<ValidatorError> errorsList = new ArrayList<>();
		triangles.forEach(Triangle -> errorsList.add(validateTriagle(Triangle)));
		return errorsList;
	}
}