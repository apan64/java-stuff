public class PrintTask implements Runnable 
{
    private final String strToBePrinted;
    
    public PrintTask( String s ) {
	   strToBePrinted = s;        
    }

    public void run(){
	    for (int i=0; i<100; i++) {
            System.out.printf( "%2d: %s\n", i, strToBePrinted);
            // System.out.printf( "%2d: %s", i, strToBePrinted);
            // System.out.printf( "\n");
            try {
            	Thread.sleep(1);
	        }
	        catch (InterruptedException ex) {
	        	ex.printStackTrace();
	        }
	    }
    }
}