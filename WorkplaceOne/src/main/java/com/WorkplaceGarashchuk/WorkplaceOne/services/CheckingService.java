package com.WorkplaceGarashchuk.WorkplaceOne.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.triangleController.MainController;

import ch.qos.logback.classic.Logger;

@Service
public class CheckingService {
	private static final Logger logger=(Logger) LoggerFactory.getLogger(MainController.class);
	public Triangle typeTriangle(Triangle triangle) {
		if (isEquilateral(triangle))
			triangle.setProperty("равносторонний");
		else if (isIsosceles(triangle))
			triangle.setProperty("равнобедренный");
		else if (isRectangular(triangle))
			triangle.setProperty("прямоугольный");
		else
			triangle.setProperty("разносторонний");
		if(triangle.getFirstSide()==triangle.getSecondSide() && triangle.getSecondSide()==triangle.getThirdSide() && triangle.getThirdSide()==1 
				|| triangle.getProperty().length()==0) {
			logger.error("Непредвиденная ошибка");
			throw new NullPointerException("UnknownError");
		}
		float perimeter = triangle.getFirstSide()+ triangle.getSecondSide() + triangle.getThirdSide();
		perimeter/=2;
		triangle.setArea(Math.sqrt(perimeter * (perimeter - triangle.getFirstSide()) * (perimeter - triangle.getFirstSide()) * (perimeter - triangle.getFirstSide())));
		return triangle;
	}
	
	public List<Triangle> typeTriangleList(List<Triangle> triangles){
		triangles.forEach(triangle -> triangle = typeTriangle(triangle));
		return triangles;
	}

	public boolean isIsosceles(Triangle triangle) {
		if (triangle.getFirstSide() == triangle.getSecondSide() || triangle.getFirstSide() == triangle.getThirdSide()
				|| triangle.getSecondSide() == triangle.getThirdSide())
			return true;
		return false;
	}

	public boolean isEquilateral(Triangle triangle) {
		if (triangle.getFirstSide() == triangle.getSecondSide() && triangle.getFirstSide() == triangle.getThirdSide())
			return true;
		return false;
	}

	public boolean isRectangular(Triangle triangle) {
		if (Math.pow(triangle.getFirstSide(), 2) == Math.pow(triangle.getSecondSide(), 2) + Math.pow(triangle.getThirdSide(), 2)
				|| Math.pow(triangle.getSecondSide(), 2) == Math.pow(triangle.getFirstSide(), 2) + Math.pow(triangle.getThirdSide(), 2)
				|| Math.pow(triangle.getThirdSide(), 2) == Math.pow(triangle.getSecondSide(), 2) + Math.pow(triangle.getFirstSide(), 2)) {
			return true;
		}
		return false;
	}
}
