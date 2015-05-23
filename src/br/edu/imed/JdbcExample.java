package br.edu.imed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.imed.model.Customer;

public class JdbcExample {

	public static void main(String[] argv) throws SQLException {

		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;

		try {

			String connectionString = "jdbc:postgresql://127.0.0.1:5432/juris";
			String user = "postgres";
			String password = "";
			connection = DriverManager.getConnection(connectionString, user, password);
			checkConnection(connection);
			runSelectCommand(connection);
			runInsertCommando(connection, new Customer(0L,""));
			connection.close();			

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}

	private static void runInsertCommando(Connection connection, Customer customer) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			connection.setAutoCommit(false);
			String insertCommand = "INSERT INTO CUSTOMER(codigo, nome) VALUES (?, ?)";
			prepareStatement = connection.prepareStatement(insertCommand);
			prepareStatement.setLong(1, customer.getCodigo());
			prepareStatement.setString(2, customer.getName());
			prepareStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally{
			prepareStatement.close();
		}
	}

	private static void checkConnection(Connection connection) {
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
			System.exit(0);
		}
	}

	private static void runSelectCommand(Connection connection) {
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
			while (rs.next()) {
				  Long codigo = rs.getLong("codigo");
				  String name = rs.getString("nome");
				  System.out.println(String.format("Código: %s",codigo));
				  System.out.println(String.format("Nome: %s",name));
				}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
