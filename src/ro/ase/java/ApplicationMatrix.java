package ro.ase.java;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import ro.ase.java.utils.ParallelAddition;
import ro.ase.java.utils.ParallelMatrixAddition;
import ro.ase.java.utils.ParallelMatrixRows;
public class ApplicationMatrix {
	public static final int noThreads=2;
	public static void main(String[] args) {
		
		long startTime=0;
		long stopTime=0;
		int start=0;
		int stop=0;
		int dim=100;
		int[][] matrix1= new int[dim][dim];
		int[][] matrix2= new int[dim][dim];
		
		//Reading two matrix from file
		try {
			File f1= new File("..\\matrix1.txt");
			File f2= new File("..\\matrix2.txt");
			Scanner scanner1 = new Scanner(f1);
			Scanner scanner2 = new Scanner(f2);
			for(int i=0;i<dim;i++)
				for(int j=0;j<dim;j++) {
					if(scanner1.hasNextInt())
						matrix1[i][j]=scanner1.nextInt();
				}
			
			for(int i=0;i<dim;i++)
				for(int j=0;j<dim;j++) {
					if(scanner2.hasNextInt())
						matrix2[i][j]=scanner2.nextInt();
				}
			scanner1.close();
			scanner2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		startTime=System.currentTimeMillis();
		int[][] resultMatrix= new int[dim][dim];
		Thread[] arrayThreads=new Thread[noThreads];
		ParallelMatrixAddition[] arrayThreadsAdd= new ParallelMatrixAddition[noThreads];
		for(int i=0;i<noThreads;i++) {
			start=i*(dim/noThreads);
			stop=(i+1)*(dim/noThreads)-1;
			arrayThreadsAdd[i]=new ParallelMatrixAddition(matrix1, matrix2,start, stop,dim);
			arrayThreads[i]= new Thread(arrayThreadsAdd[i]);
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
			for(int j=arrayThreadsAdd[i].getStart();j<=arrayThreadsAdd[i].getStop();j++) {
				for(int k=0;k<dim;k++)
				resultMatrix[j][k]=arrayThreadsAdd[i].getElement(j,k);
			}
		}
		stopTime=System.currentTimeMillis();
		
		try {
			File f= new File("resultMatrix.txt");
			FileWriter fs= new FileWriter(f);
			for(int i=0;i<dim;i++)
				{for(int j=0;j<dim;j++) {
					fs.write(resultMatrix[i][j]+" ");
				}
				fs.write("\n");
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Multithreading time(adding up two matrix): " +(stopTime-startTime));
		
		//Adding up all elements of each row in a matrix(using the matrix1):
		startTime=System.currentTimeMillis();
		int[] resultArray= new int[dim];
		ParallelMatrixRows[] arrayThreadsRows= new ParallelMatrixRows[noThreads];
		
		for(int i=0;i<noThreads;i++) {
			start=i*(dim/noThreads);
			stop=(i+1)*(dim/noThreads)-1;
			arrayThreadsRows[i]=new ParallelMatrixRows(matrix2,start, stop,dim);
			arrayThreads[i]= new Thread(arrayThreadsRows[i]);
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
			for(int j=arrayThreadsRows[i].getStart();j<=arrayThreadsRows[i].getStop();j++) {
				resultArray[j]=arrayThreadsRows[i].getResultElement(j);
			}
		}
		stopTime=System.currentTimeMillis();
		for(int i=0;i<dim;i++) {
			System.out.print(resultArray[i]+" ");
		}
		System.out.println();
		System.out.println("Multithreading time(adding up all elements of a row): " +(stopTime-startTime));
	}

}
