package DAO.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
import vo.JavaBean.*;
import java.text.SimpleDateFormat;
import DAO.Util.*;
import DAO.useDao;

public class useDaoImpl implements useDao{
	private Connection conn = null;//数据库连接
	private PreparedStatement pstmt = null;//数据库操作
	private ResultSet rs = null;//数据库操作结果
	private Statement stmt = null;//数据库操作
	
	/*查找*/
	public ArrayList<BookBean>  findByAccurateBook(String Book_id,String Book_name,String Book_from,String Book_class)
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
			BookBean bk= new BookBean();
			bk.setBook_id(rs.getString(1));
			bk.setBook_name(rs.getString(2));
			bk.setBook_from(rs.getString(3));
			bk.setBook_class(rs.getString(4));
			bk.setBook_all(rs.getInt(5));
			bk.setBook_last(rs.getInt(6));
			al.add(bk);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return al;
	}
	
	/*返回书本分类*/
	public ArrayList<String>  findBookClass()
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
	
	/*判断是否超过每个用户最多借5本，超过返回true 未超过返回false*/
	public boolean judgeUserLendBookCount(String account)	
	{
		int maxBookCount=5;
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select count(book_id) from lend where L_account=? and Is_return='否'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, account);
		rs=pstmt.executeQuery();	
		while(rs.next()) {
			flag=rs.getString(1);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		if(Integer.parseInt(flag)>5)
		{return true;}
		else
		{return false;}
	}
	
	
	/*判断是否超时  超时返回true  没超时返回false*/
	public boolean judgeUserLendBookTimeOut(String account)
	{
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select max(DATEDIFF(CURDATE(),L_time)) from lend where L_account='?' and Is_return='否'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, account);
		rs=pstmt.executeQuery();	
		while(rs.next()) {
			flag=rs.getString(1);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		if(Integer.parseInt(flag)>61)
		{
			return true;
		}
		else
			return false;
	}
	
	/*判断相同图书是否借阅过 借过未还返回true 借过已还或未结果返回false*/
	public boolean judgeUserDistinctBook(String account,String book_id)
	{
		String flag=null;
		try {
			conn=ConnDB.getConnection();
			String sql="select 1 from lend where L_account='?' and book_id=? and Is_return='否'";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, book_id);
			rs=pstmt.executeQuery();	
			while(rs.next()) {
				flag=rs.getString(1);
			}}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		if(Integer.parseInt(flag)==1)
		{
			return true;
		}
		else
			return false;
		
	}
	
	/*获取相差天数*/
	//public void CountLendTime(){}
}
