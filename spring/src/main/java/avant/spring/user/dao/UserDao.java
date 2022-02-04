package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import avant.spring.user.domain.User;

public class UserDao {
	private DataSource dataSource;

	private JdbcContext jdbcContext;

	// UserDao가 대신 DI 받는다.
	public void setDataSource(DataSource dataSource) {
		// DI 받은 DataSource를 jdbcContext에 설정해준다.
		this.jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);

		this.dataSource = dataSource;
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
		this.jdbcContext.executeSql("delete from users");
	}

}
