package multithreading;

import java.util.concurrent.Semaphore;

/** Use two Threads and Print Numbers from 1 to 10 where one thread print
    only even Numbers and another thread print only odd numbers */

class NumberPrinter {
    private Semaphore oddSemaphore = new Semaphore(1); // Start with odd
    private Semaphore evenSemaphore = new Semaphore(0); // Even waits initially
    void printOdd() {
        for (int i = 1; i <= 10; i += 2) {
            try {
                oddSemaphore.acquire();
                System.out.print(i + " ");
                evenSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            try {
                evenSemaphore.acquire();
                System.out.print(i + " ");
                oddSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        NumberPrinter np = new NumberPrinter();
        Thread oddThread = new Thread(() -> np.printOdd());
        Thread evenThread = new Thread(() -> np.printEven());
        oddThread.start();
        evenThread.start();
        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
