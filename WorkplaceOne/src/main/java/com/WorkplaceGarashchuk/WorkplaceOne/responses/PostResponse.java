package com.WorkplaceGarashchuk.WorkplaceOne.responses;

import java.util.List;

import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;

public class PostResponse {
	
	private Triangle maxTriangle;
	private Triangle minTriangle;
	private Triangle averageTriangle;
	private List<GetResponse> responses;
	
	public PostResponse() {}

    public PostResponse(Triangle maxTriangle, Triangle minTriangle, Triangle averageTriangle, List<GetResponse> responses) {
        this.maxTriangle = maxTriangle;
        this.minTriangle = minTriangle;
        this.averageTriangle = averageTriangle;
        this.responses = responses;
    }

    public Triangle getMaxTriangle() {
        return maxTriangle;
    }

    public void setMaxTriangle(Triangle maxTriangle) {
        this.maxTriangle = maxTriangle;
    }

    public Triangle getMinTriangle() {
        return minTriangle;
    }

    public void setMinTriangle(Triangle minTriangle) {
        this.minTriangle = minTriangle;
    }

    public Triangle getAverageTriangle() {
        return averageTriangle;
    }

    public void setAverageTriangle(Triangle averageTriangle) {
        this.averageTriangle = averageTriangle;
    }

    public List<GetResponse> getTriangles() {
        return responses;
    }

    public void setTriangles(List<GetResponse> responses) {
        this.responses = responses;
    }
}
