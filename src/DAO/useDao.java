package DAO;

import java.util.ArrayList;
import vo.JavaBean.*;


public interface useDao {
	public ArrayList<BookBean>  findByAccurateBook(String Book_id,String Book_name,String Book_from,String Book_class);
	public ArrayList<String>  findBookClass();
	public boolean judgeUserLendBookCount(String account);
	public boolean judgeUserLendBookTimeOut(String account);
	public boolean judgeUserDistinctBook(String account,String book_id);
	public int lend(String account,String book_id);
	public boolean BookLastCount(String book_id) ;
	public boolean BookSub(String book_id) ;
	public int CountLendTime(String account,String book_id);
	public ArrayList<LendBean> findUserLend(String account);
	public int returnBook(String account,String book_id);
	public float findByBookToll(String account,String book_id);
}
