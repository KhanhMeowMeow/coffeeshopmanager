package com.example.controller.lib;

import java.sql.Statement;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class JDBC {

    public static Connection connection;

    public static void getConnection() {

        // String url = "jdbc:sqlserver://databasedemo.c9yoyeegc6h7.ap-southeast-2.rds.amazonaws.com:1900;databaseName=coffeeshopmanager;encrypt=true;trustServerCertificate=true;";
        // String username = "admin";
        // String password = "111111";
        
        String url = "jdbc:sqlserver://localhost:1433;databaseName=coffeeshopmanager;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "111111";
        
        try {
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void disConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
            connection.close();
        } catch (Exception e) {

        }
    }

    public static <T> List<T> select(String sql, Class<T> dto) {
        List<T> lists = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    int sqlType = meta.getColumnType(i);
                    row[i - 1] = getValue(rs, i, sqlType);
                }
                lists.add(ObjectUtil.ObjectsToClass(row, dto));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
        
    }

    public static <T, S> T selectOneObject(String sql, Class<T> dto, S value) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, value);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            List<T> lists = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    int sqlType = meta.getColumnType(i);
                    row[i - 1] = getValue(rs, i, sqlType);
                }
                lists.add(ObjectUtil.ObjectsToClass(row, dto));
            }
            return lists.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, S, X, Y> List<T> selectbjectsThreeValue(String sql, Class<T> dto, S values, X valuex, Y valuey) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, values);
            ps.setObject(2, valuex);
            ps.setObject(3, valuey);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            List<T> lists = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    int sqlType = meta.getColumnType(i);
                    row[i - 1] = getValue(rs, i, sqlType);
                }
                lists.add(ObjectUtil.ObjectsToClass(row, dto));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, S, X> List<T> selectbjectsTwoValue(String sql, Class<T> dto, S values, X valuex) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, values);
            ps.setObject(2, valuex);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            List<T> lists = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    int sqlType = meta.getColumnType(i);
                    row[i - 1] = getValue(rs, i, sqlType);
                }
                lists.add(ObjectUtil.ObjectsToClass(row, dto));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, S> List<T> selectbjectsOneValue(String sql, Class<T> dto, S values) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, values);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            List<T> lists = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    int sqlType = meta.getColumnType(i);
                    row[i - 1] = getValue(rs, i, sqlType);
                }
                lists.add(ObjectUtil.ObjectsToClass(row, dto));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void insert(String sql, T data) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Object[] dataObjects = ObjectUtil.ClasstToObjects(data);
            for (int i = 0; i < dataObjects.length; i++) {
                ps.setObject(i + 1, dataObjects[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> void delete(String sql, T id) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> void update(String sql, T data) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            Object[] dataObjects = ObjectUtil.ClasstToObjects(data);
            for (int i = 0; i < dataObjects.length - 1; i++) {
                ps.setObject(i + 1, dataObjects[i + 1]);
            }
            ps.setObject(dataObjects.length, dataObjects[0]);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(ResultSet rs, int index, int sqlType) throws SQLException {
        switch (sqlType) {
            case Types.INTEGER:
            case Types.SMALLINT:
            case Types.TINYINT:
                return rs.getInt(index);

            case Types.BIGINT:
                return rs.getLong(index);

            case Types.FLOAT:
            case Types.REAL:
            case Types.DOUBLE:
                return rs.getDouble(index);

            case Types.DECIMAL:
            case Types.NUMERIC:
                return rs.getBigDecimal(index);

            case Types.BIT:
            case Types.BOOLEAN:
                return rs.getBoolean(index);

            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
            case Types.NCHAR:
            case Types.NVARCHAR:
            case Types.LONGNVARCHAR:
                return rs.getString(index);

            case Types.DATE:
                return rs.getDate(index);

            case Types.TIME:
                return rs.getTime(index);

            case Types.TIMESTAMP:
                return rs.getTimestamp(index);

            case Types.BLOB:
                return rs.getBytes(index);

            default:
                return rs.getObject(index);
        }
    }
}
