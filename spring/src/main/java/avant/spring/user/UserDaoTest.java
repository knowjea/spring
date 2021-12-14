package avant.spring.user;

import java.sql.SQLException;

import avant.spring.user.dao.DaoFactory;
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
		UserDao userDao = new DaoFactory().userDao();

		User user = new User();
		user.setId("test");
		user.setName("aaA");
		user.setPassword("asdsad");

		userDao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = userDao.get(user.getId());

		System.out.println(user2.getId() + " 조회 성공");
	}

}
