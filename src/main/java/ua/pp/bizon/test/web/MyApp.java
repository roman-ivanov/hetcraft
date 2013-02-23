package ua.pp.bizon.test.web;

import java.io.ByteArrayInputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import ua.pp.bizon.test.bean.FileElements;
import ua.pp.bizon.test.bean.BeansEntry;

@Path("/netcraft/")
public class MyApp {
    

    private BeansEntry beansEntry;

    public BeansEntry getBeansEntry() {
       if (beansEntry == null){
            beansEntry = BeansEntry.get();
        }
        return beansEntry;
    }

    public void setBeansEntry(BeansEntry beansEntry) {
        this.beansEntry = beansEntry;
    }

    /**
     * Method processing HTTP GET requests
     * 
     * @return String that will be send back.
     */
    @GET
    @Path("{v1}")
    @Produces("text/xml")
    public String getIt(@PathParam("v1") String v1) {
        try {
        int parseInt = Integer.parseInt(v1);
        FileElements finised = getBeansEntry().getF2fileElements();
        Double response = finised.get(parseInt);
        response = response > 10.0 ? response - 10 : response;
        return "<?xml version=\"1.0\"?><response>" + response + "</response>";
        } catch (Exception e){
            LoggerFactory.getLogger(getClass()).warn(e.getMessage(), e);
            return  "<?xml version=\"1.0\"?><error>" + e.getMessage() + "</error>";
        }
    }

    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    public String postIt(String params) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(params.getBytes()));
            NodeList list = doc.getDocumentElement().getChildNodes();
            Double v2 = Double.NaN;
            Integer v3 = null;
            Integer v4 = null;
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeName().equals("v2")) {
                    v2 = Double.valueOf(list.item(i).getTextContent().trim());
                }
                if (list.item(i).getNodeName().equals("v3")) {
                    v3 = Integer.valueOf(list.item(i).getTextContent().trim());
                }
                if (list.item(i).getNodeName().equals("v4")) {
                    v4 = Integer.valueOf(list.item(i).getTextContent().trim());
                } 
            }
            if (Double.isNaN(v2) || v3 == null || v4 == null) {
                LoggerFactory.getLogger(getClass()).warn("v2 or v3 or v4 is not defined");
                return "<?xml version=\"1.0\"?><response>1</response>";
            }
            Double v3temp = getBeansEntry().getF1fileElements().get(v3) + v2;
            getBeansEntry().getF2fileElements().put(v4, v3temp < 10 ? v3temp + 10 : v3temp);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).warn(e.getMessage(), e);
            return "<?xml version=\"1.0\"?><response>1</response>";
        }
        return "<?xml version=\"1.0\"?><response>0</response>";
    }

}
