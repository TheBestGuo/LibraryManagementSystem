package servelets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import DAO.factory.bookFactory;
import vo.JavaBean.*;


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
		else if("biuldYzm".equals(flag))
		{
			/**生成验证码**/
			response.setContentType("image/jpeg");
			HttpSession session = request.getSession();
			int width = 60;
			int height = 20;
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			char[] rands = new char[4];
			for (int i = 0; i < 4; i++) {
				int rand = (int) (Math.random() * 36);
				rands[i] = chars.charAt(rand);
			}
			g.setColor(new Color(0xDCDCDC));
			g.fillRect(0, 0, width, height);
			for (int i = 0; i < 120; i++) {
				int x = (int) (Math.random() * width);
				int y = (int) (Math.random() * height);
				int red = (int) (Math.random() * 255);
				int green = (int) (Math.random() * 255);
				int blue = (int) (Math.random() * 255);
				g.setColor(new Color(red, green, blue));
				g.drawOval(x, y, 1, 0);
			}
			g.setColor(Color.BLACK);
			g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
			g.drawString("" + rands[0], 1, 17);
			g.drawString("" + rands[1], 16, 15);
			g.drawString("" + rands[2], 31, 18);
			g.drawString("" + rands[3], 46, 16);
			g.dispose();
			ServletOutputStream sos = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "JPEG", baos);
			byte[] buffer = baos.toByteArray();
			response.setContentLength(buffer.length);
			sos.write(buffer);
			baos.close();
			sos.close();
			session.setAttribute("checkCode", new String(rands));
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
				else if("loginUse".equals(flag))
				{
					request.setCharacterEncoding("utf-8");
					HttpSession session = request.getSession();
					String userid = request.getParameter("account");
					String userpwd = request.getParameter("password");
					String userjob = request.getParameter("shenfen");
					String servercheckcode=(String)session.getAttribute("checkCode");
					String usercheckcode = request.getParameter("checkcode");
					String info = "";
					if(!servercheckcode.equalsIgnoreCase(usercheckcode))
					{
						info="验证码不正确，请重新输入!";
						request.setAttribute("info", info);
						request.getRequestDispatcher("/Login.jsp").forward(request, response);;
					}else 
					{		
						boolean checkFlag;
						checkFlag=bookFactory.getInterfaceAdminDao().check(userid, userpwd,userjob);
						int jobFlag=bookFactory.getInterfaceAdminDao().checkUserJob(userid, userpwd);
						if(checkFlag) {
							info="登陆成功";
							request.setAttribute("info", info);
							if(jobFlag==1)
							{
								request.getRequestDispatcher("/adminMain.jsp").forward(request, response);;
							}
							else
							{
								session.setAttribute("account", userid);
								request.getRequestDispatcher("/UserMain.jsp").forward(request, response);;

							}
							
						}else { 
							info="用户名或密码不正确";
							request.setAttribute("info", info);
							 request.getRequestDispatcher("/Login.jsp").forward(request, response);
						}
					}
					
				}
				else if("refreshCheckCode".equals(flag))
				{
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
				}
				
	}

}
