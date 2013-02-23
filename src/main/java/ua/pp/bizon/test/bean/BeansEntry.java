package ua.pp.bizon.test.bean;

import java.io.IOException;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.pp.bizon.test.utils.FileUtils;

public class BeansEntry {

    private static Logger logger = LoggerFactory.getLogger(BeansEntry.class);

    protected BeansEntry() {
        init();
    }

    protected void init() {
        try {
            f1fileElements = parse(FileUtils.readFully("F1"), "F1");
        } catch (IOException e) {
            logger.error("error in reading F1 file", e);
        }
        try {
            f2fileElements = parse(FileUtils.readFully("F2"), "F2");
        } catch (IOException e) {
            logger.error("error in reading F2 file", e);
        }
    };

    protected static FileElements parse(String data, String filename) throws IOException {
        String[] rawdata = data.split(",");
         TreeMap<Integer, Double> tree =  new TreeMap<Integer, Double>();
        for (int i = 0; i < rawdata.length; i++) {
            try {
                Double element = Double.valueOf(rawdata[i].trim());
                tree.put(i, element);
            } catch (NumberFormatException e) {
                logger.warn("in file " + filename + " element no " + i + " i not parsed, cause: " + e.getMessage());
            }
        }
        return new FileElements(filename, tree);
    }

    private FileElements f2fileElements;
    private FileElements f1fileElements;

    private static BeansEntry ENTRY;

    public static BeansEntry get() {
        if (ENTRY == null) {
            synchronized (BeansEntry.class) {
                if (ENTRY == null) {
                    ENTRY = new BeansEntry();
                }
            }
        }
        return ENTRY;
    }

    public FileElements getF2fileElements() {
        return f2fileElements;
    }

    public FileElements getF1fileElements() {
        return f1fileElements;
    }

    protected void setF1fileElements(FileElements f1fileElements) {
        this.f1fileElements = f1fileElements;
    }

    protected void setF2fileElements(FileElements f2fileElements) {
        this.f2fileElements = f2fileElements;
    }
}
