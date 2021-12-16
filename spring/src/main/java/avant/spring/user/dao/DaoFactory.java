package avant.spring.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2021. 12. 14. initial creation
 */
@Configuration
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