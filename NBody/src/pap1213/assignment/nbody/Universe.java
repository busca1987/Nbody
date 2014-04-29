package pap1213.assignment.nbody;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Universe extends Thread {

	private boolean stop;
    private UniverseFrame frame;
    private Context context;
    private int nBody;
	private Semaphore writeLock;
	private Semaphore readLock;
	private CountDownLatch writeLatch;
	private CountDownLatch doneLatch;
	
    public Universe(){
        stop = false;
        frame = new UniverseFrame();
        frame.setVisible(true);
   }
   
    public void setSemaphore (Semaphore writeLock,Semaphore readLock,CountDownLatch writeLatch,CountDownLatch doneLatch)
    {
	    this.writeLock = writeLock;
	    this.readLock = readLock;
	    this.writeLatch = writeLatch;
	    this.doneLatch = doneLatch;
    }
    
    public void setNBody (int nBody)
    {
    	this.nBody = nBody;
    }
    
    public void setContext (Context ctx)
    {
    	this.context = ctx;
    }
    
    public void run(){
        while (!stop) {
        	
            try {
            	frame.updatePosition(context.getPositions());
                readLock.release(nBody);
				writeLatch.await();
				writeLock.release(nBody);
				doneLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //Faccio leggere le nuove posizioni

            
            try {
                Thread.sleep(20);     
            } catch (Exception ex){
            }
        }
    }
}
