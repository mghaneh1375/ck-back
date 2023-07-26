package bogen.vr.ck;

import bogen.vr.ck.Utility.Jobs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
@ComponentScan({"bogen.vr.ck.Routes", "bogen.vr.ck.Security"})
@Configuration
public class CkApplication {

	final static private String username = "root";
	final static private String password = "Ghhy@110";

	final static private String dbName = "ck";

	public static Connection con = null;

	private static void setupDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?useUnicode=true&characterEncoding=UTF-8", username, password);
			Statement st = con.createStatement();
			st.executeUpdate("SET GLOBAL WAIT_TIMEOUT = 315360");
			st.executeUpdate("SET GLOBAL INTERACTIVE_TIMEOUT = 315360");
		}
		catch (Exception x) {
			x.printStackTrace();
		}

	}

	public static void main(String[] args) {

		setupDB();
		new Thread(new Jobs()).start();

		SpringApplication.run(CkApplication.class, args);
	}

}
