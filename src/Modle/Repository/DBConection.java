package Modle.Repository;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConection {
    private static final String USER = "sa";
    private static final String PASSWORD = "kha@1205";
    private static final String SERVER = "EMKHA\\MSSQLSERVER01";
    private static final String DATABASE = "NgoiSaoTinHoc";
    private static final int PORT = 1433;

    public static Connection getConnection() throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        ds.setServerName(SERVER);
        ds.setPortNumber(PORT);
        ds.setDatabaseName(DATABASE);
        //ds.setEncrypt(false); // Không dùng SSL
        ds.setTrustServerCertificate(true); // Bỏ qua kiểm tra chứng chỉ

        return ds.getConnection();
    }
}
