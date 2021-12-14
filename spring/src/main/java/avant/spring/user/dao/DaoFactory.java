package avant.spring.user.dao;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2021. 12. 14. initial creation
 */
public class DaoFactory {
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}

	public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}

	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}

	public ConnectionMaker connectionMaker() {
		return new SimpleConnectionMaker();
	}
}