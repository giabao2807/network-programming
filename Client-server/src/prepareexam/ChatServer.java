package prepareexam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	Vector<Xuly> clients = new Vector<Xuly>();

	public static void main(String[] args) {
		new ChatServer();
	}

	public ChatServer() {
		ServerSocket theserver;
		try {
			theserver = new ServerSocket(9090);
			while (true) {
				Socket soc = theserver.accept();
				Xuly x = new Xuly(soc, this);
				clients.add(x);
				x.start();
			}
		} catch (Exception e) {

		}
	}

	class Xuly extends Thread {
		Socket soc;
		ChatServer cs;
		String name;

		public Xuly(Socket soc, ChatServer cs) {
			this.soc = soc;
			this.cs = cs;
		}

		@Override
		public void run() {
			try {
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
				DataInputStream dis = new DataInputStream(soc.getInputStream());

				dos.writeUTF("Please enter your name:");
				name = dis.readUTF();
				while (true) {
					String mess = dis.readUTF();
					for (Xuly c : cs.clients) {
						try {
							DataOutputStream dos1 = new DataOutputStream(c.soc.getOutputStream());
							dos.writeUTF(name + "> " + mess);
						} catch (Exception e) {

						}
					}
				}
			} catch (Exception e) {

			}
		}
	}
}
