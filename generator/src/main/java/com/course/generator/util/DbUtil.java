package com.course.generator.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author YanXin
 * @Date 2021/9/15
 **/
public class DbUtil {

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://8.135.14.136:3306/course_system";
			String user = "yx";
			String pass = "yx2016";
			conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static String getTableComment(String tableName) throws Exception {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT table_comment FROM information_schema.tables WHERE table_name = '" + tableName + "'");
		String tableNameCH = "";
		if(rs != null){
			while(rs.next()){
				tableNameCH = rs.getString("table_comment");
				break;
			}
		}
		rs.close();
		stmt.close();
		conn.close();
		System.out.println("表名:" + tableNameCH);
		return tableNameCH;
	}

	public static List<Field> getColumnByTableName(String tableName) throws Exception{
		List<Field> fieldList = new ArrayList<>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SHOW full COLUMNS FROM `" + tableName + "`");
		if(rs != null){
			while(rs.next()){
				String columnName = rs.getString("Field");
				String type = rs.getString("Type");
				String comment = rs.getString("Comment");
				Field field = new Field();
				field.setName(columnName);
				field.setNameHump(lineToHump(columnName));
				field.setNameBigHump(lineToBigHump(columnName));
				field.setType(type);
				field.setJavaType(DbUtil.sqlTypeToJavaType(rs.getString("Type")));
				field.setComment(comment);
				if(comment.contains("|")){
					field.setNameCn(comment.substring(0,comment.indexOf("|")));
				}else{
					field.setNameCn(comment);
				}
				fieldList.add(field);
			}
		}
		rs.close();
		stmt.close();
		conn.close();
		System.out.println("列信息:" + fieldList);
		return fieldList;
	}

	public static String lineToHump(String str){
		Pattern linePattern = Pattern.compile("_(\\w)");
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String lineToBigHump(String str){
		String s = lineToHump(str);
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}

	public static String sqlTypeToJavaType(String sqlType){
		if(sqlType.toUpperCase().contains("varchar".toUpperCase()) || sqlType.toUpperCase().contains("char".toUpperCase()) || sqlType.toUpperCase().contains("text".toUpperCase())){
			return "String";
		}else if(sqlType.toUpperCase().contains("datetime".toUpperCase())){
			return "Date";
		}else if (sqlType.toUpperCase().contains("int".toUpperCase())){
			return "Integer";
		}else if (sqlType.toUpperCase().contains("long".toUpperCase())){
			return "Long";
		}else if (sqlType.toUpperCase().contains("decimal".toUpperCase())){
			return "BigDecimal";
		}else{
			return "String";
		}
	}

}
