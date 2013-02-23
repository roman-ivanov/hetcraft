package ua.pp.bizon.test.tasks;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import ua.pp.bizon.test.bean.FileElements;
import ua.pp.bizon.test.utils.FileUtils;

public class Sunc implements Runnable {

    private FileElements elements;
    private String filename;

    public Sunc(FileElements abstractElements, String filename) {
        this.elements = abstractElements;
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileUtils.writeFully(filename, elements.getRawData());
        } catch (IOException e) {
            LoggerFactory.getLogger(getClass()).error("file " + filename + "is not written, cause: " + e.getMessage(), e);
        }
    }

}
