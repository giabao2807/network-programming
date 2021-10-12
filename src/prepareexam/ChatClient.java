package prepareexam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class ChatClient {
	public static void main(String[] args) {
		new ChatClient();
	}
	public ChatClient() {
		try {
			Scanner sc = new Scanner(System.in);
			Socket soc = new Socket("localhost", 5050);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			
			System.out.println(dis.readUTF());
			String name = sc.nextLine();
			dos.writeUTF(name);
			
			while(true) {
				String mess = sc.nextLine();
				dos.writeUTF(mess);
			}
			
		} catch(Exception e){
			
		}
	}
	class XulyNhan extends Thread{
		Socket soc;
		public XulyNhan(Socket soc) {
			this.soc = soc;
		}
		@Override
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(soc.getInputStream());
				while(true) {
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

