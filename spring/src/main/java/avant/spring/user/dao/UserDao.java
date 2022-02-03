package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import avant.spring.user.domain.User;

public class UserDao {
	private JdbcContext jdbcContext;

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public void add(final User user) throws ClassNotFoundException, SQLException {
		jdbcContext.workWithStatementStrategy(new StatementStrategy() {

			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");

				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				return ps;
			}
		});
	}

	public void deleteAll() throws SQLException {
		jdbcContext.workWithStatementStrategy(new StatementStrategy() {

			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps;
				ps = c.prepareStatement("delete from users");
				return ps;
			}
		});
	}

}
