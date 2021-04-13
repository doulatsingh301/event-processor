package com.cs.server.logs.database;

import com.cs.server.logs.model.DbEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class EventsDao {

    private Connection connection  = null;
    private static final int BATCH_SIZE = 1000;

    public void createConnection(){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection =  DriverManager.getConnection("jdbc:hsqldb:file:cs-db", "SA", "");
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void createTable(){
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE EVENTS( " +
                    "id VARCHAR(100) NOT NULL, duration int, " +
                    "host VARCHAR(80), type VARCHAR(100), alert BOOLEAN, " +
                    "PRIMARY KEY (id));");
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


    public void insertDbRecords(List<DbEvent> eventsForDatabase){
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO EVENTS (id, duration, host, type, alert) VALUES(?,?,?,?,?)");

            for (int i = 0; i < eventsForDatabase.size(); i++) {
                preparedStatement.setString(1, eventsForDatabase.get(i).getId());
                preparedStatement.setLong(2, eventsForDatabase.get(i).getDuration());
                preparedStatement.setString(3, eventsForDatabase.get(i).getHost());
                preparedStatement.setString(4, eventsForDatabase.get(i).getType());
                preparedStatement.setBoolean(5, eventsForDatabase.get(i).isAlert());
                preparedStatement.addBatch();
                if(i % BATCH_SIZE == 0)
                    preparedStatement.executeBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
