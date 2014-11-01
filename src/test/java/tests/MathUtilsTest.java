package tests;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.utils.MathUtils;

public class MathUtilsTest {
    private double EPSILON = 0.00000001;

    @Test
    public void testRoundNum() {
        Assert.assertEquals(2.0, MathUtils.log(4, 2), EPSILON);
    }

    @Test
    public void testUnroundNum() {
        Assert.assertEquals(3.1699250014423126, MathUtils.log(9, 2), EPSILON);
    }

    @Test
    public void testOne() {
        Assert.assertEquals(0, MathUtils.log(1, 2), EPSILON);
    }

    @Test
    public void testZero() {
        Assert.assertEquals(Double.NEGATIVE_INFINITY, MathUtils.log(0, 2), EPSILON);
    }
}
