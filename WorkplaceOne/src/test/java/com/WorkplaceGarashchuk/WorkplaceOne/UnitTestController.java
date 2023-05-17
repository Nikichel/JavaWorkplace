package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import com.WorkplaceGarashchuk.WorkplaceOne.historyMap.HistoryMap;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.GetResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.PostResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.services.CheckingService;
import com.WorkplaceGarashchuk.WorkplaceOne.services.DatabaseService;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.triangleController.MainController;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.Validator;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.ValidatorError;


public class UnitTestController {

	@Mock
	private HistoryMap history = mock(HistoryMap.class);
	@Mock
	private CheckingService service = mock(CheckingService.class);
	@Mock
	private Validator valid = mock(Validator.class);
	@Mock
	private DatabaseService database = mock(DatabaseService.class);
	@InjectMocks
	private MainController controller = new MainController(service, valid, history,database);

	@Test
	public void goodTestController() {
		Triangle triangle = new Triangle(3, 4, 5);
		Triangle triangleAnswer = new Triangle(3, 4, 5, "прямоугольный");
		when(valid.validateTriagle(triangle)).thenReturn(new ValidatorError());
		when(service.typeTriangle(triangle)).thenReturn(triangleAnswer);
		ResponseEntity<GetResponse> result = controller.isTriangle(3, 4, 5);
		GetResponse response = (GetResponse) result.getBody();

		assertEquals("прямоугольный", response.getTriangle().getProperty());
		assertEquals(new Triangle(3, 4, 5, "прямоугольный"), response.getTriangle());
	}

	  @Test 
	  public void exceptionTestController() { 
		  Triangle triangle = new Triangle(1,1,1); 
		  when(valid.validateTriagle(triangle)).thenReturn(new ValidatorError());
		  when(service.typeTriangle(triangle)).thenThrow(new NullPointerException("UnknownError")); 
		  ResponseEntity<GetResponse> result = controller.isTriangle(1,1,1);
		  GetResponse response = (GetResponse) result.getBody();
		  
		  doNothing().when(history).addResponse(triangle, response);
		  assertEquals("Internal Server Error occured", response.getErrors().getError());
		  assertEquals("INTERNAL_SERVER_ERROR", response.getErrors().getStatus());
	  }
	  
	  @Test 
	  public void validTestController() { 
		  Triangle triangle = new Triangle(-3,4,5); 
		  ValidatorError error = new ValidatorError();
		  error.setError("Треугольник не может существовать");
		  when(valid.validateTriagle(triangle)).thenReturn(error);
		  ResponseEntity<GetResponse> result = controller.isTriangle(-3,4,5);
		  GetResponse response = (GetResponse) result.getBody();
		  
		  assertEquals("Треугольник не может существовать", response.getErrors().getError());
		  verify(service, never()).typeTriangle(triangle);
	  }
	  
	  @Test 
	  public void getHistoryResponsesTest() { 
		  List<GetResponse> responses=new ArrayList<GetResponse>();
		  when(history.getAllResponses()).thenReturn(responses);
		  responses=controller.historyResponses(); 
		  
		  assertEquals(0,responses.size()); 
	  }
	  
	  @Test 
	  public void getSizeHistoryTest() { 
		  when(history.size()).thenReturn(0);
		  int response = controller.historySize(); 
		  
		  assertEquals(0, response); 
	  }
	  
	  @Test 
	  public void resetCountTest() {
		  Triangle triangle = new Triangle(3, 4, 5);
		  Triangle triangleAnswer = new Triangle(3, 4, 5, "прямоугольный");
		  when(valid.validateTriagle(triangle)).thenReturn(new ValidatorError());
		  when(service.typeTriangle(triangle)).thenReturn(triangleAnswer);
		  for(int i=0;i<10;i++) {
			  controller.isTriangle(3, 4, 5);
		  }

		  assertEquals(10, controller.calls().getSynchronizedCount());
		  assertEquals(10, controller.calls().getAsynchronizedCount());
		  
		  controller.resetCalls();
		  assertEquals(0, controller.calls().getSynchronizedCount());
		  assertEquals(0, controller.calls().getAsynchronizedCount());
	  }
	  
	  @Test
	  public void responceAllTriagleTest() {
		  List<Triangle> triangles = new ArrayList<>();
		  triangles.add(new Triangle(3, 4, 5, "Прямоугольный"));
		  triangles.add(new Triangle(5, -5, 5, ""));
		  triangles.add(new Triangle(4, 4, 6, "Равнобедренный"));
		  
		  List<Triangle> goodTriangles = new ArrayList<>();
		  goodTriangles.add(new Triangle(3, 4, 5, "Прямоугольный"));
		  goodTriangles.add(new Triangle(4, 4, 6, "Равнобедренный"));
		  
		  List<ValidatorError> errorsList = new ArrayList<>();
		  errorsList.add(new ValidatorError());
		  errorsList.add(new ValidatorError("Ошибка валидации"));
		  errorsList.add(new ValidatorError());
		  
		  when(valid.validateListTriagles(triangles)).thenReturn(errorsList);
		  when(service.typeTriangleList(goodTriangles)).thenReturn(goodTriangles);
		  ResponseEntity<PostResponse> result = controller.responceAllTriagle(triangles);
		  assertEquals(3, result.getBody().getTriangles().size());
	  }
}
