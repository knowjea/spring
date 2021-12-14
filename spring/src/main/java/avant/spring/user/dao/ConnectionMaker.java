package avant.spring.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 *
 * @author NB-18113007
 * @history
 *          2021. 12. 14. initial creation
 */
public interface ConnectionMaker {

	public Connection getConnection() throws ClassNotFoundException, SQLException;
}
