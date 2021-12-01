package tcphandlestring;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;



public class StringServer {
	private String input;
	private String output;

	private boolean isStarted;
	private ServerSocket serverSocket;
	private int Port = 2807;

	Vector<Xuly> clients = new Vector();

	public static void main(String[] args) {
		new StringServer();
	}

	public StringServer() {
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
			e.printStackTrace();		}
	}
	
	class Xuly extends Thread {
		StringServer ss;
		Socket soc;
		boolean isAcceptStarted = false;
		DataInputStream dis;
		DataOutputStream dos;

		public Xuly(Socket soc, StringServer ss) {
			this.soc = soc;
			this.ss = ss;
		}
		 
        public void send(String str) {
            try {
                dos.writeUTF(str);
            } catch (IOException e) {}
        }

		@Override
		public void run() {
			try {
                System.out.println("Connect successed!");
                isAcceptStarted = true;
                dis = new DataInputStream(soc.getInputStream());
                dos = new DataOutputStream(soc.getOutputStream());
                
                while(isAcceptStarted) {
                    input = dis.readUTF();
                    output = "Chuỗi: " + input
                        + "\nChuỗi in hoa: " + this.chuoiHoa(input)
                        + "\nChuỗi thường: " + this.chuoiThuong(input)
                        + "\nChuỗi vừa hoa vừa thường: " + this.chuoiVuaHoaVuaThuong(input)
                        + "\nSố từ của chuỗi: " + this.soTu(input);
                    this.send(output);
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (soc != null) soc.close();
                    if (dis != null) dis.close();
                    if (dos != null) dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
        public String chuoiVuaHoaVuaThuong(String str) {
            char c;
            String result = "";
            for (int i=0; i<str.length(); i++) {
                c  = str.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    c = (char) (c-32);
                } else if (c >= 'A' && c <= 'Z') {
                    c = (char) (c+32);
                }
                result += c;
            }
            return result;
        }
    
        public String chuoiHoa(String str) {
            char c;
            String result = "";
            for (int i=0; i<str.length(); i++) {
                c  = str.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    c = (char) (c-32);
                }
                result += c;
            }
            return result;
        }
    
        public String chuoiThuong(String str) {
            char c;
            String result = "";
            for (int i=0; i<str.length(); i++) {
                c  = str.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    c = (char) (c+32);
                }
                result += c;
            }
            return result;
        }
    
        public int soTu(String str) {
            int result = 0;
            for (String s : str.split(" ")) {
                if (!s.trim().equals("")) {
                    result++;
                }
            }
            return result;
        }

	}

}
