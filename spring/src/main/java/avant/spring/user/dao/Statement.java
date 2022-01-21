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
		class DeleteAllStatement implements StatementStrategy {

			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps;
				ps = c.prepareStatement("delete from users");
				return ps;
			}

		}

		StatementStrategy st = new DeleteAllStatement();
		jdbcContextWithStatementStrategy(st);
	}

	/**
	 * 내부 클래스는 메서드내에서 정보를 공유하기 때문에 user를 따로 인스턴스 변수로 둘 필요가 없음.
	 * 내부 클래스에서 외부의 변수를 사용할 때는 외부 변수는 반드시 final로 선언해줘야 한다. user 따라미터는 메소드 내부에서 변경될 일이 없으므로 final 로
	 * 선언해도 무방하다.
	 */
	public void add(final User user) throws SQLException {
		class AddStatement implements StatementStrategy {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");

				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				return ps;
			}

		}

		StatementStrategy st = new AddStatement();
		jdbcContextWithStatementStrategy(st);
	}
}
