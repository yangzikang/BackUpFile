package p1;
import java.io.*;
public class TestFile{
	public static void main(String[] args){
		
		FileOutputStream fos = null;
		FileInputStream fis=null;
		File f = new File(".\\code\\mytemp.txt");  //��1���ļ�f�ñ���·���µ�code�ļ����µ�mytemp��ʼ��
		try{
		
		fos=new FileOutputStream(f);  //��2��fos���ļ���ʼ��
		fos.write(49);
		if(f.exists())   //��3���ж��ļ��Ƿ����
		{
			System.out.println(f.getName()); //��4�������ȡ���ļ���
			System.out.println(f.getPath());   //��5�������ȡ���ļ�·��
			System.out.println(f.getParent());  //��6�������һ�����ļ�����
			
		}
	}
	catch(Exception ex)
	{ex.printStackTrace(); }
	try{
			 
			fis=new FileInputStream(f);  //��7��fis���ļ���ʼ��
			System.out.println(fis.read());//��8��������ļ��ж��ĵ�һ���ֽ�
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
			
		}
	}
}

