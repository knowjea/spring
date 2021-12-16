package avant.spring.user.dao;

import org.springframework.context.annotation.Bean;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2021. 12. 14. initial creation
 */
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}

	@Bean
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}

	@Bean
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new SimpleConnectionMaker();
	}
}