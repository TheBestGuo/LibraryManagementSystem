package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.adminDao;
import DAO.Util.ConnDB;
import vo.JavaBean.BookBean;

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
		}
		return b;
}
	}
