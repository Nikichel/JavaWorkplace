package com.WorkplaceGarashchuk.WorkplaceOne.services;

public class CallService {
	private int synchronizedCount;
	private int asynchronizedCount;
	
    public CallService() {
        this.synchronizedCount = 0;
        this.asynchronizedCount=0;
    }
    
    public void setSynchronizedCount(int i) {
    	this.synchronizedCount=0;
    }
    
    public void setAsynchronizedCount(int i) {
    	this.asynchronizedCount=0;
    }

    public synchronized void synchronizedIncrement() {
        this.synchronizedCount++;
    }
    
    public void asynchronizedIncrement() {
        this.asynchronizedCount++;
    }

    public int getAsynchronizedCount(){
    	return this.asynchronizedCount;
    }
    
    public int getSynchronizedCount() {
    	return this.synchronizedCount;
    }
}
