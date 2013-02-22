package ua.pp.bizon.test.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static String readFully(String string) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToString(new File(string));
    }

    public static void writeFully(String filename, String rawData) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(new File(filename), rawData);
    }

}
