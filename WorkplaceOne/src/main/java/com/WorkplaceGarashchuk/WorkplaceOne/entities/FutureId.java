package com.WorkplaceGarashchuk.WorkplaceOne.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="future_id_table")
public class FutureId {
	
	@Id
	@Column(name = "future_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}
}
