package imServer;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	
	List<Client> clients = new ArrayList<Client>();
	
	
	public static void main(String[] args) throws Exception{
		new ChatServer().start();
	}
	
	public void start() throws Exception{
			ss = new ServerSocket(8888);
			started = true;
			while(started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("a client connected!");
				new Thread(c).start();
				clients.add(c);
				
			}
			ss.close();
	}
//*********************内部类*********************
	class Client implements Runnable{
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		
		public Client(Socket s) throws Exception{
			this.s = s;
			
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
		}
		
		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("对方退出了！我从List里面去掉了！");
				//e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println(str);
					for(int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);
						c.send(str);

					}
				}
			} catch (EOFException e) {
				System.out.println("Client closed!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}//finally
		}//run
		
	}//class
}//外层class
