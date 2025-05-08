package zad2;

//import com.sun.tools.classfile.RuntimeAnnotations_attribute;
enum TaskState {
    CREATED, RUNNING, ABORTED, READY;
}

public class StringTask implements Runnable {
    private final String str;
    private final int n;
    private String res;
    public String getResult() {
        return res;
    }

    private TaskState state;
    public TaskState getState() {
        return state;
    }

    public  StringTask (String str, int n){
        this.str = str;
        this.n = n;
        this.state = TaskState.CREATED;

    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();

    }

    public void abort() {
        if (state == TaskState.RUNNING) {
            state = TaskState.ABORTED;
        }
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;

    }

    @Override
    public void run() {
        state = TaskState.RUNNING;

        StringBuilder sb = new StringBuilder();

        try {
            for (int i = 0; i < n; i++) {
                if (state == TaskState.ABORTED) {
                    break;
                }
                sb.append(str);

            }
            res = sb.toString();
            state = TaskState.READY;

        } catch (Exception e) {
            state = TaskState.ABORTED;
        }
    }
}
