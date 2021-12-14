package avant.spring.user.dao;

/**
 * Hello world!
 *
 */
public class MessageDao {
	ConnectionMaker connectionMaker;

	public MessageDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

}
