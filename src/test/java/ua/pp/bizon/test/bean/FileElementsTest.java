package ua.pp.bizon.test.bean;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileElementsTest {

    @Test
    public void testGet() {
        FileElements testedd = new FileElementsMock();
        assertNull(testedd.get(0));
        assertNull(testedd.get(1));
        assertNull(testedd.get(-1));
        testedd.put(0, 0.1);
        assertEquals(Double.valueOf(0.1), testedd.get(0));
    }

    @Test
    public void testPut() {
        FileElements testedd = new FileElementsMock();
        testedd.put(0, 0.1);
        assertEquals(Double.valueOf(0.1), testedd.get(0));
        testedd.put(3, 0.3);
        assertEquals(Double.valueOf(0.1), testedd.get(0));
        assertEquals(Double.valueOf(0.3), testedd.get(3));
        assertNull(testedd.get(1));
        assertNull(testedd.get(2));
        testedd.put(2, 0.2);
        assertEquals(Double.valueOf(0.2), testedd.get(2));
        assertEquals(Double.valueOf(0.3), testedd.get(3));
    
    }

    @Test
    public void testGetRawData() {
        FileElements testedd = new FileElementsMock();
        testedd.put(0, 0.1);
        System.out.println(testedd.lastId);
        assertEquals("0.1", testedd.getRawData());
        testedd.put(3, 0.3);
        assertEquals("0.1,,,0.3", testedd.getRawData());
        testedd.put(2, 0.2);
        assertEquals("0.1,,0.2,0.3", testedd.getRawData());
    }
    
}
