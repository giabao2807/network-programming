
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class readPdfServlet
 */
@WebServlet("/readPdfServlet")
public class readPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public readPdfServlet() {
		super();
	}

	static byte[] data = null;
	static int count = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/pdf");
		try {
			count++;
			int cc = count;
			// Đọc file pdf có trong máy
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream("C:/Users/ASUS/Downloads/QD_4768_0001.pdf"));
			System.out.println("Read file "+cc);
			if (data==null)
				data = bis.readAllBytes();
			
			// Gửi nội dung của file đó
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(data);
			System.out.println("send file "+cc);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
