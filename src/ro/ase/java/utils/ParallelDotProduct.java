package ro.ase.java.utils;

public class ParallelDotProduct implements Runnable {
	private int[] v1;
	private int[] v2;
	protected int start;
	protected int stop;
	private int rez;
	
	public ParallelDotProduct(int[] v1, int[] v2, int start, int stop) {
		this.v1=v1;
		this.v2=v2;
		this.start=start;
		this.stop=stop;
	}
	
	public int getStart() {
		return this.start;
	}
	
	public int getStop() {
		return this.stop;
	}
	
	public int getRezult() {
		return this.rez;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int idx=start; idx<=stop;idx++) {
			rez+=v1[idx]*v2[idx];
		}
	}
}
