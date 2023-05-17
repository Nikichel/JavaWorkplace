package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.historyMap.HistoryMap;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.GetResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.ValidatorError;

public class HistoryMapTest {
	
	  @Test
	  public void testAddResponse() {
		HistoryMap historyMap = new HistoryMap();
	    Triangle triangle = new Triangle(3, 4, 5);
	    GetResponse response = new GetResponse(triangle, new ValidatorError());
	    
	    historyMap.addResponse(triangle, response);
	    
	    assertTrue(historyMap.containsResponse(triangle));
	    assertEquals(response, historyMap.getResponseByTriagle(triangle));
	  }
	  
	  @Test
	  public void testAddResponseList() {
		HistoryMap historyMap = new HistoryMap();
	    List<GetResponse> responseList = new ArrayList<>();
	    Triangle triangle1 = new Triangle(3, 4, 5);
	    Triangle triangle2 = new Triangle(5, 5, 5);
	    GetResponse response1 = new GetResponse(triangle1, new ValidatorError());
	    GetResponse response2 = new GetResponse(triangle2, new ValidatorError());
	    responseList.add(response1);
	    responseList.add(response2);
	    
	    historyMap.addResponseList(responseList);
	    
	    assertTrue(historyMap.containsResponse(triangle1));
	    assertTrue(historyMap.containsResponse(triangle2));
	    assertEquals(response1, historyMap.getResponseByTriagle(triangle1));
	    assertEquals(response2, historyMap.getResponseByTriagle(triangle2));
	  }
	  
	  @Test
	  public void testSize() {
		HistoryMap historyMap = new HistoryMap();
	    Triangle triangle1 = new Triangle(3, 4, 5);
	    Triangle triangle2 = new Triangle(5, 5, 5);
	    GetResponse response1 = new GetResponse(triangle1, new ValidatorError());
	    GetResponse response2 = new GetResponse(triangle2, new ValidatorError());
	    
	    historyMap.addResponse(triangle1, response1);
	    historyMap.addResponse(triangle2, response2);
	    
	    assertEquals(2, historyMap.size());
	  }
	  
	  @Test
	  public void testGetAllResponses() {
		HistoryMap historyMap = new HistoryMap();
	    Triangle triangle1 = new Triangle(3, 4, 5);
	    Triangle triangle2 = new Triangle(5, 5, 5);
	    GetResponse response1 = new GetResponse(triangle1, new ValidatorError());
	    GetResponse response2 = new GetResponse(triangle2, new ValidatorError());
	    
	    historyMap.addResponse(triangle1, response1);
	    historyMap.addResponse(triangle2, response2);
	    
	    List<GetResponse> responses = historyMap.getAllResponses();
	    
	    assertEquals(2, responses.size());
	    assertTrue(responses.contains(response1));
	    assertTrue(responses.contains(response2));
	  }
	  
	  @Test
	  public void testCleanHistory() {
		HistoryMap historyMap = new HistoryMap();
	    Triangle triangle = new Triangle(3, 4, 5);
	    GetResponse response = new GetResponse(triangle, new ValidatorError());
	    
	    historyMap.addResponse(triangle, response);
	    historyMap.cleanHistory();
	    
	    assertFalse(historyMap.containsResponse(triangle));
	    assertEquals(0, historyMap.size());
	  }
}
