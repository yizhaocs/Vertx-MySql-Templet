package com.fuhu;

public class SingletonOfQueryGenerator {
	private static SingletonOfQueryGenerator instance = null;

	private SingletonOfQueryGenerator() {

	}

	public static SingletonOfQueryGenerator getInstance() {
		if (instance == null) {
			instance = new SingletonOfQueryGenerator();
		}
		return instance;
	}

	public String insert(String table, String columns, String values) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(table);
		sb.append("(");
		sb.append(columns);
		sb.append(")");
		sb.append(" VALUES ");
		sb.append("(");
		sb.append(values);
		sb.append(")");
		sb.append(";");
		return sb.toString();
	}

	public String upsert(String table, String[] insertColumns, String[] values, String[] updateColumns) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(table);
		sb.append("(");
		for (int i = 0; i < insertColumns.length; i++) {
			sb.append(insertColumns[i]);
			if (i + 1 < insertColumns.length) {
				sb.append(",");
			}
		}
		sb.append(")");
		sb.append(" VALUES ");
		sb.append("(");
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i + 1 < values.length) {
				sb.append(",");
			}
		}
		sb.append(")");
		sb.append(" ON DUPLICATE KEY UPDATE ");
		for (int i = 0; i < updateColumns.length; i++) {
			sb.append(updateColumns[i]);
			sb.append("=VALUES(");
			sb.append(updateColumns[i]);
			sb.append(")");
			if (i + 1 < updateColumns.length) {
				sb.append(",");
			}
		}
		sb.append(";");
		return sb.toString();
	}

	public String select(String a1, String table, String[] whereClauseCoulmns, String[] whereClauseValues) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(a1);
		sb.append(" FROM ");
		sb.append(table);
		sb.append(" WHERE ");
		sb.append(whereClauseBuilder(whereClauseCoulmns, whereClauseValues));
		sb.append(";");
		return sb.toString();
	}

	public String update(String table, String[] updateCoulmns, String[] updateValues, String[] whereClauseCoulmns, String[] whereClauseValues) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(table);
		sb.append(" SET ");
		for (int i = 0; i < updateCoulmns.length; i++) {
			sb.append(updateCoulmns[i]);
			sb.append("=");
			sb.append(updateValues[i]);
			if (i + 1 < updateCoulmns.length) {
				sb.append(",");
			}
		}
		sb.append(" WHERE ");
		sb.append(whereClauseBuilder(whereClauseCoulmns, whereClauseValues));
		sb.append(";");
		return sb.toString();
	}
	
	public String delete(String table, String[] whereClauseCoulmns, String[] whereClauseValues) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(table);
		sb.append(" WHERE ");
		sb.append(whereClauseBuilder(whereClauseCoulmns, whereClauseValues));
		sb.append(";");
		return sb.toString();
	}

	private String whereClauseBuilder(String[] whereClauseCoulmns, String[] whereClauseValues) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < whereClauseCoulmns.length; i++) {
			sb.append(whereClauseCoulmns[i]);
			sb.append("=");
			sb.append(whereClauseValues[i]);
			if (i + 1 < whereClauseCoulmns.length) {
				sb.append(" AND ");
			}
		}
		return sb.toString();
	}
}
