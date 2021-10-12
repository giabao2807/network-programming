package prepareexam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class DatetimeClient {
	public static void main(String[] args) {
		try {
			Socket soc = new Socket("localhost",9090);
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			dos.writeUTF("DinhGiaBao");
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			System.out.println(dis.readUTF());
		} catch(Exception e) {
			
		}
	}
}
