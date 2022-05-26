import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {

    private int id;
    private Semaphore leftChopstick;
    private Semaphore rightChopstick;

    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    void think() {
        try {
            int randTime = ThreadLocalRandom.current().nextInt(0, 1000);
            System.out.println("Philosopher " + (id + 1) + " is thinking");
            Thread.sleep(randTime); // Generate random delay for philosopher to think
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    void eat() {
        try {
            int randTime = ThreadLocalRandom.current().nextInt(0, 1000);
            System.out.println("Philosopher " + (id + 1) + " is eating");
            Thread.sleep(randTime); // Generate random delay for philosopher to eat
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Think
                think();
                // Grab chopsticks if available
                leftChopstick.acquire();
                System.out.println("Philosopher " + (id + 1) + " grabs left chopstick");
                rightChopstick.acquire();
                System.out.println("Philosopher " + (id + 1) + " grabs right chopstick");
                // Eat
                eat();
                // Put down chopsticks after eating
                leftChopstick.release();
                System.out.println("Philosopher " + (id + 1) + " puts down left chopstick");
                rightChopstick.release();
                System.out.println("Philosopher " + (id + 1) + " puts down right chopstick");
            }
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
}
