package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import avant.spring.user.domain.User;

/**
 * Hello world!
 *
 */
public class UserDao {
	private DataSource dataSource;

	public void setConnectionMaker(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = dataSource.getConnection();

			ps = c.prepareStatement("select * from users where id = ?");
			ps.setString(1, id);

			rs = ps.executeQuery();
			rs.next();

			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));

			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {

			}

			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {

			}
		}

	}

	public void deleteAll() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			// 1. 커넥션 얻기
			c = dataSource.getConnection();
			// 2. 쿼리 수행
			StatementStrategy strategy = new DeleteAllStatement(); // 전략 구현체
			ps = strategy.makePreparedStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			// 3. 리소스 회수
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {

			}

			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {

			}
		}
	}

}
