package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import avant.spring.user.domain.User;

/**
 * Hello world!
 *
 */
public class Statement {
	private DataSource dataSource;

	public void setConnectionMaker(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			// 1. 커넥션 얻기
			c = dataSource.getConnection();
			// 2. 쿼리 수행
			ps = stmt.makePreparedStatement(c);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			// 3. 리소스 회수
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {}

			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {}
		}
	}

	public void deleteAll() throws SQLException {
		StatementStrategy st = new DeleteAllStatement();
		jdbcContextWithStatementStrategy(st);
	}

	public void add(User user) throws SQLException {
		StatementStrategy st = new AddStatement(user);
		jdbcContextWithStatementStrategy(st);
	}
}
