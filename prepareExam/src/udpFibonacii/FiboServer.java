package udpFibonacii;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FiboServer {
	public static void main(String[] args) {
		new FiboServer();
	}

	static boolean isPerfectSquare(int x) {
		int s = (int) Math.sqrt(x);
		return (s * s == x);
	}

	static boolean isFibonacci(int n) {
		return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
	}

	public FiboServer() {
		try {
			DatagramSocket soc = new DatagramSocket(5000);
			while (true) {
				try {
					byte redata[] = new byte[1000];
					DatagramPacket re = new DatagramPacket(redata, redata.length);
					soc.receive(re);
					String str = new String(re.getData());
					System.out.println(str);
					String strtg = "";
					boolean check = false;
					if (str.matches("\\d+")) {
						strtg += "khong phai so";
					} else {
						int n = Integer.parseInt(str.trim());
						check = isFibonacci(n);
						if (check) {
							strtg += "stop";

						} else {
							strtg += "false";
						}
					}
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