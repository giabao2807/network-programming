

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
		BigInteger A = BigInteger.valueOf(Long.parseLong(request.getParameter("A")));
		BigInteger B = BigInteger.valueOf(Long.parseLong(request.getParameter("B")));
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Ket qua la </h1>");
		out.println("UCLN la: " + UCLN(A, B));
		out.println("BCNN la: " + BCNN(A, B));
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
	public BigInteger UCLN(BigInteger a, BigInteger b) {
		if (a.compareTo(b) < 0)
			return UCLN(b, a);
		if (a.mod(b).compareTo(BigInteger.ZERO) == 0)
			return b;
		return UCLN(b, a.mod(b));
	}

	public BigInteger BCNN(BigInteger a, BigInteger b) {
		return (a.multiply(b)).divide(UCLN(a, b));
	}

}
