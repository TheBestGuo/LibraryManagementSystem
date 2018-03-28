package DAO;

import java.util.ArrayList;

import vo.JavaBean.BookBean;
import vo.JavaBean.UserBean;

public interface adminDao {
	
	public ArrayList<BookBean>  showAllBook();
	
	public boolean  addBook(BookBean ub);
	
	public boolean delbook(String id);
	
	public ArrayList<BookBean>  findBook(String Book_id,String Book_name,String Book_from,String Book_class);
	
	public ArrayList<String>  findClass();
	
	public boolean  altBook(BookBean ub,String id);
	
	public ArrayList<UserBean>  showAllUser();
	
	public boolean  addUser(UserBean ub);
	
	public boolean deluser(String id);
	
	public ArrayList<UserBean>  finduser(String account,String job,String phone);
	
	public boolean  altUser(UserBean ub,String id);
}
