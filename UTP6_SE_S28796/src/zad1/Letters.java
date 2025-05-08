package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    private List<Thread> threads;

    public Letters (String letters) {

        threads = new ArrayList<>();

        for (char x : letters.toCharArray()) {

            Thread t = new Thread(() -> {

                while (true) {

                    System.out.print(x);

                    try {

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }, "Thread " + x);

            threads.add(t);

        }
    }
    public List<Thread> getThreads(){
        return threads;
    }
}
