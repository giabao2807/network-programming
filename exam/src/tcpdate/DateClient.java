package tcpdate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DateClient {
	private static Scanner sc = new Scanner(System.in);
	private int Port = 9999;

	public static void main(String[] args) {
		new DateClient();
	}

	public DateClient() {
		try {
			Socket soc = new Socket("localhost", Port);
			
			
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			
			
			System.out.println("Please enter date:");
			String mgs = sc.nextLine();
			dos.writeUTF(mgs);
			
			System.out.println(dis.readUTF());
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}