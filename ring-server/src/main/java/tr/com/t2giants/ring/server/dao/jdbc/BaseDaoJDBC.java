package tr.com.t2giants.ring.server.dao.jdbc;

import tr.com.t2giants.ring.server.dao.BaseDao;
import tr.com.t2giants.ring.server.data.BaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * User: mertcaliskan
 * Date: 6/11/12
 */
public abstract class BaseDaoJDBC<T extends BaseObject> implements BaseDao<T> {

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

