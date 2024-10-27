package org.example.scoped_values;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScopedIntro {
    // Define a ScopedValue
    private static final ScopedValue<String> USERNAME = ScopedValue.newInstance();

    /***
     * Scoped Values are a new feature introduced in Java 21 that provide a structured and efficient way to share immutable data between threads (or within a specific scope of code execution).
     * They are intended to replace or supplement ThreadLocal variables, offering a cleaner and more efficient alternative for passing contextual data in highly concurrent programs
     * **/
    public static void main(String[] args)  throws IllegalStateException{
        // Bind a value to the ScopedValue in a specific scope
        ScopedValue.where(USERNAME, "Alice").run(() -> {
            // Access the ScopedValue within this scope
            System.out.println("Current user: " + USERNAME.get());  // Outputs: Current user: Alice

            // Inner scope with a different value
            ScopedValue.where(USERNAME, "Bob").run(() -> {
                System.out.println("Inner scope user: " + USERNAME.get());  // Outputs: Inner scope user: Bob
            });

            // Back to the outer scope
            System.out.println("Outer scope user again: " + USERNAME.get());  // Outputs: Outer scope user again: Alice
        });

        // Out of the scope, ScopedValue is not available
      //  System.out.println("Outside the scope: " + USERNAME.get());  // Throws IllegalStateException

        System.out.println("Finished Scoped Value now using Virtual Threads");
        usingVirtualThreads();
    }


    public static void usingVirtualThreads(){
        // Use an ExecutorService to run virtual threads
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Bind a value to the ScopedValue in a specific scope
        ScopedValue.where(USERNAME, "Alice").run(() -> {
            // Submit tasks to the virtual thread executor
            for (int i = 0; i < 5; i++) {
                executor.submit(() -> {
                    System.out.println("Task running with user: " + USERNAME.get());
                });
            }
        });


        // Shutdown the executor
        executor.shutdown();
    }
}
