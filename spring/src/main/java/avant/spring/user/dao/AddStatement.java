package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import avant.spring.user.domain.User;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2022. 1. 21. initial creation
 */
public class AddStatement implements StatementStrategy {
	User user;

	public AddStatement(User user) {
		this.user = user;
	}

	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");

		// 현재 user를 얻을 수가 없어 컴파일 에러가 발생한다
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		return ps;
	}

}
