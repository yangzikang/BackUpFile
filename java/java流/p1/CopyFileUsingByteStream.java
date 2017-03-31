package p1;


import java.io.*;

public class CopyFileUsingByteStream{
   public static void main(String[] args){ 
    FileInputStream fis = null;
    FileOutputStream fos = null;

    try{ 
      fis = new FileInputStream(new File("FInputFile.txt"));

      File file = new File("FOutputFile.txt");
      if (file.exists())
      {
        System.out.println("file  already exists");
        return;
      }
      else
        fos = new FileOutputStream("FOutputFile.txt");

        System.out.println("The file FInputFile " + " has "+
        fis.available() + " bytes");

      int r;
        while ((r = fis.read()) != -1)
      {
        fos.write(r);
      }
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("File FInputFile not found: " +ex.getMessage());
    }
    catch (IOException ex)
    {
      System.out.println(ex.getMessage());
    }
    finally
    {
      try
      {
        if (fis != null) fis.close();
        if (fos != null) fos.close();
      }
      catch (IOException ex)
      {
        System.out.println(ex);
      }
    }
  }
}
