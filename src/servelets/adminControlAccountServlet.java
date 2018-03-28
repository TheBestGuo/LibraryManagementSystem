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
 * Servlet implementation class adminControlAccountServlet
 */
@WebServlet("/adminControlAccountServlet")
public class adminControlAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminControlAccountServlet() {
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
		if("alluser".equals(flag)) {

			ArrayList<UserBean> al=bookFactory.getInterfaceAdminDao().showAllUser();
			request.setAttribute("user", al);
			request.getRequestDispatcher("ShowAllUser.jsp").forward(request,response);
		}	
		else if("deluser".equals(flag)) {
			String id = request.getParameter("id");
			if (bookFactory.getInterfaceAdminDao().deluser(id)) {
				request.setAttribute("res", "操作成功！");
			} else {
				request.setAttribute("res", "操作失败！");

			}	
			request.getRequestDispatcher("adminMiddle.jsp").forward(request,
					response);
		}
		
		else if("finuser".equals(flag)) {
			String id = request.getParameter("id");
			ArrayList<UserBean> al=bookFactory.getInterfaceAdminDao().finduser(id,"","");
			request.setAttribute("user", al);
			request.getRequestDispatcher("altuser.jsp").forward(request,response);
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
				
				if("adduser".equals(flag)) {
					String user_account = request.getParameter("user_account");
					String user_password = request.getParameter("user_password");
					String user_job = request.getParameter("user_job");
					String user_phone = request.getParameter("user_phone");
					UserBean ub = new UserBean();
					ub.setAccount(user_account);;
					ub.setPassword(user_password);;
					ub.setJob(user_job);;
					ub.setPhone(user_phone);;
					if (bookFactory.getInterfaceAdminDao().addUser(ub)) {
						request.setAttribute("res", "操作成功！");
					} else {
						request.setAttribute("res", "操作失败！");

					}	
				
				request.getRequestDispatcher("adminMiddle.jsp").forward(request,
						response);
					
				}	
				else if("finduser".equals(flag)) {
					String user_account = request.getParameter("user_account");
					String user_job = request.getParameter("user_job");
					String user_phone = request.getParameter("user_phone");
					ArrayList<UserBean> al=bookFactory.getInterfaceAdminDao().finduser(user_account, user_job, user_phone);
					request.setAttribute("user", al);
						
				
					request.getRequestDispatcher("ShowAllUser.jsp").forward(request,response);
					
					
				}
				else if("altuser".equals(flag)) {
					String id = request.getParameter("id");
					String user_account = request.getParameter("user_account");
					String user_password = request.getParameter("user_password");
					String user_job = request.getParameter("user_job");
					String user_phone = request.getParameter("user_phone");
					UserBean ub = new UserBean();
					ub.setAccount(user_account);
					ub.setPassword(user_password);
					ub.setJob(user_job);
					ub.setPhone(user_phone);

					if (bookFactory.getInterfaceAdminDao().altUser(ub, id)) {
						request.setAttribute("res", "操作成功！");
					} else {
						request.setAttribute("res", "操作失败！");

					}	
				
				request.getRequestDispatcher("adminMiddle.jsp").forward(request,
						response);
					
					
				}
	}

}
