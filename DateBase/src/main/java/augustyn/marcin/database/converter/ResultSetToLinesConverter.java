package augustyn.marcin.database.converter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class ResultSetToLinesConverter {
	
	public List<String> convertResultSet(ResultSet rs) throws SQLException{
		List<String> dataAsStrings = new ArrayList<>();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		String columnFormat = "|";

		   
		for(int i = 0; i < rs.getMetaData().getColumnCount(); i++){
			columnFormat += " %-" + rsmd.getColumnDisplaySize(i + 1) + "s |";
		}
		
		rs.first();
		do{
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			List<String> row = new ArrayList<>();
			for(int i = 0; i < rs.getMetaData().getColumnCount(); i++){
				row.add(i, rs.getString(i + 1));
			}
			formatter.format(columnFormat, row.toArray());
			dataAsStrings.add(sb.toString());
			formatter.close();
		}while(rs.next());
		
		return dataAsStrings;
	}
	
	public String getHeadersRow(ResultSetMetaData rsmd) throws SQLException{

		String columnFormat = "|";

		for(int i = 0; i < rsmd.getColumnCount(); i++){
			columnFormat += " %-" + rsmd.getColumnDisplaySize(i + 1) + "s |";
		}

		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		List<String> headerRow = new ArrayList<>();
		for(int i = 0; i < rsmd.getColumnCount(); i++){
			headerRow.add(i, rsmd.getColumnLabel(i + 1));
		}
		formatter.format(columnFormat, headerRow.toArray());
		formatter.close();
		
		return sb.toString();
	}
}
