package udpCalculator;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class CalculatorServerConso {
	public static void main(String[] args) {
		new CalculatorServerConso();
	}

	public CalculatorServerConso() {
		try {
			DatagramSocket soc = new DatagramSocket(5000);
			while (true) {
				try {
					byte redata[] = new byte[1000];
					DatagramPacket re = new DatagramPacket(redata, redata.length);
					soc.receive(re);
					String str = new String(re.getData());
					System.out.println(str);
				
					String dau = "";
					
					int index = 0;
					int i = 0;
					for (var item : str.trim().toCharArray()) {
						if (!(String.valueOf(item)).trim().matches("\\d+")) {
							dau += String.valueOf(item);
							index = i;
						}
						i++;
					}
					String tm1 = str.substring(0, index);
					double x = Double.parseDouble(tm1);
					System.out.println(tm1);
					String tm2 = str.substring(index + 1, str.length());
					double y = Double.parseDouble(tm2);
					System.out.println(tm2);
					System.out.println(dau);
					String rs = "";
					switch (dau.trim()) {
					case "+":
						rs += (x + y);
						break;
					case "/":
						rs += (x / y);
						break;
					case "-":
						rs += (x - y);
						break;
					case "*":
						rs += (x * y);
						break;
					default:
						rs = "dinh dang khong dung";
						break;
					}

					DatagramPacket se = new DatagramPacket(rs.getBytes(), rs.length(), re.getAddress(), re.getPort());
					soc.send(se);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}