package org.blaec.movies.persist;

import lombok.extern.slf4j.Slf4j;
import org.blaec.movies.dao.AbstractDao;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.logging.SLF4JLog;
import org.skife.jdbi.v2.tweak.ConnectionFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;

@Slf4j
public class DBIProvider {

    private volatile static ConnectionFactory connectionFactory = null;

    private static class DBIHolder {
        static final DBI jDBI;

        static {
            URI dbUri = null;
            try {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            initDBI(dbUrl, username, password);

            jDBI = new DBI(connectionFactory);
            jDBI.setSQLLog(new SLF4JLog());
        }
    }

    public static void init(ConnectionFactory connectionFactory) {
        DBIProvider.connectionFactory = connectionFactory;
    }

    public static void initDBI(String dbUrl, String dbUser, String dbPassword) {
        init(() -> {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("PostgreSQL driver not found", e);
            }
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        });
    }

    public static <T extends AbstractDao> T getDao(Class<T> daoClass) {
        return DBIHolder.jDBI.onDemand(daoClass);
    }
}
