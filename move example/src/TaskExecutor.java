import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TaskExecutor
{
    public static void main( String[] args )
    {
        // create and name each runnable
        PrintTask task1 = new PrintTask( "D" );
        PrintTask task2 = new PrintTask( "E" );
        PrintTask task3 = new PrintTask( "F" );
        
        System.out.println( "Starting Executor" );

        // create ExecutorService to manage threads
        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        // start threads and place in runnable state
        threadExecutor.execute( task1 ); // start task1	
        threadExecutor.execute( task2 ); // start task2
        threadExecutor.execute( task3 ); // start task3

        // No new tasks will be accepted
        // shut down worker threads when their tasks complete
        threadExecutor.shutdown(); 

        System.out.println( "Tasks started, main ends.\n" );
    }
}