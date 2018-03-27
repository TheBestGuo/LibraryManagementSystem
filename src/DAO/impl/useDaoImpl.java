package DAO.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.Util.*;
import DAO.useDao;
import vo.JavaBean.*;

public class useDaoImpl implements useDao{
	private Connection conn = null;//数据库连接
	private PreparedStatement pstmt = null;//数据库操作
	private ResultSet rs = null;//数据库操作结果
	private Statement stmt = null;//数据库操作
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
	
}
