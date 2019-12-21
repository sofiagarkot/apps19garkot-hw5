package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AsIntStreamTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamof() {
        System.out.println("streamOf -- not singletons");
        int[] intArr = {-1, 0, 1, 2, 3};
        IntStream expResult = AsIntStream.of(intArr);
        assertNotEquals(expResult, intStream);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAverage() {
        System.out.println("Empty Average");
        int[] intArr = {};
        Double expResult = AsIntStream.of(intArr).average();
    }

    @Test
    public void testNegativeAverage() {
        System.out.println("Negative Average");
        int[] intArr = {0, -9, -10, -1};
        Double result = AsIntStream.of(intArr).average();
        Double expResult = -5.0;
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        System.out.println("Max");
        int[] intArr = {0, 9, 10, 1};
        int result = AsIntStream.of(intArr).max();
        int expResult = 10;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax() {
        System.out.println("Empty Max");
        int[] intArr = {};
        int result = AsIntStream.of(intArr).max();
    }

    @Test
    public void testMin() {
        System.out.println("Min");
        int[] intArr = {0, 9, 10, 1};
        int result = AsIntStream.of(intArr).min();
        int expResult = 0;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin() {
        System.out.println("Empty Min");
        int[] intArr = {};
        int result = AsIntStream.of(intArr).min();
    }

    @Test
    public void testCount(){
        System.out.println("Count");
        int[] intArr = {0, 9, 10, 1};
        long expResult = 4;
        long result = AsIntStream.of(intArr).count();
        assertEquals(expResult, result);
    }

    @Test
    public void testEmptyCount(){
        System.out.println("Empty Count");
        int[] intArr = {};
        long expResult = 0;
        long result = AsIntStream.of(intArr).count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum(){
        System.out.println("Sum");
        int[] intArr = {0, 9, 10, 1};
        long expResult = 20;
        long result = AsIntStream.of(intArr).sum();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum() {
        System.out.println("Empty Sum");
        int[] intArr = {};
        int result = AsIntStream.of(intArr).sum();
    }

    @Test
    public void testToArray() {
        System.out.println("To Array");
        int[] intArr = {0, 9, 10, 1};
        int[] result = AsIntStream.of(intArr).toArray();
        assertArrayEquals(intArr, result);
    }

    @Test
    public void testEmptyToArray() {
        System.out.println("To Array");
        int[] intArr = {};
        int[] result = AsIntStream.of(intArr).toArray();
        assertArrayEquals(intArr, result);
    }

    @Test
    public void testFilter() {
        System.out.println("Filter");
        int[] intArr = {-2,-1,0,1,2,3,4,5};
        int[] expResult = {1,2,3,4,5};
        int[] result = AsIntStream.of(intArr).filter(x -> x > 0).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMap() {
        System.out.println("Map");
        int[] intArr = {2,3,4};
        int[] expResult = {4,6,8};
        int[] result = AsIntStream.of(intArr).map(x -> x * 2).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testForEach() {
        System.out.println("For Each");
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        String result = str.toString();
        String expResult = "-10123";
        assertEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        System.out.println("Flat Map");
        int[] intArr = {2,3,4};
        int[] expResult = {1,2,3,2,3,4,3,4,5};
        int[] result = AsIntStream.of(intArr).flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        System.out.println("Reduce");
        int[] intArr = {1,2,3,4,5};
        int expResult = 16;
        int result = AsIntStream.of(intArr).reduce(1, (sum, x) -> sum += x);
        assertEquals(expResult, result);
    }


}
