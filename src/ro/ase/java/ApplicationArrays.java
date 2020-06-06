package ro.ase.java;

import java.util.Random;

import ro.ase.java.utils.ParallelAddition;
import ro.ase.java.utils.ParallelDotProduct;

public class ApplicationArrays {
	
	public static final int noThreads=4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime=0;
		long stopTime=0;
		int start=0;
		int stop=0;
		int arrayDim=10000;
		Random random= new Random();
		int[] v1=random.ints(arrayDim, 0,10).toArray();
		int[] v2=random.ints(arrayDim, 0,10).toArray();
		
		startTime=System.currentTimeMillis();
		
		int[] resultArray=new int[arrayDim];
		Thread[] arrayThreads=new Thread[noThreads];
		ParallelAddition[] arrayThreadsAddition= new ParallelAddition[noThreads];
		
		// Parallel addition of two large arrays
		for(int i=0;i<noThreads;i++) {
			start=i*(arrayDim/noThreads);
			stop=(i+1)*(arrayDim/noThreads)-1;
			arrayThreadsAddition[i]=new ParallelAddition(v1, v2,start, stop);
			arrayThreads[i]= new Thread(arrayThreadsAddition[i]);
		}
		
		for(int i=0;i<noThreads;i++) {
			arrayThreads[i].start();
		}
		
		for(int i=0;i<noThreads;i++) {
			try {
				arrayThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<noThreads;i++) {
			for(int j=arrayThreadsAddition[i].getStart();j<=arrayThreadsAddition[i].getStop();j++) {
				resultArray[j]=arrayThreadsAddition[i].getRezultatIndexat(j);
			}
		}
		
		stopTime=System.currentTimeMillis();
		for(int i=0;i<arrayDim;i++) {
			System.out.println(v1[i]+" + "+v2[i]+" = "+resultArray[i]+" ");
		}
		System.out.println("Multithreading time(addition): "+(stopTime-startTime));
		
		//Parallel Dot Product of two large arrays
		int resultDotProduct=0;
		startTime=System.currentTimeMillis();
		ParallelDotProduct[] arrayThreadsDotProduct= new ParallelDotProduct[noThreads];
		
		for(int i=0;i<noThreads;i++) {
			start=i*(arrayDim/noThreads);
			stop=(i+1)*(arrayDim/noThreads)-1;
			arrayThreadsDotProduct[i]=new ParallelDotProduct(v1, v2,start, stop);
			arrayThreads[i]= new Thread(arrayThreadsDotProduct[i]);
		}
		
		for(int i=0;i<noThreads;i++) {
			arrayThreads[i].start();
		}
		
		for(int i=0;i<noThreads;i++) {
			try {
				arrayThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<noThreads;i++) {
			for(int j=arrayThreadsDotProduct[i].getStart();j<=arrayThreadsDotProduct[i].getStop();j++) {
				resultDotProduct+=arrayThreadsDotProduct[i].getRezult();
			}
		}
		stopTime=System.currentTimeMillis();
		System.out.println("Dot product result: "+resultDotProduct);
		System.out.println("Multithreading time(dot product): "+(stopTime-startTime));
	}

}
