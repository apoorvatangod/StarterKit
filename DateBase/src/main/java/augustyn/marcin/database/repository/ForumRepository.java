package augustyn.marcin.database.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import augustyn.marcin.database.converter.ResultSetToLinesConverter;

public class ForumRepository {
	private static final Logger logger = LogManager.getLogger(ForumRepository.class);
	private static final String DB_URL = "jdbc:mysql://localhost:3306/forum";
	private static final String USER = "user";
	private static final String PASSWORD = "user";
	ResultSetToLinesConverter converter = new ResultSetToLinesConverter();

	public List<String> executeDbQuery(String tableName, int fromRowIdx, int rowsToRead) {
		// Vector<Object> resultVector = null;
		List<String> rowsAsStringList = new ArrayList<>();
		if(tableName.matches("\\W")){
			return null;
		}
		try {
			Connection con = DriverManager.getConnection(DB_URL + "?" + "user=" + USER + "&password=" + PASSWORD + "&useSSL=true");
			PreparedStatement statement;
			String query;
			ResultSet resultSet = null;
			if (rowsToRead < 1) {
				query = String.format("SELECT count(*) FROM forum.%s;", tableName);
				statement = con.prepareStatement(query);
				resultSet = statement.executeQuery();
				resultSet.first();
				rowsToRead = resultSet.getInt("count(*)");
			}
			query = String.format("SELECT * FROM forum.%s LIMIT %d OFFSET %d;", tableName, rowsToRead, fromRowIdx);
			statement = con.prepareStatement(query);
			resultSet = statement.executeQuery();

			rowsAsStringList = converter.convertResultSet(resultSet);

			resultSet.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			logger.info(e);
		}
		return rowsAsStringList;
	}

	public String getDbHeaderRow(String tableName) {
		String headerRow = "";
		try {
			Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String query = String.format("SELECT * FROM forum.%s;", tableName);
			PreparedStatement headerStatement = con.prepareStatement(query);
		
			ResultSetMetaData metadata = headerStatement.getMetaData();
			headerRow = converter.getHeadersRow(metadata);
			
			con.close();
			
		} catch (SQLException e) {
			logger.info(e);
		}
		return headerRow;
	}

	public String getColumnDataTypeByColumnName(String tableName, String columnName, Connection con) {
		String columnType = "";
		try {
			//con = DriverManager.getConnection(DB_URL + "?" + "user=" + USER + "&password=" + PASSWORD + "&useSSL=true");
			String query = String.format("SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = ? AND COLUMN_NAME = ?;");
			PreparedStatement dataTypeStatement = con.prepareStatement(query);
			dataTypeStatement.setString(1, tableName);
			dataTypeStatement.setString(2, columnName);
			dataTypeStatement.executeQuery();
			ResultSet dataTypeResultSet = dataTypeStatement.getResultSet();
			dataTypeResultSet.first();
			columnType = dataTypeResultSet.getString(1);

		} catch (SQLException e) {
			logger.info(e);
		}
		return columnType;
	}
	public void insertDataSetToTable(String tableName, List<List<String>> data) {
		
		 Connection con = null; 
		 try { 
			 con = DriverManager.getConnection(DB_URL + "?" + "user=" + USER + "&password=" + PASSWORD + "&useSSL=true");
			 Statement newStatment = con.createStatement(); 
			 newStatment.executeUpdate("INSERT INTO " + tableName + createColumnNameStringForQuery(data) + " VALUES "
						+ createValuesStringForQuery(data, tableName, con));
		 } catch (SQLException e) { 
			 logger.info(e);
		 }
	}

	private String createColumnNameStringForQuery(List<List<String>> data) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ( ");
		for (String columnName : data.get(0)) {
			sb.append(columnName).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" )");
		return sb.toString();
	}

	private String createValuesStringForQuery(List<List<String>> data, String tableName, Connection con) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < data.size(); i++) {
			List<String> row = data.get(i);
			sb.append("( ");
			for (int j = 0; j < row.size(); j++) {
				if("varchar".equals(getColumnDataTypeByColumnName(tableName, data.get(0).get(j), con))){
					sb.append("'");
				}
				sb.append(row.get(j));
				if("varchar".equals(getColumnDataTypeByColumnName(tableName, data.get(0).get(j), con))){
					sb.append("'");
				}
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" ),");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(";");
		return sb.toString();
	}
}
/////////////////////////////// NOTATKI - ALTERNATYWNA
/////////////////////////////// WERSJA///////////////////////////////

/*
 * private Vector<Object> convertResultSetToVector(ResultSet rs) throws
 * SQLException{ Vector<Object> dataAsVector = new Vector<>(); rs.first(); do{
 * Vector<Object> row = new Vector<>(); for(int i = 0; i <
 * rs.getMetaData().getColumnCount(); i++){ row.add(i, rs.getString(i + 1)); }
 * dataAsVector.add(row); }while(rs.next()); return dataAsVector; }
 */