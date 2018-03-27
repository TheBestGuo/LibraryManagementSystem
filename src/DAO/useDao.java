package DAO;

import java.util.ArrayList;

import vo.JavaBean.BookBean;

public interface useDao {
	public ArrayList<BookBean>  findByAccurateBook(String Book_id,String Book_name,String Book_from,String Book_class);
	public ArrayList<String>  findBookClass();
}
