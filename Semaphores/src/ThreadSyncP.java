/* Andrew Pan
 * Project #4
 * This program runs multiple threads using Semaphores to control the times at which each thread can execute its next action.
 */
import java.lang.Thread;
import java.util.concurrent.*;

public class ThreadSyncP
{
    private static boolean runFlag = true;
    private static final Semaphore availableS = new Semaphore(2);
    private static final Semaphore availableN = new Semaphore(0);
    private static final Semaphore availableL = new Semaphore(2);
	
    public static void main( String[] args ) {
     	Runnable[] tasks = new Runnable[17];
    	Thread[] threads = new Thread[17];
    	// create 10 digit threads
    	for (int d=0; d<10; d++) {
    		tasks[d] = new PrintDigit((char)('0'+d));
    		threads[d] = new Thread( tasks[d] );
    		threads[d].start();
    	}
    	// create 6 letter threads
    	for (int d=0; d<6; d++) {
    		tasks[d+10] = new PrintLetter((char)('A'+d));
    		threads[d+10] = new Thread( tasks[d+10] );
    		threads[d+10].start();
    	}
       	// create a thread to print dash(-)
		tasks[16] = new PrintSymbol('*');
		threads[16] = new Thread( tasks[16] );
		threads[16].start();

		// Let the threads to run for a period of time
        try {
        	Thread.sleep(500);
        }
        catch (InterruptedException ex) {
        	ex.printStackTrace();
        }
        
        runFlag = false;
        // Interrupt the threads
        for (int i=0; i<17; i++) threads[i].interrupt();
    }
    
    public static class PrintDigit implements Runnable 
    {
    	private char c;
    	public PrintDigit(char c) { this.c=c; }
        public void run(){
    	    while (runFlag) {
    	    	try {
					availableN.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
       	        System.out.printf( "%c\n", c);
       	        availableL.release(2);
    	    }
        }
    }
    public static class PrintLetter implements Runnable 
    {
    	private char c;
    	public PrintLetter(char c) { this.c=c; }
        public void run(){
    	    while (runFlag) {
    	    	try {
					availableL.acquire(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
    	        System.out.printf( "%c\n", c);
    	        availableS.release();
    	        availableL.release(2);
    	    }
         }
    }
    public static class PrintSymbol implements Runnable 
    {
    	private char c;
    	public PrintSymbol(char c) { this.c=c; }
        public void run(){
    	    while (runFlag) {
    	    	try {
					availableS.acquire(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
    	    	try {
					availableL.acquire(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
    	        System.out.printf( "%c\n", c);
    	        availableN.release(2);
    	    }
        }
    }
}