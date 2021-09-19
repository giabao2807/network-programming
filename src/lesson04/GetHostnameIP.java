package lesson04;

import java.net.InetAddress;
import java.util.Scanner;

public class GetHostnameIP {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			String name= sc.next();
			InetAddress[] ips = InetAddress.getAllByName(name);
			for(InetAddress ip : ips) {
				System.out.println(ip.getHostAddress());
				System.out.println(ip.getHostName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
