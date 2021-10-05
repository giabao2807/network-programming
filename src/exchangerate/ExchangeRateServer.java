package exchangerate;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ExchangeRateServer extends Thread {

	public static void main(String[] args) {
		new ExchangeRateServer();
	}

	Map<String, Double> tygia = new HashMap<String, Double>();
	Random rand = new Random();

	public void run() {
		while (true) {
			for (String key : tygia.keySet()) {
				double tg = tygia.get(key);
				tg = (1.0 + rand.nextDouble() * 0.02 - 0.01) * tg;
				tygia.put(key, tg);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	public ExchangeRateServer() {
		tygia.put("USD", 23000d);
		tygia.put("VND", 1d);
		tygia.put("JPY", 230d);
		this.start();
		try {
			DatagramSocket soc = new DatagramSocket(5000);
			while (true) {
				try {
					byte redata[] = new byte[1000];
					DatagramPacket re = new DatagramPacket(redata, redata.length);
					soc.receive(re);
					String str = new String(re.getData());
					// System.out.println(new String(re.getData()));
					// Xuly
					String cmd = str.substring(0, 12);
					String t1 = str.substring(12, 15);
					String to = str.substring(15, 17);
					String t2 = str.substring(17, 20);
					if (!cmd.equals("ExchangeRate") || !to.equals("to"))
						continue;
					double tg = tygia.get(t1) / tygia.get(t2);
					String strtg = "" + tg;
					DatagramPacket se = new DatagramPacket(strtg.getBytes(), strtg.length(), re.getAddress(),
							re.getPort());
					soc.send(se);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {

		}
	}

}

