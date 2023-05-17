package com.WorkplaceGarashchuk.WorkplaceOne;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.WorkplaceGarashchuk.WorkplaceOne.responses.GetResponse;
import com.WorkplaceGarashchuk.WorkplaceOne.triangle.Triangle;
import com.WorkplaceGarashchuk.WorkplaceOne.validator.ValidatorError;

public class MyResponseTest {
	
	@Test
    void testEquals() {
        Triangle t1 = new Triangle(3, 4, 5);
        ValidatorError e1 = new ValidatorError("Invalid triangle sides");
        GetResponse r1 = new GetResponse(t1, e1);
        GetResponse r2 = new GetResponse();
        r2.setErrors(e1);
        r2.setTriagnle(t1);

        assertTrue(r1.equals(r2));
    }

    @Test
    void testGetTriangle() {
        Triangle t = new Triangle(3, 4, 5);
        ValidatorError e = new ValidatorError("Invalid triangle sides");
        GetResponse r = new GetResponse(t, e);

        assertEquals(t, r.getTriangle());
    }

    @Test
    void testGetErrors() {
        Triangle t = new Triangle(3, 4, 5);
        ValidatorError e = new ValidatorError("Invalid triangle sides");
        GetResponse r = new GetResponse(t, e);

        assertEquals(e, r.getErrors());
    }
}
