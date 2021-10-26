package prepareexam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DatetimeServer {
	public static void main(String[] args) {
		try {
			ServerSocket serversoc = new ServerSocket(9090);
			while (true) {
				Socket connect = serversoc.accept();
				new Xuly(connect).start();
			}
		} catch (Exception e) {
		}
	}

}

class Xuly extends Thread {
	Socket soc;

	public Xuly(Socket soc) {
		this.soc = soc;
	}

	@Override
	public void run() {
		try {
			soc.setSoTimeout(1000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

			System.out.println(dis.readUTF());

			Date dt = new Date();
			dos.writeUTF(dt.toString());
			soc.close();
			System.out.println("done");
		} catch (Exception e) {

		}
	}
}
