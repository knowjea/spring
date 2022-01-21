package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2022. 1. 21. initial creation
 */
public class DeleteAllStatement implements StatementStrategy {

	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps;
		ps = c.prepareStatement("delete from users");
		return ps;
	}

}
