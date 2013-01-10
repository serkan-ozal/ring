package tr.com.t2giants.ring.server.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoJdbcImpl {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
}
