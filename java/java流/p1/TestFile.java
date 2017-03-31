package p1;
import java.io.*;
public class TestFile{
	public static void main(String[] args){
		
		FileOutputStream fos = null;
		FileInputStream fis=null;
		File f = new File(".\\code\\mytemp.txt");  //（1）文件f用本地路径下的code文件夹下的mytemp初始化
		try{
		
		fos=new FileOutputStream(f);  //（2）fos用文件初始化
		fos.write(49);
		if(f.exists())   //（3）判断文件是否存在
		{
			System.out.println(f.getName()); //（4）输出获取的文件名
			System.out.println(f.getPath());   //（5）输出获取的文件路径
			System.out.println(f.getParent());  //（6）输出上一级的文件夹名
			
		}
	}
	catch(Exception ex)
	{ex.printStackTrace(); }
	try{
			 
			fis=new FileInputStream(f);  //（7）fis用文件初始化
			System.out.println(fis.read());//（8）输出从文件中读的第一个字节
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
			
		}
	}
}

