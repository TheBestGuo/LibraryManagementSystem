package servelets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.impl.*;
import vo.JavaBean.*;
import DAO.factory.*;
/**
 * Servlet implementation class adminControlBookServlet
 */
@WebServlet("/adminControlBookServlet")
public class adminControlBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminControlBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String flag = request.getParameter("flag");
		if("allbook".equals(flag)) {

			ArrayList<BookBean> al=bookFactory.getInterfaceAdminDao().showAllBook();
			request.setAttribute("book", al);
			request.getRequestDispatcher("ShowAllBook.jsp").forward(request,response);
		}	
		else if("delbook".equals(flag)) {
			String id = request.getParameter("id");
			if (bookFactory.getInterfaceAdminDao().delbook(id)) {
				request.setAttribute("res", "�����ɹ���");
			} else {
				request.setAttribute("res", "����ʧ�ܣ�");

			}	
			request.getRequestDispatcher("adminMiddle.jsp").forward(request,
					response);
		}
		
		else if("finbook".equals(flag)) {
			String id = request.getParameter("id");
			ArrayList<BookBean> al=bookFactory.getInterfaceAdminDao().findBook(id,"","","");
			request.setAttribute("book", al);
			request.getRequestDispatcher("altbook.jsp").forward(request,response);
		}
		
		
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
String flag = request.getParameter("flag");
		
		if("addbook".equals(flag)) {
			String book_id = request.getParameter("book_id");
			String book_name = request.getParameter("book_name");
			String book_class = request.getParameter("book_class");
			String book_from = request.getParameter("book_from");
			int book_all = Integer.parseInt(request.getParameter("book_all"));
			int book_last = Integer.parseInt(request.getParameter("book_all"));
			BookBean ub = new BookBean();
			ub.setBook_id(book_id);
			ub.setBook_name(book_name);
			ub.setBook_class(book_class);
			ub.setBook_from(book_from);
			ub.setBook_all(book_all);
			ub.setBook_last(book_last);
			if (bookFactory.getInterfaceAdminDao().addBook(ub)) {
				request.setAttribute("res", "�����ɹ���");
			} else {
				request.setAttribute("res", "����ʧ�ܣ�");

			}	
		
		request.getRequestDispatcher("adminMiddle.jsp").forward(request,
				response);
			
		}	
		else if("findbook".equals(flag)) {
			String book_id = request.getParameter("book_id");
			String book_name = request.getParameter("book_name");
			String book_class = request.getParameter("book_class");
			String book_from = request.getParameter("book_from");
			ArrayList<BookBean> al=bookFactory.getInterfaceAdminDao().findBook(book_id, book_name, book_from, book_class);
			request.setAttribute("book", al);
				
		
			request.getRequestDispatcher("ShowAllBook.jsp").forward(request,response);
			
			
		}
		else if("altbook".equals(flag)) {
			String id = request.getParameter("id");
			String book_id = request.getParameter("book_id");
			String book_name = request.getParameter("book_name");
			String book_class = request.getParameter("book_class");
			String book_from = request.getParameter("book_from");
			int book_all = Integer.parseInt(request.getParameter("book_all"));
			int book_last = Integer.parseInt(request.getParameter("book_all"));
			BookBean ub = new BookBean();
			ub.setBook_id(book_id);
			ub.setBook_name(book_name);
			ub.setBook_class(book_class);
			ub.setBook_from(book_from);
			ub.setBook_all(book_all);
			ub.setBook_last(book_last);
			if (bookFactory.getInterfaceAdminDao().altBook(ub, id)) {
				request.setAttribute("res", "�����ɹ���");
			} else {
				request.setAttribute("res", "����ʧ�ܣ�");

			}	
		
		request.getRequestDispatcher("adminMiddle.jsp").forward(request,
				response);
			
			
		}
		
		
		}
	
		
		
		
	

}
