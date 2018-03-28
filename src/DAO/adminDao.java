package DAO;

import java.util.ArrayList;

import vo.JavaBean.BookBean;

public interface adminDao {
	public ArrayList<BookBean>  showAllBook();
	
	public boolean  addBook(BookBean ub);
	
	public ArrayList<BookBean>  findBook(String Book_id,String Book_name,String Book_from,String Book_class);
	
	public ArrayList<String>  findClass();
}
