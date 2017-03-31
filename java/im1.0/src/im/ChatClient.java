 package im;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	String haoyou=null;
	String yonghu2=null;
	Socket s = null;
	DataOutputStream dos = null; //д
	DataInputStream dis = null; //��
	private boolean bConnected = false;

	TextField tfTxt = new TextField();

	TextArea taContent = new TextArea();
	
	Thread tRecv = new Thread(new RecvThread()); 

	/*public  void chaungjian() {
		new ChatClient().launchFrame(); 
	}*/

	public void lauchFrame(String yonghu1,String haoyou){
		setLocation(400, 300);
		yonghu2=yonghu1;
		this.haoyou=haoyou;
		this.setSize(300, 300);
		setTitle(yonghu1+"��"+haoyou);
		add(tfTxt, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				disconnect();
				setVisible(false);
			}
			
		});
		tfTxt.addActionListener(new TFListener());
		setVisible(true);
		connect();
		
		tRecv.start();
	}
	public void launchFrame(String yonghu1) {
		setLocation(400, 300);
		yonghu2=yonghu1;
		setTitle("��"+yonghu1+"����");		
		this.setSize(300, 300);
		add(tfTxt, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				disconnect();
				setVisible(false);
			}
			
		});
		tfTxt.addActionListener(new TFListener());
		setVisible(true);
		connect();
		
		tRecv.start();
	}
	
	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			System.out.println("connected!");
			bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private class TFListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim();
			String yonghuming=yonghu2;
			//taContent.setText(str);
			tfTxt.setText("");
			
			try {
				if(haoyou!=null){
					dos.writeUTF(haoyou+" "+yonghuming+":"+str);
				}
				else{
				//System.out.println(s);
				dos.writeUTF(yonghuming+":"+str);
				}
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	private class RecvThread implements Runnable {

		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					String jiexi[]=str.split("\\s+");
					if(jiexi.length==1){
						taContent.setText(taContent.getText() + str + '\n');
					}
					else{
						if(jiexi[0].equals(yonghu2)){
							taContent.setText(taContent.getText() + jiexi[1] + '\n');
						}
					}
				}
			} catch (SocketException e) {
				System.out.println("�˳��ˣ�bye!");
			} catch (EOFException e) {
				System.out.println("�Ƴ��ˣ�bye - bye!");
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
	}
}
