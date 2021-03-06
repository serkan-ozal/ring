package tr.com.t2giants.ring.server.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import tr.com.t2giants.ring.core.domain.BaseObject;
import tr.com.t2giants.ring.server.dao.BaseDao;

import javax.sql.DataSource;

public abstract class BaseDaoJDBC<T extends BaseObject> implements BaseDao<T> {

    protected final Log logger = LogFactory.getLog(getClass());
	
	@Qualifier("localDS")
    @Autowired
    protected DataSource dataSource;

    @Required
    protected void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    JdbcTemplate getJDBCTemplate() {
        return new JdbcTemplate(dataSource);
    }

}

