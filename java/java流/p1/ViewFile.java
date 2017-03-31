package p1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ViewFile extends JFrame implements ActionListener{
  private JButton jbtView = new JButton("View");

  private JTextField jtfFilename = new JTextField(12);

  private JTextArea jtaFile = new JTextArea();

  public static void main(String[] args){
    ViewFile frame = new ViewFile();
    frame.setTitle("View File");
    frame.setDefaultCloseOperation(3);
    frame.setSize(400, 300);
    frame.setVisible(true);
  }

   public ViewFile() {
    Panel p = new Panel();
    p.setLayout(new BorderLayout());
    p.add(new Label("Filename"), BorderLayout.WEST);
    p.add(jtfFilename, BorderLayout.CENTER);
    jtfFilename.setBackground(Color.yellow);
    jtfFilename.setForeground(Color.red);
    p.add(jbtView, BorderLayout.EAST);

    JScrollPane jsp = new JScrollPane(jtaFile);

    getContentPane().add(jsp, BorderLayout.CENTER);
    getContentPane().add(p, BorderLayout.SOUTH);

    jbtView.addActionListener(this);
  }

   public void actionPerformed(ActionEvent e){
    if (e.getSource() == jbtView)
      showFile();
  }

  private void showFile(){
    BufferedReader infile = null;
    String filename = jtfFilename.getText().trim();

    String inLine;

    try{
      infile = new BufferedReader(new FileReader(filename));

      while ((inLine = infile.readLine()) != null){
        jtaFile.append(inLine + '\n');
      }
    }
    catch (FileNotFoundException ex){
      System.out.println("File not found: " + filename);
    }
    catch (IOException ex){
      System.out.println(ex.getMessage());
    }
    finally{
      try{
        if (infile != null) infile.close();
      }
      catch (IOException ex){
        System.out.println(ex.getMessage());
      }
    }
  }
}

//输入路径后点VIEW能读取文件中的内容
