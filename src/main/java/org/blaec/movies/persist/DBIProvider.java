package org.blaec.movies.persist;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.blaec.movies.configs.Configs;
import org.blaec.movies.dao.AbstractDao;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.logging.SLF4JLog;
import org.skife.jdbi.v2.tweak.ConnectionFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;

@Slf4j
public class DBIProvider {
//    public static final String profile = "local";
    public static final String profile = "heroku";

    private volatile static ConnectionFactory connectionFactory = null;

    private static class DBIHolder {
        static final DBI jDBI;

        static {
            try {
                switch (profile) {
                    case "local":
                        Config db = Configs.getConfig("persist.conf", "db");
                        initDBI(db.getString("url"), db.getString("user"), db.getString("password"));
                        break;
                    case "heroku":
                        URI dbUri = new URI(System.getenv("DATABASE_URL"));
                        String username = dbUri.getUserInfo().split(":")[0];
                        String password = dbUri.getUserInfo().split(":")[1];
                        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                        initDBI(dbUrl, username, password);
                        break;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

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
