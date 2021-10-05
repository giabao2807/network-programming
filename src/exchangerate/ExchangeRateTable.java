package exchangerate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;

import javax.swing.JFrame;

public class ExchangeRateTable extends JFrame implements Runnable {

	public static void main(String[] args) {
		new Thread(new ExchangeRateTable()).start();
	}
	BufferedImage img;
	Graphics g;
	public ExchangeRateTable() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(3);
		img = new BufferedImage(400, 300, BufferedImage.TYPE_4BYTE_ABGR);
		g = img.getGraphics();
		this.setVisible(true);
	}
	Vector<Double> tygia = new Vector<Double>();
	public void run() {
		byte buf[] = new byte[1000];
		while (true) {
			try {
				DatagramSocket soc = new DatagramSocket();
				String str = "ExchangeRateUSDtoVND";
				DatagramPacket sedata = new DatagramPacket(str.getBytes(), str.length(),
						InetAddress.getByName("localhost"), 5000);
				soc.send(sedata);
				
				DatagramPacket redata = new DatagramPacket(buf, 1000);
				soc.receive(redata);
				Double tg = Double.parseDouble(new String(redata.getData()).substring(0,redata.getLength()));
				tygia.add(tg);
				if (tygia.size()>100)
					tygia.remove(0);
				repaint();
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}
	
	public void paint(Graphics g1) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		int Ox = 50;
		int Oy = 250;
		
		g.setColor(Color.GREEN);
		g.drawRect(50 , 50 , 300, 200);
		
		g.setColor(Color.BLUE);
		for (int i = 1;i<tygia.size();i++) {
			int x1 = Ox + 3 * (i-1);
			int y1 = (int)(Oy - tygia.get(i-1)/200.0);
			
			int x2 = Ox + 3 * (i);
			int y2 = (int)(Oy - tygia.get(i)/200.0);
			
			g.drawLine(x1, y1, x2, y2);
		}
		g1.drawImage(img, 0, 0, null);
	}

}
