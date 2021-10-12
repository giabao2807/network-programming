package exam;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class TimSoClient extends JFrame implements MouseListener,Runnable{
	public static void main(String[] args) {
		new TimSoClient();
	}
	int n = 5;
	int s = 100;
	int os = 50;
	int matran[][] = new int[n][n];
	Random rand = new Random();
	List<Point> dadanh = new ArrayList<Point>();
	DataOutputStream dos;
	DataInputStream dis;
	
	public TimSoClient() {
		this.setSize(n * s + 2 * os, n * s + 2 * os);
		this.setTitle("Caro");
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);

		//Ketnoi toi server lay ma tran
		try {
			Socket soc = new Socket("localhost", 80);
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			n = Integer.parseInt(dis.readUTF());
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					matran[i][j] = Integer.parseInt(dis.readUTF());
					if (i != -1)
						dadanh.add(new Point(i, j));
				}
			}
		} catch(Exception e) {
			
		}
		new Thread(this).start();
		this.setVisible(true);
	}
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		
		
		for (int i = 0; i < dadanh.size(); i++) {
			int ix = dadanh.get(i).x;
			int iy = dadanh.get(i).y;
			int x = os + ix * s;
			int y = os + iy * s;
			g.fillRect(x, y, s, s);;
		}
		
		g.setColor(Color.BLACK);
		for (int i = 0; i <= n; i++) {
			g.drawLine(os, os + i * s, os + n * s, os + i * s);
			g.drawLine(os + i * s, os, os + i * s, os + n * s);
		}
		g.setFont(new Font("arial", Font.BOLD, s/3));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				String str = matran[i][j]+"";
				if (matran[i][j]<10) str = "00" + str;
				else if (matran[i][j]<100) str = "0" + str;
				int x = os + i * s + s / 2 - s / 4;
				int y = os + j * s + s / 2 + s / 4 - s/8;
				g.drawString(str, x, y);
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				int ix= Integer.parseInt(dis.readUTF());
				int iy= Integer.parseInt(dis.readUTF());
				dadanh.add(new Point(ix, iy));
				this.repaint();
			} catch(Exception e2) {
				break;
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x < os || x >= os + n * s)
			return;
		if (y < os || y >= os + n * s)
			return;
		int ix = (x - os) / s;
		int iy = (y - os) / s;
		for (Point d : dadanh) {
			if (ix == d.x && iy == d.y)
				return;
		}
		//gui thong tin den server
		try {
			dos.writeUTF(ix+"");
			dos.writeUTF(iy+"");
		} catch(Exception e1) {
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
