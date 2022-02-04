package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
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

	public void executeSql(final String query) throws SQLException {
		workWithStatementStrategy(new StatementStrategy() {

			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps;
				ps = c.prepareStatement(query);
				return ps;
			}
		});
	}
}
