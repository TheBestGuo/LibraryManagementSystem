package DAO;

import java.util.ArrayList;

import vo.JavaBean.BookBean;

public interface adminDao {
	public ArrayList<BookBean>  showAllBook();
	
	public boolean  addBook(BookBean ub);
}
