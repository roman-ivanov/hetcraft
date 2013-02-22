package ua.pp.bizon.test.bean;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class BeansEntryTest {

    @Test
    public void test() throws IOException {
        FileElements e = BeansEntry.parse("", "path");
        assertEquals(-1, e.lastId);
         e = BeansEntry.parse("0.1", "path");
        assertEquals(0, e.lastId);
        assertEquals(Double.valueOf(0.1), e.get(0));
        e = BeansEntry.parse("0.1,1,2,,5,,", "path");
        assertEquals(Double.valueOf(0.1), e.get(0));
        assertEquals(Double.valueOf(1), e.get(1));
        assertEquals(Double.valueOf(2), e.get(2));
        assertEquals(null, e.get(3));
        assertEquals(Double.valueOf(5), e.get(4));
        assertEquals(null, e.get(5));
        assertEquals(null, e.get(6));
        assertEquals(null, e.get(7));
        assertEquals(4, e.lastId);
    }

}
