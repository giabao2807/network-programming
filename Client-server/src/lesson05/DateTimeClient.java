package lesson05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class DateTimeClient {
	public static void main(String[] args) {
		try {
			//IP th Tuan 118.71.133.217
			Socket soc = new Socket("localhost",5000);
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			dos.writeUTF("102190252,Dinh Gia Bao");
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			System.out.println(dis.readUTF());
		} catch(Exception e) {
		}
	}
}
