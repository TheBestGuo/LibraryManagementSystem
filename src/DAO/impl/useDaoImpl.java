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
	
	/*��������������ؽ�����*/
	public int lend(String account,String book_id)
	{
		if(judgeUserLendBookCount(account))
		{
			return 1;//���Ĵ���5��
		}
		if(judgeUserLendBookTimeOut(account))
		{
			return 2;//�������鱾��ʱ
		}
		if(judgeUserDistinctBook(account,book_id))
		{
			return 3;//�����Ѿ����
		}
		if(!BookLastCount(book_id))
		{
			return 4;//������������
		}
		int flag=0;
		try {
			Date dt=new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now=sdf.format(dt);
			conn=ConnDB.getConnection();
			String sql="insert into lend VALUES(?,?,?,?,?,?);";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2,book_id);
			pstmt.setString(3,now);
			pstmt.setString(4,null);
			pstmt.setString(5,"��");
			pstmt.setFloat(6, 0);
			flag=pstmt.executeUpdate();			
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		if(flag==0)
		{
			return 5;//δ֪����
		}
		else
		{
			if(BookSub(book_id))
			{
				return 0;//�ɹ�}
			}
			else
				return 5;
		}
			
	}
	
	/*�ж��鱾����������  ������true ��������false*/
	public boolean BookLastCount(String book_id) {
		String flag="";
		try {
			conn=ConnDB.getConnection();
			String sql="select Book_last from book where Book_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, book_id);
			rs=pstmt.executeQuery();	
			if(rs.next()) {
				flag=rs.getString(1);
			}}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		if(flag!=null) {
			if(Integer.parseInt(flag)==0)
			{return false;}
			else
			{return true;}
			}
			else 
				return false;
	
	}
		
	/*�����ɹ�����BOOK�� �ɹ��޸���������true  ʧ�ܷ���false*/
	public boolean BookSub(String book_id) {
		int flag=0;
		try {
			Date dt=new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now=sdf.format(dt);
			conn=ConnDB.getConnection();
			String sql="update book set Book_last=Book_last-1 where Book_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, book_id);
			flag=pstmt.executeUpdate();			
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
			if(flag>0)
			{return true;}
			else
			{return false;}
		
	}
	
	/*�ж��Ƿ񳬹�ÿ���û�����5������������true δ��������false*/
	public boolean judgeUserLendBookCount(String account)	
	{
		int maxBookCount=5;
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select count(L_id) from lend where L_account=? and Is_return='��'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, account);
		rs=pstmt.executeQuery();	
		if(rs.next()) {
			flag=rs.getString(1);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		if(flag!=null) {
		if(Integer.parseInt(flag)>=5)
		{return true;}
		else
		{return false;}}
		else 
			return false;
	}
	
	/*�ж��Ƿ�ʱ  ��ʱ����true  û��ʱ����false*/
	public boolean judgeUserLendBookTimeOut(String account)
	{
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select max(DATEDIFF(CURDATE(),L_time)) from lend where L_account=? and Is_return='��'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, account);
		rs=pstmt.executeQuery();	
		if(rs.next()) {
			flag=rs.getString(1);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		if(flag!=null) {
		if(Integer.parseInt(flag)>61)
		{
			return true;
		}
		else
			return false;}
		else
			return false;
	}
	
	/*�ж���ͬͼ���Ƿ���Ĺ� ���δ������true ����ѻ���δ�������false*/
	public boolean judgeUserDistinctBook(String account,String book_id)
	{
		String flag=null;
		try {
			conn=ConnDB.getConnection();
			String sql="select 1 from lend where L_account=? and L_id=? and Is_return='��'";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, book_id);
			rs=pstmt.executeQuery();	
			if(rs.next()) {
				flag=rs.getString(1);
			}}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		if(flag!=null)
		{
		if(Integer.parseInt(flag)==1)
		{
			return true;
		}
		else
			return false;
		}
		else
			return false;
	}
	/*��������鱾��,���ز������*/
	public boolean returnBookTable(String book_id)
	{
		int flag=0;
		try {
			conn=ConnDB.getConnection();
			String sql="update book set Book_last=Book_last+1 where Book_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, book_id);
			flag=pstmt.executeUpdate();	
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
		if(flag>0)
		{
			return true;
		}
		else {
			return false;
		}
		
	}
	/*������������¼�����ػ�����*/
	public int returnBook(String account,String book_id)
	{
		int flag=0;
		try {
		int bookTime=CountLendTime(account, book_id);
		float toll=(float)0.0;
		if(bookTime>61)
		{
				toll=(float)(bookTime*0.1);
				/*Date dt=new Date(); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String now=sdf.format(dt);*/
				conn=ConnDB.getConnection();
				String sql="update lend set R_time=DATE_FORMAT(now(), '%Y-%m-%d'),Is_return='��' ,Toll=? where L_account=? and L_id=?;";
				pstmt=conn.prepareStatement(sql);
				//pstmt.setString(1, now);
				pstmt.setFloat(1,toll);
				pstmt.setString(2,account);
				pstmt.setString(3,book_id);
				flag=pstmt.executeUpdate();	
		}
		else
		{
			toll=0;
			/*Date dt=new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now=sdf.format(dt);*/
			conn=ConnDB.getConnection();
			String sql="update lend set R_time=DATE_FORMAT(now(), '%Y-%m-%d') , Is_return='��' ,Toll=? where L_account=? and L_id=?;";
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, now);
			pstmt.setFloat(1,toll);
			pstmt.setString(2,account);
			pstmt.setString(3,book_id);
			flag=pstmt.executeUpdate();	
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		if(flag>0)
		{
			if(returnBookTable(book_id))
			{return 1;}
			else
			 return 0;
		}
		else
		{
			return 0;
		}
	}
	
	/*��ѯ��ǰ�û��������*/
	public ArrayList<LendBean> findUserLend(String account)
	{
		ArrayList<LendBean> al = new ArrayList<LendBean>();
		try {
		conn=ConnDB.getConnection();
		String sql="select * from lend where L_account=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, account);
		rs=pstmt.executeQuery();		
		while(rs.next()) {
			LendBean ld= new LendBean();
			ld.setL_account(rs.getString(1));
			ld.setL_id(rs.getString(2));
			ld.setL_time(rs.getString(3));
			ld.setR_time(rs.getString(4));
			ld.setIs_retrun(rs.getString(5));
			ld.setToll(rs.getFloat(6));
			al.add(ld);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
		return al;
	}
	
	/*��ѯ�鱾��Ӧ�ĳ��������*/
	public float findByBookToll(String account,String book_id)
	{
		String flag="";
		try {
			conn=ConnDB.getConnection();
			String sql="select toll from lend where L_account=? and L_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, book_id);
			rs=pstmt.executeQuery();	
			if(rs.next()) {
				flag=rs.getString(1);
			}}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnDB.free(rs, pstmt,stmt, conn);
			}
				return Float.parseFloat(flag);
	}
	
	/*��ȡ�������*/
	public int CountLendTime(String account,String book_id)
	{
		String flag=null;
		try {
		conn=ConnDB.getConnection();
		String sql="select DATEDIFF(CURDATE(),L_time) from lend where L_account=? and L_id=? and Is_return='��'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, book_id);
		rs=pstmt.executeQuery();	
		if(rs.next()) {
			flag=rs.getString(1);
		}}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnDB.free(rs, pstmt,stmt, conn);
		}
			return Integer.parseInt(flag);
	}
	
}
