package caro;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class CaroServer extends Thread {

	public static void main(String[] args) {
		new CaroServer();
	}
	static int n = 15;
	static List<Point> dadanh = new ArrayList<Point>();
	static Vector<Xuly> clients = new Vector<Xuly>();

	public CaroServer() {
		try {
			ServerSocket server = new ServerSocket(81);
			
			// Viewer
			while (true) {
				Socket s = server.accept();
				Xuly v = new Xuly(s);
				clients.add(v);
				if(clients.size()<=2) {
					v.start();
				}
			}

		} catch (Exception e) {

		}
	}
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			String cmd = sc.nextLine();
			if(cmd.equals("kickall")) {
				dadanh = new ArrayList<Point>();
				clients = new Vector<Xuly>();
			}
		}
	}

}

class Xuly extends Thread {
	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;

	public Xuly(Socket soc) {
		try {
			this.soc = soc;
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			for (Point p : CaroServer.dadanh) {
				dos.writeUTF(p.x+"");
				dos.writeUTF(p.y+"");
			}
		} catch (Exception e) {
		}
	}
	public void run() {
		try {
loop:		while(true) {
				int ix = Integer.parseInt(dis.readUTF());
				int iy = Integer.parseInt(dis.readUTF());
				///Can phai lam gi??!!!!!
				// kiem tra ca 2 client da vao hay chua?
				if (CaroServer.clients.size()<2) continue;
				
				// Kiem tra co phai luot danh cua client nay hay khong!! 
				if (this == CaroServer.clients.get(0) && CaroServer.dadanh.size()%2!=0) continue;
				if (this == CaroServer.clients.get(1) && CaroServer.dadanh.size()%2==0) continue;
				
				// Kiem tra toa do co phu hop khong
				if (ix<0 || ix>=CaroServer.n) continue;
				if (iy<0 || iy>=CaroServer.n) continue;
				for (Point d : CaroServer.dadanh) {
					if (ix == d.x && iy == d.y)
						continue loop;
				}
				CaroServer.dadanh.add(new Point(ix,iy));
				// Gui toa do cho tat ca client dang co!!
				for (Xuly c : CaroServer.clients) {
					try {
						c.dos.writeUTF(ix+"");
						c.dos.writeUTF(iy+"");
					}catch(Exception e) {
						
					}
				}
			}
		}catch(Exception e) {
			
		}
	}
}
