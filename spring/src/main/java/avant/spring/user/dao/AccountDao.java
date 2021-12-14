package avant.spring.user.dao;

/**
 * Hello world!
 *
 */
public class AccountDao {
	ConnectionMaker connectionMaker;

	public AccountDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

}
