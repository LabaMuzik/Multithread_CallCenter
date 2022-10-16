import java.util.concurrent.BlockingQueue;
public class CallCenter implements Runnable {
    private final int CALLS;
    private final BlockingQueue<String> REQUESTS;
    public CallCenter (BlockingQueue<String> requests, int calls) {
        this.REQUESTS = requests;
        this.CALLS = calls;
    }
    @Override
    public void run() {
        for (int i = 0; i < CALLS; i++) {
            try {
                Thread.sleep(4000);
                System.out.println("Оработан - " + REQUESTS.take() + "! \n");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}