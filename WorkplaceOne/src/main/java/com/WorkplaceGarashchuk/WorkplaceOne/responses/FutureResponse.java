package com.WorkplaceGarashchuk.WorkplaceOne.responses;

import com.WorkplaceGarashchuk.WorkplaceOne.entities.FutureId;

public class FutureResponse {
	private FutureId id;
	private String message;
	
	public FutureResponse() {
		id=null;
		message=null;
	}
	
	public FutureResponse(FutureId id, String message) {
		this.id=id;
		this.message=message;
	}
	
	public void setFutureId(FutureId id) {
		this.id=id;
	}
	
	public FutureId getFutureId() {
		return id;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
