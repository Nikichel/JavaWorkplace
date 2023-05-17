package com.WorkplaceGarashchuk.WorkplaceOne.historyMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.WorkplaceGarashchuk.WorkplaceOne.responses.GetResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;

@Component
public class HistoryMap {
	
	private Map<Triangle,GetResponse> historyMap;
	
	public HistoryMap(){
		historyMap=new HashMap<Triangle,GetResponse>();
	}
	
	public void addResponse(Triangle triangle,GetResponse response) {
		if(!historyMap.containsKey(triangle))
			historyMap.put(triangle, response);
	}
	
	public void addResponseList(List<GetResponse> responseList) {
		responseList.forEach(MyResponse -> addResponse(MyResponse.getTriangle(),MyResponse));
	}
	
	public int size() {
		return historyMap.size();
	}
	
	public List<GetResponse> getAllResponses() {
		List<GetResponse> responses = new ArrayList<GetResponse>();
		historyMap.forEach((k,v) -> responses.add(v));
		return responses;
	}
	
	public void cleanHistory() {
		historyMap.clear();
	}

	public boolean containsResponse(Triangle triangle) {
		return historyMap.containsKey(triangle);
	}
	
	public GetResponse getResponseByTriagle(Triangle triangle) {
		return historyMap.get(triangle);
	}
}
