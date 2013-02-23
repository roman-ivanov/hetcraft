package ua.pp.bizon.test.tasks;

import java.util.List;

public class TasksRunner {

    private List<Runnable> tasks;

    public TasksRunner() {
        init();
    }

    private void init() {
        if (tasks != null){
            for (Runnable r: tasks){
                run(r);
            }
        }
    }

    public static void run(Runnable runnable) {
        new Thread(runnable, "Task: " + runnable.toString()).start();
    }

}
