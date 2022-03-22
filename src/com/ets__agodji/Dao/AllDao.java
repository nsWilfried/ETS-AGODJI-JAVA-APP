package com.ets__agodji.Dao;

import com.ets__agodji.Models.Users;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;


public abstract class AllDao {

    private final static String DATABASE_URL =
            "jdbc:sqlite:C:\\Users\\DELL\\IdeaProjects\\ets__agodji\\src\\com\\ets__agodji\\Resources\\database\\ets_agodji_db.sqlite";
    static ConnectionSource  connectionSource = null;

    public static void Connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
    }

    public static Dao<Users, String> UserDao() throws SQLException {
         Dao<Users, String> usersDao = DaoManager.createDao(connectionSource, Users.class);
         return usersDao;
    }

    public static void BuildTables() throws SQLException {
        Connect();
        TableUtils.createTableIfNotExists(connectionSource, Users.class);
        System.out.println("Base de données crée");
    }
}
