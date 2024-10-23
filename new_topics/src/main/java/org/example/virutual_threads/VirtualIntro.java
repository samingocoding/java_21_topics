package org.example.virutual_threads;

public class VirtualIntro {
    public static void main(String[] args) throws InterruptedException {

        // Creating a virtual thread using Thread.ofVirtual() factory
        Thread virtualThread = Thread.ofVirtual().unstarted(()->{
            System.out.println("Java 21 Virtual thread started");
        });
        Thread vt = Thread.ofVirtual().start(() -> System.out.println("Hello from virtual thread!"));

        vt.join();
        // Join to wait for the virtual thread to finish
        virtualThread.join();

        virtualThread.start();
        virtualThread.join();
        /***
         * Join : used to pause the execution of the current thread until the thread on which join() was called has finished executing.
         * In simpler terms, it tells one thread to "wait" until another thread completes its task.
         *
         * */

        System.out.println("Practising how join works");
        joinMethod();
    }

    public static void joinMethod() throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);  // Simulate a long task
            } catch (InterruptedException e) {
                System.out.println("Main hread interrupted");
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread task completed");
        });

        t.start();

        // Main thread waits for a maximum of 1 second (1000 ms) for the thread to finish
        t.join(1000); //as we will not complete we neeed 3000 hence we complete later

        System.out.println("Main thread resumed after 1 second of waiting");

    }
}
