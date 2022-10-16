import java.util.concurrent.*;

public class Main {

    private static final int WORKFLOW = 10;
    private static final int THREADPOOL = 5;
    private static final BlockingQueue<String> REQUESTS = new ArrayBlockingQueue<>(WORKFLOW);
    private static final ExecutorService EXECUTORSERVICE = Executors.newFixedThreadPool(THREADPOOL);


    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < WORKFLOW; i++) {
                REQUESTS.add("Запрос №" + i);
                System.out.println(" -> Входящий звонок №  " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();

        for (int i = 0; i < THREADPOOL; i++) {
            EXECUTORSERVICE.execute(new CallCenter(REQUESTS, WORKFLOW));
        }
        EXECUTORSERVICE.shutdown();
    }

}