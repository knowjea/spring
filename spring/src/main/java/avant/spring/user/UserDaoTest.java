package avant.spring.user;

import java.sql.SQLException;

import avant.spring.user.dao.SimpleConnectionMaker;
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
		UserDao dao = new UserDao(new SimpleConnectionMaker());

		User user = new User();
		user.setId("test");
		user.setName("aaA");
		user.setPassword("asdsad");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());

		System.out.println(user2.getId() + " 조회 성공");
	}

}
