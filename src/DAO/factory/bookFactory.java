package DAO.factory;
import DAO.adminDao;
import DAO.useDao;
import DAO.impl.*;

public class bookFactory {
public static useDao getInterfaceUseDao()
{
	return new useDaoImpl();
}

public static adminDao getInterfaceAdminDao()
{
	return new adminDaoImpl();
}

}
