package ua.pp.bizon.test.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MyAppTest {
    
    private MyApp app;

    @Before
    public  void setUp() throws Exception {
        this.app = new MyApp();
        app.setBeansEntry(new BeansEntryMock());
        app.getBeansEntry().getF1fileElements().put(0, 0.0);
        app.getBeansEntry().getF1fileElements().put(1, 1.0);
        app.getBeansEntry().getF1fileElements().put(2, 2.0);
        app.getBeansEntry().getF1fileElements().put(3, 10.0);
        app.getBeansEntry().getF1fileElements().put(4, 20.0);        
        app.getBeansEntry().getF2fileElements().put(0, 1.0);
    }

    @Test
    public void testGetIt() {
        assertEquals("<?xml version=\"1.0\"?><response>1.0</response>", app.getIt("0"));
        assertEquals("<?xml version=\"1.0\"?><error>null</error>", app.getIt("1"));
        assertEquals("<?xml version=\"1.0\"?><error>For input string: \"q\"</error>", app.getIt("q"));
        assertEquals("<?xml version=\"1.0\"?><error>For input string: \"\"</error>", app.getIt(""));
    }

    @Test
    public void testPostIt() {
        assertEquals("<?xml version=\"1.0\"?><response>1</response>",app.postIt(""));
        assertEquals("<?xml version=\"1.0\"?><response>1</response>",app.postIt("<?xml version=\"1.0\"?><error>null</error>"));
        assertEquals("<?xml version=\"1.0\"?><response>1</response>",app.postIt("<?xml version=\"1.0\"?><request><v2>12</v2><v3>34</v3><v4>56</v4></request>"));
        assertEquals("<?xml version=\"1.0\"?><response>1</response>",app.postIt("<?xml version=\"1.0\"?><request><v2>12</v2><v3>we</v3><v4>56</v4></request>"));
        assertEquals("<?xml version=\"1.0\"?><response>1</response>",app.postIt("<?xml version=\"1.0\"?><request><v2>12</v2><v3>34</v3></request>"));
        assertEquals("<?xml version=\"1.0\"?><response>0</response>",app.postIt("<?xml version=\"1.0\"?><request><v2>12</v2><v3>0</v3><v4>1</v4></request>"));
        assertEquals("<?xml version=\"1.0\"?><response>0</response>",app.postIt("<?xml version=\"1.0\"?><request><v2>12</v2><v3>4</v3><v4>2</v4></request>"));
        assertEquals("1.0,12.0,32.0", app.getBeansEntry().getF2fileElements().getRawData());
    }

}
