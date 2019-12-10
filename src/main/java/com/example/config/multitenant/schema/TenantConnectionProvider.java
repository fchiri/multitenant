package com.example.config.multitenant.schema;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.stereotype.Component;

import com.example.config.multitenant.database.TenantDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(TenantConnectionProvider.class);
    private String DEFAULT_TENANT = "public";
    private DataSource datasource;

    @Autowired
    private ApplicationContext context;

  
    public TenantConnectionProvider(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return datasource.getConnection();
    }
  
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        logger.info("Get connection for tenant {}", tenantIdentifier);
        Connection connection = null;
        if(!tenantIdentifier.equals("public")) {
        	TenantDataSource tds = context.getBean(TenantDataSource.class);
        	DataSource ds = tds.getDataSource(tenantIdentifier);
        
        	connection = ds.getConnection();
        }else {
            connection = getAnyConnection();
        }
        final Connection conn = connection;
        conn.setSchema(tenantIdentifier);
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        logger.info("Release connection for tenant {}", tenantIdentifier);
        connection.setSchema(DEFAULT_TENANT);
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
