package org.example.virutual_threads;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorToVirtual {
    public static void main(String[] args) {
//        System.out.println("Starting old executor thread");
//        oldExecutorThread();
//        System.out.println("Ended old executor thread");

        System.out.println("Starting new virtual thread");
        newVirtualExecutorThread();
        System.out.println("Ended new virtual thread");
    }

    public static void oldExecutorThread() {

//        var numberOfCores = 8; //number of Threads
        var numberOfCores = 99999; //number of Threads this leads to out of memory
        /***
         * We get an exception
         *Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
         *at java.base/java.lang.Thread.start0(Native Method)
         *at java.base/java.lang.Thread.start(Thread.java:1553)
         *
         * */
        try (var executor = Executors.newFixedThreadPool(numberOfCores)) {
            IntStream.range(0, 99999).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(i);
                    return i;
                });
            });
        }


    }

    public static void newVirtualExecutorThread() {

//        var numberOfCores = 8; //number of Threads
        var numberOfCores = 99999; //number of Threads this leads to out of memory
        /***
         * No need to pass newVirtualThreadPerTaskExecutor( x number of cores)
         * No memory reached full capacity
         * */
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 99999).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(i);
                    return i;
                });
            });
        }


    }
}
