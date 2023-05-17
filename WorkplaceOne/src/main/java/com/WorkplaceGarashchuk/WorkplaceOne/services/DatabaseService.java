package com.WorkplaceGarashchuk.WorkplaceOne.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkplaceGarashchuk.WorkplaceOne.entities.EntityTriangle;
import com.WorkplaceGarashchuk.WorkplaceOne.entities.FutureId;
import com.WorkplaceGarashchuk.WorkplaceOne.jpaEntities.JpaTriangle;
import com.WorkplaceGarashchuk.WorkplaceOne.jpaEntities.jpaFutureId;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;


@Service
public class DatabaseService {
	
	@Autowired
	private JpaTriangle repositoryTriangle;
	
	@Autowired
	private jpaFutureId repositoryFutureId;
	
	public void saveTriangle(Triangle triangle) {
		EntityTriangle entity = new EntityTriangle (triangle.getFirstSide(), triangle.getSecondSide(), triangle.getThirdSide(), triangle.getProperty());
		List<FutureId> futureIds = repositoryFutureId.findAll();
		FutureId max = Collections.max(futureIds, Comparator.comparing(t ->t.getId()));
		entity.setFutureId(max.getId());
		repositoryTriangle.save(entity);
	}
	
	public void saveTriangles(List<Triangle> triangles) {
		triangles.forEach(t -> saveTriangle(t));
	}
	
	public List<EntityTriangle> getTriangles() {
		return repositoryTriangle.findAll();
	}
	
	public EntityTriangle findByFutureId(int id) {
	    return repositoryTriangle.findByFutureId(id);
	}
	
	public void saveFutureId(FutureId id) {
		repositoryFutureId.save(id);
	}
}
