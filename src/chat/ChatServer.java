package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	Vector<Xuly> clients = new Vector();

	public static void main(String[] args) {
		new ChatServer();
	}

	public ChatServer() {
		try {
			ServerSocket server = new ServerSocket(80);
			while (true) {
				Socket soc = server.accept();
				Xuly x = new Xuly(soc, this);
				clients.add(x);
				x.start();
			}
		} catch (Exception e) {

		}
	}

	class Xuly extends Thread {
		ChatServer cs;
		Socket soc;
		String name;

		public Xuly(Socket soc, ChatServer cs) {
			this.soc = soc;
			this.cs = cs;
		}

		public void run() {
			try {
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
				DataInputStream dis = new DataInputStream(soc.getInputStream());

				dos.writeUTF("Please input your name");
				name = dis.readUTF();
				while (true) {
					// Nhanthongdiep
					String mgs = dis.readUTF();
					for (Xuly c : cs.clients) {
						try {
							DataOutputStream dos1 = new DataOutputStream(c.soc.getOutputStream());
							dos1.writeUTF(name + "> " + mgs);
						} catch (Exception e) {

						}
					}

				}
			} catch (Exception e) {

			}

		}
	}
}
