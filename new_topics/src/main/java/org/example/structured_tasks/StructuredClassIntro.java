package org.example.structured_tasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

public class StructuredClassIntro {
    public static void main(String[] args) {
        try(var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()){
            StructuredTaskScope.Subtask<String> task1 = scope.fork(() -> {
                Thread.sleep(1000);
                return "task1 completed";
            });
            StructuredTaskScope.Subtask<String> task2 = scope.fork(() -> {
                Thread.sleep(2000);
                return "task2 completed";
            });
            //Fork method : // Submit multiple tasks that run concurrently

            scope.join(); // the join  method : Wait for tasks to complete
            String result = scope.result();

            System.out.println("First completed result: " + result);

        } catch (InterruptedException  | ExecutionException e) {
            throw new RuntimeException(e);
        }


        System.out.println("On Failer of Structured tasks ");


            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

                // Submit multiple tasks that run concurrently
                StructuredTaskScope.Subtask<String> task1 = scope.fork(() -> {
                    Thread.sleep(1000); // Simulating a task
                    return "Result from task 1";
                });

                StructuredTaskScope.Subtask<String> task2 = scope.fork(() -> {
                    Thread.sleep(2000); // Simulating another task
                    return "Result from task 2";
                });

                // Wait for all tasks to complete or fail
                scope.join();          // Wait for both tasks
                scope.throwIfFailed(); // Throw an exception if any task failed

                System.out.println("On Failer of Structured tasks ");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
