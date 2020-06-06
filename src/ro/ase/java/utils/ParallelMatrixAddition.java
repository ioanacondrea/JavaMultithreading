package ro.ase.java.utils;

public class ParallelMatrixAddition implements Runnable {
	private int[][] matrix1;
	private int[][] matrix2;
	private int[][] result;
	private int start;
	private int stop;
	
	public ParallelMatrixAddition(int[][] matrix1, int[][] matrix2, int start, int stop, int dim) {
		this.matrix1=matrix1;
		this.matrix2=matrix2;
		this.start=start;
		this.stop=stop;
		this.result=new int[dim][dim];
	}

	@Override
	public void run() {
		for(int i=start;i<=stop;i++) {
			for(int j=0;j<matrix1[0].length;j++) {
				result[i][j]=matrix1[i][j]+matrix2[i][j];
			}
		}
		
	}
			 
	public int getStart() {
		return this.start;
	}
	
	public int getStop() {
		return this.stop;
	}
	
	public int getElement(int i, int j) {
		return this.result[i][j];
	}
}
