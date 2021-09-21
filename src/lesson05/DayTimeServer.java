package lesson05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {
	private final static int daytimePort =5000;
	public static void main(String[] args) {
		ServerSocket theServer;
		try {
			theServer = new ServerSocket(daytimePort);
			while(true) {
				Socket theConnect = theServer.accept();
				new Xuly(theConnect);
			}
		} catch(Exception e) {
			
		}
		
	}

}
class Xuly extends Thread{
	Socket soc;
	public Xuly(Socket soc) {
		this.soc=soc;
	}
	public void run() {
		try {
			soc.setSoTimeout(1000);
			System.out.println(soc.getInetAddress());
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			
			String t = dis.readUTF();
			if(t.length()==0) {
				return;
			}
			System.out.println(", " + t + ", ");
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			Date d = new Date();
			dos.writeUTF(d.toString());
			soc.close();
			System.out.println("Done");
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
