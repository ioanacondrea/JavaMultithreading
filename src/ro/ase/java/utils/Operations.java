package ro.ase.java.utils;

public class Operations implements Runnable{
	private Object lock = new Object();
	protected float[] v1;
	protected float[] v2;
	
	public Operations(float[] v1, float[] v2) {
		this.v1= v1;
		this.v2= v2;
		}
	
	private float VectorAddition(float x, float y) {
		synchronized(lock){
				try {
					Thread.currentThread();
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			return x+y;
			
		}
	}
	
	
	
	@Override
	public void run() {
		for(int i=0;i<4;i++) {
			float rez=this.VectorAddition(v1[i], v2[i]);
			System.out.println("Am adunat "+v1[i]+"+"+v2[i]+" = "+rez+" "+Thread.currentThread().getName());
		}
	}
	
	
}
