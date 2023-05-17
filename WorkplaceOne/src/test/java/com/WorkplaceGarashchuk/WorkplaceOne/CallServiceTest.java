package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.services.CallService;

public class CallServiceTest {
	  
	  @Test
	  public void testConcurrentSynchronizedIncrement() throws InterruptedException {
	    CallService service = new CallService();
	    Runnable task = () -> {		//лямбда-функция
	      for (int i = 0; i < 1000; i++) {
	        service.synchronizedIncrement();
	      }
	    };
	    Thread thread1 = new Thread(task);
	    Thread thread2 = new Thread(task);		//поток
	    thread1.start();
	    thread2.start();	//запуск потока
	    thread1.join();
	    thread2.join();		//ожидание конца потока(может выкинуть исключение)
	    assertEquals(2000, service.getSynchronizedCount());
	  }
	  
	  @Test
	  public void testConcurrentAsynchronizedIncrement() throws InterruptedException {
	    CallService service = new CallService();
	    Runnable task = () -> {		//лямбда-функция
	      for (int i = 0; i < 1000; i++) {
	        service.asynchronizedIncrement();
	      }
	    };
	    Thread thread1 = new Thread(task);
	    thread1.start();		
	    thread1.join();
	    assertEquals(1000, service.getAsynchronizedCount());
	  }
	}