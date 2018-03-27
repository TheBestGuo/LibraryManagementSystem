package servelets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.factory.*;
import vo.JavaBean.*;
import java.util.*;


/**
 * Servlet implementation class userControlServlet
 */
@WebServlet("/userControlServlet")
public class userControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//request.setCharacterEncoding("utf-8");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag").toString();
		if(flag.equals("find"))
		{
			  String Book_id=request.getParameter("book_id");
			  String Book_name=request.getParameter("book_name");
			  String Book_from=request.getParameter("book_from");
			  String Book_class=request.getParameter("book_class");
			  List<BookBean> list =bookFactory.getInterfaceUseDao().findByAccurateBook(Book_id,Book_name,Book_from,Book_class);
			  request.setAttribute("result", list);
			  request.getRequestDispatcher("FindByAccountBookResult.jsp").forward(request,response);
		}
	}

}
