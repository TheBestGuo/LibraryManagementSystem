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
	private Connection conn = null;//���ݿ�����
	private PreparedStatement pstmt = null;//���ݿ����
	private ResultSet rs = null;//���ݿ�������
	private Statement stmt = null;//���ݿ����
	
	/*����*/
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
	
	/*�����鱾����*/
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
	
	/*�ж��Ƿ񳬹�ÿ���û�����5������������true δ��������false*/
	public boolean judgeUserLendBookCount(String account)	
	{
		int maxBookCount=5;
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select count(book_id) from lend where L_account=? and Is_return='��'";
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
	
	
	/*�ж��Ƿ�ʱ  ��ʱ����true  û��ʱ����false*/
	public boolean judgeUserLendBookTimeOut(String account)
	{
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select max(DATEDIFF(CURDATE(),L_time)) from lend where L_account='?' and Is_return='��'";
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
	
	/*�ж���ͬͼ���Ƿ���Ĺ� ���δ������true ����ѻ���δ�������false*/
	public boolean judgeUserDistinctBook(String account,String book_id)
	{
		String flag=null;
		try {
			conn=ConnDB.getConnection();
			String sql="select 1 from lend where L_account='?' and book_id=? and Is_return='��'";
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
	
	/*��ȡ�������*/
	//public void CountLendTime(){}
}
