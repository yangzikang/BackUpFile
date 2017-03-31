package p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class myBuffered {
	
	public static void main(String []args){
		String str;
		try {
			BufferedReader infile = new BufferedReader(new FileReader("D:\\test.txt"));
			while((str=infile.readLine())!=null){
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
