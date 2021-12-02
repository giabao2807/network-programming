package tcpdate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DateServer {
	private String input;
	private String output;

	private boolean isStarted;
	private ServerSocket serverSocket;
	private int Port = 9999;

	private static String PARTTERN = "dd/MM/yyyy";
	private static DateFormat df = new SimpleDateFormat(PARTTERN);

	Vector<Xuly> clients = new Vector();

	public static void main(String[] args) {
		new DateServer();
	}

	public DateServer() {
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
		DateServer ds;
		Socket soc;

		boolean isAcceptStarted = false;
		DataInputStream dis;
		DataOutputStream dos;

		public Xuly(Socket soc, DateServer ds) {
			this.soc = soc;
			this.ds = ds;
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
				// TODO: handle exception
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

		private String execute(String input) {
			String rs = "";

			if (isValidDate(input)) {
				int d = Integer.valueOf(input.substring(0, 2));
				int m = Integer.valueOf(input.substring(3, 5));
				int y = Integer.valueOf(input.substring(6, 10));
			if (input.charAt(2) == '/' && input.charAt(5) == '/') {
				@SuppressWarnings("deprecation")
				Date date = new Date(y-1900, m-1, d);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, 7);
				date = c.getTime();
				rs = df.format(date);
			}
			} else {
				LocalDate date = LocalDate.now();
				rs = df.format(date);
			}

			return rs;
		}

		private boolean isValidDate(String input) {
			try {
				df.parse(input);
			} catch (Exception e) {
				return false;
			}
			return true;
		}

	}
}
