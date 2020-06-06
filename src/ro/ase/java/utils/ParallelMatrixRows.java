package ro.ase.java.utils;

public class ParallelMatrixRows implements Runnable {
	private int[][] matrix;
	private int[] result;
	private int start;
	private int stop;
	
	public ParallelMatrixRows(int[][] matrix, int start, int stop, int dim) {
		this.matrix=matrix;
		this.start=start;
		this.stop=stop;
		this.result=new int[dim];
	}

	@Override
	public void run() {
		for(int i=start;i<=stop;i++) {
			result[i]=0;
			for(int j=0;j<matrix[0].length;j++) {
				result[i]+=matrix[i][j];
			}
		}
		
	}
			 
	public int getStart() {
		return this.start;
	}
	
	public int getStop() {
		return this.stop;
	}
	
	public int getResultElement(int index) {
		return this.result[index];
	}
}
