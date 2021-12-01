package tcpCalculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.Vector;

public class CalculatorServer {
	private String input;
	private String output;

	private boolean isStarted;
	private ServerSocket serverSocket;
	private int Port = 9999;

	Vector<Xuly> clients = new Vector();

	public static void main(String[] args) {
		new CalculatorServer();
	}

	public CalculatorServer() {
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
			// TODO: handle exception
		}
	}

	class Xuly extends Thread {
		CalculatorServer cs;
		Socket soc;
		boolean isAcceptStarted = false;
		DataInputStream dis;
		DataOutputStream dos;

		public Xuly(Socket soc, CalculatorServer cs) {
			this.soc = soc;
			this.cs = cs;
		}

		@Override
		public void run() {
			try {
				System.out.println("Connect successed!");
				isAcceptStarted = true;
				dis = new DataInputStream(soc.getInputStream());
				dos = new DataOutputStream(soc.getOutputStream());

				while (isAcceptStarted) {
					input = dis.readUTF();
					System.out.println("input: " + input);
					output = "Result: " + execute(input);
					dos.writeUTF(output);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (soc != null)
						soc.close();
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		private String execute(String str) {
			String v = "";
			Stack<String> numst = new Stack<String>();
			Stack<Character> opest = new Stack<Character>();
			String num = "";

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);

				switch (c) {
				default:
					return "Error!";
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '.':
					num += c;
					if (i == str.length() - 1) {
						if (!opest.isEmpty()) {
							numst.push(getValue(numst.pop(), opest.pop(), num));
							while (!opest.isEmpty()) {
								String n2 = numst.pop(), n1 = numst.pop();
								numst.push(getValue(n1, opest.pop(), n2));
							}
						} else
							return num;
					}
					break;
				case '+':
				case '-':
					if (i == 0) {
						num += c;
						break;
					}
				case '*':
				case '/':
				case '(':
					if (!num.equals("")) {
						numst.push(num);
						num = "";
					}
					if (opest.isEmpty()) {
						opest.push(c);
					} else {
						if (c == '(') {
							opest.push(c);
							break;
						}
						while (!opest.isEmpty() && (opest.lastElement() != '(')
								&& prio(c) <= prio(opest.lastElement())) {
							String n2 = numst.pop(), n1 = numst.pop();
							numst.push(getValue(n1, opest.pop(), n2));
						}
						opest.push(c);
					}
					break;
				case ')':
					if (!num.equals("")) {
						numst.push(num);
						num = "";
					}
					while ((!opest.isEmpty()) && opest.lastElement() != '(') {
						String n2 = numst.pop(), n1 = numst.pop();
						numst.push(getValue(n1, opest.pop(), n2));
					}
					opest.pop();
				}
			}

			while (!opest.isEmpty()) {
				String n2 = numst.pop(), n1 = numst.pop();
				numst.push(getValue(n1, opest.pop(), n2));
			}
			v = numst.pop();
			return v;
		}

		private String getValue(String a, char o, String b) {
			float af = Float.valueOf(a);
			float bf = Float.valueOf(b);
			switch (o) {
			case '+':
				return String.valueOf(af + bf);
			case '-':
				return String.valueOf(af - bf);
			case '*':
				return String.valueOf(af * bf);
			case '/':
				if (bf == 0)
					return "devided by zero";
				return String.valueOf((float) af / bf);
			default:
				return "error";
			}
		}

		private int prio(char c) {
			if (c == '+' || c == '-')
				return 0;
			if (c == '(')
				return 1;
			if (c == '*' || c == '/')
				return 2;
			return -1;
		}
	}

}
