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

    }
}