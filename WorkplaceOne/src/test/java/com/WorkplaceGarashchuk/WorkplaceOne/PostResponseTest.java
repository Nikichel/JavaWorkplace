package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.responses.GetResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.responses.PostResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;

public class PostResponseTest {
	@Test
	public void testPostResponseConstructor() {
	    PostResponse postResponse = new PostResponse();
	    assertNotNull(postResponse);
	}

	@Test
	public void testPostResponseConstructorWithParams() {
	    Triangle maxTriangle = new Triangle(3, 4, 5);
	    Triangle minTriangle = new Triangle(1, 1, 1);
	    Triangle averageTriangle = new Triangle(2, 3, 4);
	    List<GetResponse> responses = new ArrayList<>();
	    responses.add(new GetResponse());
	    PostResponse postResponse = new PostResponse(maxTriangle, minTriangle, averageTriangle, responses);
	    assertNotNull(postResponse);
	    assertEquals(maxTriangle, postResponse.getMaxTriangle());
	    assertEquals(minTriangle, postResponse.getMinTriangle());
	    assertEquals(averageTriangle, postResponse.getAverageTriangle());
	    assertEquals(responses, postResponse.getTriangles());
	}

	@Test
	public void testSetAndGetMaxTriangle() {
	    Triangle maxTriangle = new Triangle(3, 4, 5);
	    PostResponse postResponse = new PostResponse();
	    postResponse.setMaxTriangle(maxTriangle);
	    assertEquals(maxTriangle, postResponse.getMaxTriangle());
	}

	@Test
	public void testSetAndGetMinTriangle() {
	    Triangle minTriangle = new Triangle(1, 1, 1);
	    PostResponse postResponse = new PostResponse();
	    postResponse.setMinTriangle(minTriangle);
	    assertEquals(minTriangle, postResponse.getMinTriangle());
	}

	@Test
	public void testSetAndGetAverageTriangle() {
	    Triangle averageTriangle = new Triangle(2, 3, 4);
	    PostResponse postResponse = new PostResponse();
	    postResponse.setAverageTriangle(averageTriangle);
	    assertEquals(averageTriangle, postResponse.getAverageTriangle());
	}

	@Test
	public void testSetAndGetTriangles() {
	    List<GetResponse> responses = new ArrayList<>();
	    responses.add(new GetResponse());
	    PostResponse postResponse = new PostResponse();
	    postResponse.setTriangles(responses);
	    assertEquals(responses, postResponse.getTriangles());
	}
}
