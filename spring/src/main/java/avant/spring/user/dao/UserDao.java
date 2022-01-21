package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Hello world!
 *
 */
public abstract class UserDao {
	private DataSource dataSource;

	public void setConnectionMaker(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void query() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			// 1. 커넥션 얻기
			c = dataSource.getConnection();
			// 2. 쿼리 수행
			ps = makeStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			// 3. 리소스 회수
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) { // ps.close도 에러가 발생할 수 있다. 만약 catch로 잡아내지 않는다면 아래 코드가 수행되지 않아
										// c.close()가 호출되지 않아 문제가 된다

			}

			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {

			}
		}
	}

	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;

}
