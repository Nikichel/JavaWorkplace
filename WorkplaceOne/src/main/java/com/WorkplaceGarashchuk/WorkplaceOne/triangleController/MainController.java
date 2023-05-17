package com.WorkplaceGarashchuk.WorkplaceOne.triangleController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.WorkplaceGarashchuk.WorkplaceOne.entities.EntityTriangle;
import com.WorkplaceGarashchuk.WorkplaceOne.entities.FutureId;
import com.WorkplaceGarashchuk.WorkplaceOne.historyMap.HistoryMap;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.FutureResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.GetResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.PostResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.services.CallService;
import com.WorkplaceGarashchuk.WorkplaceOne.services.CheckingService;
import com.WorkplaceGarashchuk.WorkplaceOne.services.DatabaseService;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.Validator;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.ValidatorError;

import ch.qos.logback.classic.Logger;
@RestController

@RequestMapping("api/answer")
@ResponseStatus (HttpStatus.OK)
public class MainController {
	private static final Logger logger=(Logger) LoggerFactory.getLogger(MainController.class);
	private CallService count = new CallService();
	private HistoryMap history;
	private CheckingService checkService;
	private Validator validator;
	private DatabaseService dataBase;
	
	@Autowired
	public MainController(final CheckingService checkService, Validator validator, HistoryMap history, DatabaseService dataBase) {
		this.checkService=checkService;
		this.validator = validator;
		this.history=history;
		this.dataBase=dataBase;
	}

	@GetMapping("/isTriangle")
	public ResponseEntity<GetResponse> isTriangle(@RequestParam float firstSide, @RequestParam float secondSide, @RequestParam float thirdSide) {
		logger.error("Обработка треугольника");
		count.asynchronizedIncrement();
		count.synchronizedIncrement();
		Triangle triangle = new Triangle(firstSide, secondSide, thirdSide);
		if(history.containsResponse(triangle))
			return new ResponseEntity<>(history.getResponseByTriagle(triangle), HttpStatus.OK);
		
		ValidatorError errors = validator.validateTriagle(triangle);
		
		GetResponse response=new GetResponse(triangle,errors);
		
		if(response.getErrors().getError().length() != 0) {
			logger.error("Параметры некорректны");
			response.getErrors().setStatus(HttpStatus.BAD_REQUEST.name());
			history.addResponse(triangle,response);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			triangle=checkService.typeTriangle(triangle);
			response.getErrors().setStatus(HttpStatus.OK.name());
			response.setTriagnle(triangle);
		}catch(NullPointerException except) {
			response.getErrors().setError("Internal Server Error occured");
			response.getErrors().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			response.setTriagnle(triangle);
			logger.error("Непредвиденная ошибка");
			history.addResponse(triangle,response);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		history.addResponse(triangle,response);
		dataBase.saveTriangle(response.getTriangle());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/historyResponses")
	public List<GetResponse> historyResponses(){
		return history.getAllResponses();
	}
	
	@GetMapping("/historySize")
	public int historySize(){
		return history.size();
	}
	
	@GetMapping("/calls")
	public CallService calls(){
		return count;
	}
	
	@GetMapping("/resetCalls")
	public CallService resetCalls() {
		count.setSynchronizedCount(0);
		count.setAsynchronizedCount(0);
		return count;
	}
	
	@PostMapping("/areTriangles")
	@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<PostResponse> responceAllTriagle(@RequestBody List<Triangle> triangles) {
		List<GetResponse> responseList = new ArrayList<>();
		final List<Triangle> triangles_fun = new ArrayList<>();
		List<Triangle> goodTriangles = new ArrayList<>();
		List<ValidatorError> errorsList = validator.validateListTriagles(triangles);
		
		errorsList.stream().forEach(error -> { int index = errorsList.indexOf(error);
	        if(error.getError().length() == 0) {
	        	triangles_fun.add(triangles.get(index));
	        } else {
	            error.setStatus(HttpStatus.BAD_REQUEST.name());
	            responseList.add(new GetResponse(triangles.get(index), error));
	        }
	    });
		
		goodTriangles = checkService.typeTriangleList(triangles_fun);
		
		goodTriangles.forEach(triangle -> responseList.add(new GetResponse(triangle,new ValidatorError())));
		
		Triangle max = Collections.max(goodTriangles, Comparator.comparing(t ->t.getArea()));
		Triangle min = Collections.min(goodTriangles, Comparator.comparing(t -> t.getArea()));
		double averageArea = goodTriangles.stream().mapToDouble(triangle -> {
		        	return triangle.getArea();
		        	}).average().orElse(Double.NaN);
		
		Triangle average = Collections.min(goodTriangles, Comparator.comparing(t -> Math.abs(t.getArea() - averageArea))); 
		
		history.addResponseList(responseList);
		dataBase.saveTriangles(goodTriangles);
		
		return ResponseEntity.status(201).body(new PostResponse(max,min,average, responseList));
	}
	
	@GetMapping("/getFromDatabase")
	public List<EntityTriangle> getFromDatabase() {
		return dataBase.getTriangles();
	}
	
	@GetMapping("/futureIsTriangle")
	public FutureResponse futureIsTriangle(@RequestParam float firstSide, @RequestParam float secondSide, @RequestParam float thirdSide) {
		logger.info("Старт future");
		Triangle triangle = new Triangle(firstSide, secondSide, thirdSide);
		
		ValidatorError errors = validator.validateTriagle(triangle);
		
		if(errors.getError().length() != 0) {
			logger.info("Треугольник не прошёл валидацию");
			return new FutureResponse(null, "Triangle failed validation");
		}
		FutureId id = new FutureId();
		dataBase.saveFutureId(id);
		
		CompletableFuture.runAsync(new Runnable() {
		      @Override
		      public void run(){
		      logger.info("Запустилась обработка треугольника");
		      Triangle triangleFuture=checkService.typeTriangle(triangle);
		      dataBase.saveTriangle(triangleFuture);
		      logger.info("Треугольник обработан и добавлен в БД");
		      }
		    }
		);
		logger.info("Конец future");
		return new FutureResponse(id, "Triangle will be added to the database soon");
	}
	
	@GetMapping("/findByFutureId")
	public EntityTriangle findById(@RequestParam int id) {
		return dataBase.findByFutureId(id);
	}
}