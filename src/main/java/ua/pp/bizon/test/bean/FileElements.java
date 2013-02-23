package ua.pp.bizon.test.bean;

import java.util.TreeMap;

import ua.pp.bizon.test.tasks.Sunc;
import ua.pp.bizon.test.tasks.TasksRunner;

public class FileElements {

    private TreeMap<Integer, Double> elements = new TreeMap<Integer, Double>();
    protected int lastId = -1;
    private final String filename;

    public FileElements(String name) {
        filename = name;
    }
    
    public FileElements(String name, TreeMap<Integer, Double> data) {
        filename = name;
        elements = data;
        lastId = elements.isEmpty() ? -1 : elements.lastKey() ;
    }

    public Double get(Integer from) {
        return elements.get(from);
    }

    public synchronized void put(Integer to, Double data) {
        elements.put(to, data);
        if (lastId <= to)
            lastId = to;
        sunc();
    }

    protected void sunc() {
        TasksRunner.run(new Sunc(this, filename));
    }

    public synchronized String getRawData() {
        StringBuilder builder = new StringBuilder();
        if (lastId == -1){
            return builder.toString();
        }
        for (int i =0; i < lastId; i++){
            Double el = elements.get(i);
            if (el != null){
                builder.append(el);
            }
            builder.append(',');
        }
        builder.append(elements.get(lastId));
        return builder.toString();
    }
}
