package ro.ase.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ApplicationFiles {
	public static void main(String[] args) {
		Random r= new Random();
		try {
			File f= new File("matrix2.txt");
			FileWriter fs= new FileWriter(f);
			for(int i=0;i<100;i++)
				{for(int j=0;j<100;j++) {
					fs.write(r.nextInt(10)+" ");
				}
				fs.write("\n");
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
