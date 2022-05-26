import java.util.concurrent.Semaphore;

// Driver class
public class Activity2 {
    public static void main(String[] args) {
        Philosopher[] philos = new Philosopher[5];
        Semaphore[] chopsticks = new Semaphore[5];

        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            if (i == 4)
                philos[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % 5]);
            else
                philos[i] = new Philosopher(i, chopsticks[(i + 1) % 5], chopsticks[i]);
            new Thread(philos[i]).start();
        }

        // Current run results in an infinite loop
        // Uncomment the code below if program will stop once all philosophers have
        // eaten

        /*
         * while (true) {
         * try {
         * Thread.sleep(1000);
         * boolean lock = true;
         * for (Semaphore c : chopsticks) {
         * if (c.availablePermits() > 0) {
         * lock = false;
         * break;
         * }
         * }
         * if (lock) {
         * Thread.sleep(1000);
         * System.out.println("Lahat ay kumain na, ggez.");
         * break;
         * }
         * } catch (Exception e) {
         * e.printStackTrace(System.out);
         * }
         * }
         * System.exit(0);
         */
    }
}