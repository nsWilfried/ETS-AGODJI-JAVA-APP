package com.ets__agodji.Dao;

import com.ets__agodji.Models.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public abstract class AllDao {

    private final static String DATABASE_URL =
            "jdbc:sqlite:/home/anonym/Desktop/ets__agodji/src/com/ets__agodji/Resources/database/ets_agodji_db.sqlite";
    static ConnectionSource  connectionSource = null;

    // connect data to jdbc
    public static void Connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
    }

    // Users dao
    public static Dao<Users, String> UserDao() throws SQLException {
         Dao<Users, String> usersDao = DaoManager.createDao(connectionSource, Users.class);
         return usersDao;
    }

    // Customers dao
    public static Dao<Customers, String> CustomerDao() throws SQLException {
        Dao<Customers, String> customersDao = DaoManager.createDao(connectionSource, Customers.class);
        return customersDao;
    }

    // Categories dao
    public static Dao<Categories, String> CategoryDao() throws SQLException {
        Dao<Categories, String> categoriesDao = DaoManager.createDao(connectionSource, Categories.class);
        return categoriesDao;
    }

    // Products dao
    public static Dao<Products, String> ProductDao() throws SQLException{
        Dao<Products,String> productDao = DaoManager.createDao(connectionSource, Products.class);
        return productDao;
    }

    // Providers dao
    public static Dao<Providers, String> ProviderDao() throws SQLException {
        Dao<Providers, String> providerDao = DaoManager.createDao(connectionSource, Providers.class);
        return providerDao;
    }

    // Sales dao
    public static Dao<Sales, String> SaleDao() throws SQLException {
        Dao<Sales, String> saleDao = DaoManager.createDao(connectionSource, Sales.class);
        return saleDao;
    }

    // SalesProducts dao
    public static Dao<SalesProducts, String> SaleProductDao() throws SQLException{
        Dao<SalesProducts, String> saleProductDao = DaoManager.createDao(connectionSource, SalesProducts.class);
        return saleProductDao;
    }

    public static void BuildTables() throws SQLException {
        Connect();
        TableUtils.createTableIfNotExists(connectionSource, Users.class);
        TableUtils.createTableIfNotExists(connectionSource, Customers.class);
        TableUtils.createTableIfNotExists(connectionSource, Categories.class);
        TableUtils.createTableIfNotExists(connectionSource, Products.class);
        TableUtils.createTableIfNotExists(connectionSource, Providers.class);
        TableUtils.createTableIfNotExists(connectionSource, Sales.class);
        TableUtils.createTableIfNotExists(connectionSource, SalesProducts.class);

    }
}
