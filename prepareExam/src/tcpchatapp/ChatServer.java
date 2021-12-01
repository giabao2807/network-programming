package tcpchatapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	Vector<Xuly> clients = new Vector();

	private boolean isStarted;
	private ServerSocket serverSocket;
	private int Port = 2807;

	public static void main(String[] args) {
		new ChatServer();
	}

	public ChatServer() {
		try {

			serverSocket = new ServerSocket(Port);
			isStarted = true;
			System.out.println("Server is started with port: " + Port);

			while (isStarted) {
				Socket soc = serverSocket.accept();
				Xuly x = new Xuly(soc, this);
				clients.add(x);
				x.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	class Xuly extends Thread {
		ChatServer cs;
		Socket soc;

		DataInputStream dis;
		DataOutputStream dos;
		boolean isAcceptStarted = false;

		public Xuly(Socket soc, ChatServer cs) {
			this.soc = soc;
			this.cs = cs;
		}

		public void run() {
			try {

				System.out.println("Connect successed!");
				isAcceptStarted = true;

				dos = new DataOutputStream(soc.getOutputStream());
				dis = new DataInputStream(soc.getInputStream());

				while (isAcceptStarted) {
					// Nhanthongdiep
					String mgs = dis.readUTF();
					for (Xuly c : cs.clients) {
						try {
							DataOutputStream dos1 = new DataOutputStream(c.soc.getOutputStream());
							dos1.writeUTF(mgs);
						} catch (Exception e) {

						}
					}

				}
			} catch (Exception e) {

			} finally {
				try {
					if (soc != null)
						soc.close();
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
