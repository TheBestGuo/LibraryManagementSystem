package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.adminDao;
import DAO.Util.ConnDB;
import vo.JavaBean.*;

public class adminDaoImpl implements adminDao {
	private Connection conn = null;//数据库连接
	private PreparedStatement pstmt = null;//数据库操作
	private ResultSet rs = null;//数据库操作结果
	private Statement stmt = null;//数据库操作
	
	public ArrayList<BookBean>  showAllBook()
	{
		/**显示所有书籍**/
		ArrayList<BookBean> al = new ArrayList<BookBean>(); 
		try {
		conn=ConnDB.getConnection();
		pstmt=conn.prepareStatement("select * from book");
		rs=pstmt.executeQuery();
		while(rs.next()){
			BookBean ub=new BookBean();
			ub.setBook_id(rs.getString(1));
			ub.setBook_name(rs.getString(2));
			ub.setBook_from(rs.getString(3));
			ub.setBook_class(rs.getString(4));
			ub.setBook_all(rs.getInt(5));
			ub.setBook_last(rs.getInt(6));
			al.add(ub);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return al;
}
		
	
	
	public boolean  addBook(BookBean ub)
	{
		/**添加书籍**/
		boolean b = false;
		ArrayList<BookBean> al = new ArrayList<BookBean>(); 
		try {
		conn=ConnDB.getConnection();
		pstmt=conn.prepareStatement("insert into book(Book_id,Book_name,Book_from,Book_class,Book_all,Book_last)values(?,?,?,?,?,?)");
		pstmt.setString(1,ub.getBook_id());
		pstmt.setString(2,ub.getBook_name());
		pstmt.setString(3,ub.getBook_from());
		pstmt.setString(4,ub.getBook_class());
		pstmt.setInt(5,ub.getBook_all());
		pstmt.setInt(6,ub.getBook_last());
		int num=pstmt.executeUpdate();
		if(num==1) {b = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return b;
}
	
	public boolean delbook(String id)
	{
		boolean b = false;
		try {
			conn=ConnDB.getConnection();
			pstmt=conn.prepareStatement("delete from book where Book_id = ?");
			pstmt.setString(1,id);
			int num=pstmt.executeUpdate();
			if(num==1) {b = true;}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		return b;
	}
	
	
	public ArrayList<BookBean>  findBook(String Book_id,String Book_name,String Book_from,String Book_class)
	{
		ArrayList<BookBean> al = new ArrayList<BookBean>();
		try {
		conn=ConnDB.getConnection();
		String sql="select * from book where 1=1";
		if(!Book_id.equals(""))
			sql=sql+" and Book_id="+Book_id;
		if(!Book_name.equals(""))
			sql=sql+" and Book_name like '%"+Book_name+"%'";
		if(!Book_from.equals(""))
			sql=sql+" and Book_from like '%"+Book_from+"%'";
		if(!Book_class.equals(""))
			sql=sql+" and Book_class like '%"+Book_class+"%';";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();				
		while(rs.next()) {
			BookBean ub= new BookBean();
			ub.setBook_id(rs.getString(1));
			ub.setBook_name(rs.getString(2));
			ub.setBook_from(rs.getString(3));
			ub.setBook_class(rs.getString(4));
			ub.setBook_all(rs.getInt(5));
			ub.setBook_last(rs.getInt(6));
			al.add(ub);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return al;
	}
	
	public ArrayList<String>  findClass()
	{
		ArrayList<String> al = new ArrayList<String>();
		try {
		conn=ConnDB.getConnection();
		String sql="select distinct Book_class from book";
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);		
		while(rs.next()) {
			al.add(rs.getString(1));
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return al;
	}
	
	public boolean  altBook(BookBean ub,String id)
	{
		/**添加书籍**/
		boolean b = false;
		ArrayList<BookBean> al = new ArrayList<BookBean>(); 
		try {
		conn=ConnDB.getConnection();
		pstmt=conn.prepareStatement("update book set Book_id=?,Book_name=?,Book_from=?,Book_class=?,Book_all=?,Book_last=? where Book_id=?");
		pstmt.setString(1,ub.getBook_id());
		pstmt.setString(2,ub.getBook_name());
		pstmt.setString(3,ub.getBook_from());
		pstmt.setString(4,ub.getBook_class());
		pstmt.setInt(5,ub.getBook_all());
		pstmt.setInt(6,ub.getBook_last());
		pstmt.setString(7,id);
		int num=pstmt.executeUpdate();
		if(num==1) {b = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return b;
}

	/**对用户操作**/
	public ArrayList<UserBean>  showAllUser()
	{
		/**显示所有书籍**/
		ArrayList<UserBean> al = new ArrayList<UserBean>(); 
		try {
		conn=ConnDB.getConnection();
		pstmt=conn.prepareStatement("select * from users");
		rs=pstmt.executeQuery();
		while(rs.next()){
			UserBean ub=new UserBean();
			ub.setAccount(rs.getString(1));
			ub.setJob(rs.getString(3));
			ub.setPhone(rs.getString(4));
			al.add(ub);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}


		return al;
}
		
	
	
	public boolean  addUser(UserBean ub)
	{
		/**添加书籍**/
		boolean b = false;
		ArrayList<UserBean> al = new ArrayList<UserBean>(); 
		try {
		conn=ConnDB.getConnection();
		pstmt=conn.prepareStatement("insert into users(account,password,job,phone)values(?,?,?,?)");
		pstmt.setString(1,ub.getAccount());
		pstmt.setString(2,ub.getPassword());
		pstmt.setString(3,ub.getJob());
		pstmt.setString(4,ub.getPhone());
		int num=pstmt.executeUpdate();
		if(num==1) {b = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return b;
}
	
	public boolean deluser(String id)
	{
		boolean b = false;
		try {
			conn=ConnDB.getConnection();
			pstmt=conn.prepareStatement("delete from users where account = ?");
			pstmt.setString(1,id);
			int num=pstmt.executeUpdate();
			if(num==1) {b = true;}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		return b;
	}
	
	
	public ArrayList<UserBean>  finduser(String account,String job,String phone)
	{
		ArrayList<UserBean> al = new ArrayList<UserBean>();
		try {
		conn=ConnDB.getConnection();
		String sql="select * from users where 1=1";
		if(!account.equals(""))
			sql=sql+" and account="+account;
		if(!job.equals(""))
			sql=sql+" and job like '%"+job+"%'";
		if(!phone.equals(""))
			sql=sql+" and phone like '%"+phone+"%';";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();				
		while(rs.next()) {
			UserBean ub= new UserBean();
			ub.setAccount(rs.getString(1));
			ub.setPassword(rs.getString(2));
			ub.setJob(rs.getString(3));
			ub.setPhone(rs.getString(4));
			al.add(ub);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return al;
	}
	
	
	public boolean  altUser(UserBean ub,String id)
	{
		/**添加书籍**/
		boolean b = false;
		ArrayList<UserBean> al = new ArrayList<UserBean>(); 
		try {
		conn=ConnDB.getConnection();
		pstmt=conn.prepareStatement("update users set account=?,password=?,job=?,phone=? where account=?");
		pstmt.setString(1,ub.getAccount());
		pstmt.setString(2,ub.getPassword());
		pstmt.setString(3,ub.getJob());
		pstmt.setString(4,ub.getPhone());
		pstmt.setString(5,id);
		int num=pstmt.executeUpdate();
		if(num==1) {b = true;}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return b;
}
	
	
	/*判断身份 管理员返回1 普通用户返回0*/
	public int checkUserJob(String account,String password)
	{
		String flag="";
		try {
			conn=ConnDB.getConnection();
			pstmt=conn.prepareStatement("select job from users where account=? and password=?");
			pstmt.setString(1,account);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				flag = rs.getString(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		if(flag.equals("管理员"))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	
	
	/*判断登录 登陆成功返回true 登录失败返回false*/
	public boolean check(String userid,String userpwd,String userjob) {
		int flag=0;
		try {
				String info ="";
				conn=ConnDB.getConnection();
				String sql="select 1 from users where account=? and password=? and job=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpwd);
				pstmt.setString(3, userjob);
				rs=pstmt.executeQuery();
				if(rs.next())
				{   
					flag=1;
				}
				else
				{ 
					flag=0;
				}
		}catch(Exception e){e.printStackTrace();}
		if(flag==1)
		return true;
		else
		return false;
	}

}
