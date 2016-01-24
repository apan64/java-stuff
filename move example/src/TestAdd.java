import java.lang.Thread;
import java.util.concurrent.*;

public class TestAdd
{
//	private static Semaphore mutex = new Semaphore(1);

	private static int total = 0;
	
    public static void main( String[] args ) {
        System.out.printf( "Begin main thread\n");

        // create and name each runnable
        Runnable task1 = new AddTask();
        Runnable task2 = new AddTask();
        Runnable task3 = new AddTask();

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
        
        System.out.printf( "total is %d\n", total);

        System.out.printf( "End main thread\n");
    }
    
    public static class AddTask implements Runnable 
    {
        public void run(){
    	    for (int i=0; i<100; i++) {
//    	    	try {
//    	    		mutex.acquire();
//    	    	}
//    	        catch (InterruptedException ex) {
//    	        	ex.printStackTrace();
//    	        }
    	    	total = total+1;
//    	    	mutex.release();
    	    }
        }
    }
}