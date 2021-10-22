package info.seleniumcucumber.utils.dataproviders;

//import com.mysql.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.concurrent.Executors;

public final class DBConnection {
    public Connection conn;
    private Statement statement;
    public static DBConnection db;
    private ConfigFileReader configFileReader = new ConfigFileReader();
    


    private DBConnection() {
        String url = configFileReader.getDBUrl();
        String dbName = configFileReader.getDBName();
        String driver = "com.mysql.jdbc.Driver";
        String userName = configFileReader.getDBUserName();
        String password = configFileReader.getDBPassWord();

        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
            this.conn.setNetworkTimeout(Executors.newSingleThreadExecutor(), 60 * 60 * 1000);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static synchronized DBConnection getDbCon() {
        if ( db == null ) {
            db = new DBConnection();
        }
        return db;
 
    }
    
    public int insertRow(String coloumn, String columnNames, String values) throws SQLException {
        statement = db.conn.createStatement();
        String insertQuery = "INSERT INTO `automation_zrx`.`"+coloumn+"` "+columnNames+" VALUES "+values+";" ;
        int result = statement.executeUpdate(insertQuery);
        System.out.println("done");
        return result;
    }

    public int updateRow(String table, String col_val, String condition) throws SQLException {

        statement = db.conn.createStatement();
        String updateQuery = "update " + table + " set " + col_val + " where " + condition;
        int result = statement.executeUpdate(updateQuery);
        System.out.println("done");
        return result;

    }

    public int deleteRow(String table, String condition) throws SQLException {

        statement = db.conn.createStatement();
        String deleteQuery = "DELETE FROM " + table + " where " + condition;
        int result = statement.executeUpdate(deleteQuery);
        System.out.println("done");
        return result;


    }
    
    public int truncateTable(String table) throws SQLException {

        statement = db.conn.createStatement();
        String truncateQuery = "TRUNCATE TABLE " + table;
        int result = statement.executeUpdate(truncateQuery);
        System.out.println("done");
        return result;

    }
    
    
    public String getData(String query, String coloumn) throws SQLException {
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        res.next();
        String getValue = res.getString(coloumn);
        return getValue;

    }


//    public static String getProperties(String name) {
//
//            String val = null;
//            Helper getHelp = new Helper();
//            getHelp.set_path("src/main/resources/Constants.properties");
//            try {
//                 val = getHelp.loadProperties(name);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return val;
//        }

 
}