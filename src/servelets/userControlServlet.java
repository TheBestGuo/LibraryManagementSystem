package servelets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String account=session.getAttribute("account").toString();
		String flag=request.getParameter("flag").toString();
		if(flag.equals("lend"))
		{
			String book_id=request.getParameter("book_id");
			int result=bookFactory.getInterfaceUseDao().lend(account, book_id);			
			switch(result)
			{
			case 0:request.setAttribute("lendResult", "借阅成功");break;
			case 1:request.setAttribute("lendResult", "借阅失败：您已借阅大于5本，请先还书！");break;
			case 2:request.setAttribute("lendResult", "借阅失败：你有借阅超期的图书，请先还书！");break;
			case 3:request.setAttribute("lendResult", "借阅失败：您所借的书已经被您借阅过，且未归还，无法再借！");break;
			case 4:request.setAttribute("lendResult", "借阅失败：该书余量不足！");break;
			case 5:request.setAttribute("lendResult", "借阅失败：未知错误，请联系工作人员！");break;
			default:request.setAttribute("lendResult", "借阅失败：未知错误，请联系工作人员！");break;
			}
			request.getRequestDispatcher("FindByAccountBook.jsp").forward(request,response);
		}
		else if(flag.equals("returnBook"))
		{
			String book_id=request.getParameter("book_id");
			float toll=0;
			int result=bookFactory.getInterfaceUseDao().returnBook(account,book_id);
			if(result>0)
			{
				request.setAttribute("returnResult", "归还成功");
				toll=bookFactory.getInterfaceUseDao().findByBookToll(account, book_id);
				String resultToll="您应缴费 "+toll+" 元！";
				request.setAttribute("returnToll", resultToll);
				request.getRequestDispatcher("ReturnBook.jsp").forward(request,response);
			}
			else
			{
				request.setAttribute("returnResult", "归还失败");
				request.getRequestDispatcher("ReturnBook.jsp").forward(request,response);
			}
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String account=session.getAttribute("account").toString();
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
