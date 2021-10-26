

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UCLNBCNN
 */
@WebServlet("/UCLNBCNNServlet")
public class UCLNBCNN extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UCLNBCNN() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Integer A= Integer.parseInt(request.getParameter("A"));
		Integer B= Integer.parseInt(request.getParameter("B"));
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Ket qua la </h1>");
		out.println("UCLN la: " + USCLN(A, B));
		out.println("BCNN la: " + BSCNN(A, B));
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    public  int USCLN(int a, int b) {
        if (b == 0) return a;
        return USCLN(b, a % b);
    }
     
    /**
     * Tìm bội số chung nhỏ nhất (BSCNN)
     * 
     * @param a: số nguyên dương
     * @param b: số nguyên dương
     * @return BSCNN của a và b
     */
    public  int BSCNN(int a, int b) {
        return (a * b) / USCLN(a, b);
    }
//	 public BigInteger USCLN(BigInteger a, BigInteger b) {
//	        BigInteger temp1 = a;
//	        BigInteger temp2 = b;
//	        while (temp1 != temp2) {
//	            if (temp1.compareTo(temp2) ==1){
//	                temp1 = temp1.subtract(temp2);
//	            } else {
//	                temp2 = temp2.subtract(temp1);
//	            }
//	        }
//	        BigInteger uscln = temp1;
//	        return uscln;
//	    }
//	 
//	 public BigInteger BSCNN(BigInteger a, BigInteger b) {
//	        return (a.multiply(b)).divide(USCLN(a, b));
//	    }

}
