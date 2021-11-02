
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/ReadpdfServelet" })
public class ReadpdfServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadpdfServelet() {

	}
	static byte[] data;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pdfFileName = "Lab1.pdf";
		String contextPath = getServletContext().getRealPath(File.separator);
		//System.out.println(contextPath);
		//System.out.println(contextPath + pdfFileName);
		File pdfFile = new File(contextPath + pdfFileName);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=" + pdfFileName);
		response.setContentLength((int) pdfFile.length());

		BufferedInputStream bis = new BufferedInputStream( new FileInputStream(pdfFile)); 
		if (data==null) 
			 data = bis.readAllBytes();
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		bos.write(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
