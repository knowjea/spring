package avant.spring.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import avant.spring.user.dao.UserDao;
import avant.spring.user.domain.User;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2021. 12. 3. initial creation
 */
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 관계 설정
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("test");
		user.setName("aaA");
		user.setPassword("asdsad");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 성공");

	}

}
