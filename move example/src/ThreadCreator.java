import java.lang.Thread;

public class ThreadCreator
{
    public static void main( String[] args ) {
        System.out.printf( "Begin main thread\n");

        // create and name each runnable
        PrintTask task1 = new PrintTask( "A" );
        PrintTask task2 = new PrintTask( "B" );
        PrintTask task3 = new PrintTask( "C" );

        Thread thread1 = new Thread( task1 );
        Thread thread2 = new Thread( task2 );
        Thread thread3 = new Thread( task3 );

        thread1.start();
        thread2.start();
        thread3.start();

        try {
	        thread1.join();
	        thread2.join();
	        thread3.join();
        }
        catch (InterruptedException ex) {
        	ex.printStackTrace();
        }
        
        System.out.printf( "End main thread\n");
    }
}