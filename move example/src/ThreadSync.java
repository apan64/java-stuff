import java.lang.Thread;
import java.util.concurrent.*;

public class ThreadSync
{
//	private static Semaphore canPrintA = new Semaphore(1);
//	private static Semaphore canPrintB = new Semaphore(0);
//	private static Semaphore canPrintC = new Semaphore(0);

	private static int count = 100;
	
    public static void main( String[] args ) {
        System.out.printf( "Begin main thread\n");

        // create and name each runnable
        Runnable task1 = new PrintTaskA();
        Runnable task2 = new PrintTaskB();
        Runnable task3 = new PrintTaskC();

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
    
    public static class PrintTaskA implements Runnable 
    {
        public void run(){
    	    for (int i=0; i<count; i++) {
//    	    	try {
//    	    		canPrintA.acquire();
//    	    	}
//    	        catch (InterruptedException ex) {
//    	        	ex.printStackTrace();
//    	        }
    	        System.out.printf( "%2d: %s\n", i, "A");
//    	    	canPrintB.release();
    	    }
        }
    }
    public static class PrintTaskB implements Runnable 
    {
        public void run(){
    	    for (int i=0; i<count; i++) {
//    	    	try {
//    	    		canPrintB.acquire();
//    	    	}
//    	        catch (InterruptedException ex) {
//    	        	ex.printStackTrace();
//    	        }
    	        System.out.printf( "%2d: %s\n", i, "B");
//    	    	canPrintC.release();
    	    }
        }
    }
    public static class PrintTaskC implements Runnable 
    {
        public void run(){
    	    for (int i=0; i<count; i++) {
//    	    	try {
//    	    		canPrintC.acquire();
//    	    	}
//    	        catch (InterruptedException ex) {
//    	        	ex.printStackTrace();
//    	        }
    	        System.out.printf( "%2d: %s\n", i, "C");
//    	    	canPrintA.release();
    	    }
        }
    }
}