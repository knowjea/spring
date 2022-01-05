package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import avant.spring.user.domain.User;

/**
 * 잘못된 싱글톤 구현방식을 테스트 하기 위한 UserDao
 *
 * @author NB-18113007
 * @history
 *          2021. 12. 26. initial creation
 */
public class StatefulUserDao {
	ConnectionMaker connectionMaker;
	private User user;

	public StatefulUserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.getConnection();

		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();

		user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}

}
