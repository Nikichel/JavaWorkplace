package com.WorkplaceGarashchuk.WorkplaceOne.jpaEntities;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WorkplaceGarashchuk.WorkplaceOne.entities.EntityTriangle;

public interface JpaTriangle extends JpaRepository<EntityTriangle, Integer> {
	
	 EntityTriangle findByFutureId(int futureId);
	
}
