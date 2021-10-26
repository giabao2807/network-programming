package exam;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class TimSoServer {
	public static void main(String[] args) {
		new TimSoServer();
	}
	static int n=5;
	static int matran[][] = new int[n][n];
	static List<Point> dadanh = new ArrayList<Point>();
	static Vector<Xuly> clients = new Vector<Xuly>();
	Random rd = new Random();
	
	public TimSoServer() {
		try {
		ServerSocket server = new ServerSocket(8080);
		//khoi tao ma tran de gui client
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matran[i][j] = i * n + j + 1;
			}
		}
		for (int r = 0; r < n * n; r++) {
			int i1 = rd.nextInt(n);
			int j1 = rd.nextInt(n);
			int i2 = rd.nextInt(n);
			int j2 = rd.nextInt(n);
			int tmp = matran[i1][j1];
			matran[i1][j1] = matran[i2][j2];
			matran[i2][j2] = tmp;
		}
		//Player1
		Socket s1 = server.accept();
		Xuly v1 = new Xuly(s1);
		clients.add(v1);
		
		//Player2
		Socket s2 = server.accept();
		Xuly v2 = new Xuly(s2);
		clients.add(v2);
		
		//Player3
		Socket s3 = server.accept();
		Xuly v3 = new Xuly(s3);
		clients.add(v3);
		
		//player4
		Socket s4 = server.accept();
		Xuly v4 = new Xuly(s4);
		clients.add(v4);
		
		
		//Du 4 player bat dau game gui ma tran
		if(clients.size()==4) {
			for(Xuly v: clients) {
				v.start();
			}
		}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
class Xuly extends Thread{
	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;
	
	public Xuly(Socket soc) {
		try {
			this.soc = soc;
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			dos.writeUTF(TimSoServer.n + "");
			for (int i = 0; i < TimSoServer.n; i++)
				for (int j = 0; j < TimSoServer.n; j++) {
					dos.writeUTF(TimSoServer.matran[i][j] + "");
					Point p = new Point(i, j);
					for (Point d : TimSoServer.dadanh) {
						if (i == d.x && j == d.y) {
							dos.writeUTF(i+"");
							dos.writeUTF(j+"");
						}
						else
							dos.writeUTF(-1+"");
					}
				}
		} catch (Exception e) {
		}
	}
	@Override
	public void run() {
		try {
loop:			while(true) {
				
				int ix = Integer.parseInt(dis.readUTF());
				int iy = Integer.parseInt(dis.readUTF());
				
				// kiem tra ca 4 client da vao hay chua?
				if (TimSoServer.clients.size()<4) continue;
				
				//ktra toa do phu hop hay k
				if (ix<0 || ix>=TimSoServer.n) continue;
				if (iy<0 || iy>=TimSoServer.n) continue;
				for (Point d : TimSoServer.dadanh) {
					if (ix == d.x && iy == d.y)
						continue loop;
				}
				TimSoServer.dadanh.add(new Point(ix,iy));
				
				//Gui toa do cho cac client dang co
				for (Xuly c : TimSoServer.clients) {
					try {
						c.dos.writeUTF(ix+"");
						c.dos.writeUTF(iy+"");
						c.dos.writeUTF(TimSoServer.clients.indexOf(this)+"");
					}catch(Exception e) {
						
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
