package com.WorkplaceGarashchuk.WorkplaceOne.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorError {
	private String error;
	private List<String> explanation = new ArrayList<String>();
	private String status;
	
	public ValidatorError() {
		error="";
		status="";
	}
	
	public ValidatorError(String error) {
		this.error=error;
		status="";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		ValidatorError guest = (ValidatorError) obj;
		return this.error == guest.error && this.explanation == guest.explanation && this.status == guest.status;
	}
	
	public void addExplanation(String newExplanation) {
		explanation.add(newExplanation);
	}
	
	public void setStatus(String status) {
		this.status=status;
	}
	
	public void setError(String error) {
		this.error=error;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public List<String> getExplanation(){
		return this.explanation;
	}
	
	public String getError() {
		return this.error;
	}

}