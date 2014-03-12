package com.enation.framework.database.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.Assert;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.DBRuntimeException;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.framework.util.ReflectionUtil;
import com.enation.framework.util.StringUtil;

/**
 * jdbc数据库操作支撑
 * 
 * @author kingapex 2010-1-6下午01:54:18
 * @param <T>
 */
public class JdbcDaoSupport<T> implements IDaoSupport<T> {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	protected final Logger logger = Logger.getLogger(getClass());

	public void execute(String sql, Object... args) {
		try {
			this.simpleJdbcTemplate.update(sql, args);
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		}
	}

	public int getLastId(String table) {
		if (EopSetting.DBTYPE.equals("1")) {
			return this.jdbcTemplate
					.queryForInt("SELECT last_insert_id() as id");
		} else if (EopSetting.DBTYPE.equals("2")) {
			int result = 0;
			result = this.jdbcTemplate.queryForInt("SELECT s_" + table
					+ ".currval as id from DUAL");
			return result;
		} else if (EopSetting.DBTYPE.equals("3")) {
			int result = 0;
			result = this.jdbcTemplate.queryForInt("select @@identity");
			return result;
		}
		throw new RuntimeException("未知的数据库类型");
	}

	public void insert(String table, Map fields) {
		String sql = "";

		try {
			Assert.hasText(table, "表名不能为空");
			Assert.notEmpty(fields, "字段不能为空");
			table = quoteCol(table);

			Object[] cols = fields.keySet().toArray();
			Object[] values = new Object[cols.length];
			for (int i = 0; i < cols.length; i++) {
				values[i] = fields.get(cols[i]);
				cols[i] = quoteCol(cols[i].toString());
			}

			sql = "INSERT INTO " + table + " ("
					+ StringUtil.implode(", ", cols);

			sql = sql + ") VALUES (" + StringUtil.implodeValue(", ", values);

			sql = sql + ")";
			// System.out.println(sql);
			jdbcTemplate.update(sql, values);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new DBRuntimeException(e, sql);
		}
	}

	public void insert(String table, Object po) {
		insert(table, ReflectionUtil.po2Map(po));
	}

	public int queryForInt(String sql, Object... args) {
		try {
			return this.simpleJdbcTemplate.queryForInt(sql, args);
		} catch (RuntimeException e) {
			this.logger.error(e.getMessage(), e);
			throw e;
		}
	}

	public String queryForString(String sql) {
		String s = "";
		try {
			s = (String) this.jdbcTemplate.queryForObject(sql, String.class);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public List queryForList(String sql, Object... args) {
		return this.jdbcTemplate.queryForList(sql, args);
	}

	public List<T> queryForList(String sql, RowMapper mapper, Object... args) {
		try {
			return this.jdbcTemplate.query(sql, args, mapper);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	public List<T> queryForList(String sql, Class clazz, Object... args) {
		return this.simpleJdbcTemplate.query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);
	}

	public List queryForListPage(String sql, int pageNo, int pageSize,
			Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = this.buildPageSql(sql, pageNo, pageSize);
			return queryForList(listSql, args);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	public List<T> queryForList(String sql, int pageNo, int pageSize,
			RowMapper mapper) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = this.buildPageSql(sql, pageNo, pageSize);
			return this.queryForList(listSql, mapper);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	public long queryForLong(String sql, Object... args) {
		return this.jdbcTemplate.queryForLong(sql, args);
	}

	public Map queryForMap(String sql, Object... args) {
		// Map map = this.simpleJdbcTemplate.queryForMap(sql, args);
		try {
			Map map = this.jdbcTemplate.queryForMap(sql, args);
			if (EopSetting.DBTYPE.equals("2")) {
				Map newMap = new HashMap();
				Iterator keyItr = map.keySet().iterator();
				while (keyItr.hasNext()) {
					String key = (String) keyItr.next();
					Object value = map.get(key);
					newMap.put(key.toLowerCase(), value);
				}
				return newMap;
			} else
				return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ObjectNotFoundException(ex, sql);
		}
	}

	public T queryForObject(String sql, Class clazz, Object... args) {
		try {
			return (T) simpleJdbcTemplate
					.queryForObject(sql, ParameterizedBeanPropertyRowMapper
							.newInstance(clazz), args);
			// return (T) this.jdbcTemplate.queryForObject(sql, args, clazz);
		} catch (Exception ex) {
			// ex.printStackTrace();
			// throw new ObjectNotFoundException(ex, sql);
			this.logger.error("查询出错", ex);
			return null;
		}
	}

	public Page queryForPage(String sql, int pageNo, int pageSize,
			Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List list = queryForList(listSql, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	public Page queryForPage(String sql, int pageNo, int pageSize,
			RowMapper rowMapper, Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, rowMapper, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	public Page queryForPage(String sql, int pageNo, int pageSize,
			Class<T> clazz, Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, clazz, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}

	public void update(String table, Map fields, Map where) {
		String whereSql = "";

		if (where != null) {
			Object[] wherecols = where.keySet().toArray();
			for (int i = 0; i < wherecols.length; i++) {
				wherecols[i] = quoteCol(wherecols[i].toString()) + "="
						+ quoteValue(where.get(wherecols[i]).toString());
			}
			whereSql += StringUtil.implode(" AND ", wherecols);
		}
		update(table, fields, whereSql);
	}

	public void update(String table, T po, Map where) {
		String whereSql = "";
		// where值
		if (where != null) {
			Object[] wherecols = where.keySet().toArray();
			for (int i = 0; i < wherecols.length; i++) {
				wherecols[i] = quoteCol(wherecols[i].toString()) + "="
						+ quoteValue(where.get(wherecols[i]).toString());
			}
			whereSql += StringUtil.implode(" AND ", wherecols);
		}
		update(table, ReflectionUtil.po2Map(po), whereSql);
	}

	public void update(String table, T po, String where) {
		this.update(table, ReflectionUtil.po2Map(po), where);
	}

	public void update(String table, Map fields, String where) {
		String sql = "";
		try {
			Assert.hasText(table, "表名不能为空");
			Assert.notEmpty(fields, "字段不能为空");
			Assert.hasText(where, "where条件不能为空");
			table = quoteCol(table);

			if ("3".equals(EopSetting.DBTYPE)) { // SQLServer主键不允许更新，执行update前先删除
				try {
					where = where.replaceAll(" ", "");
					String key = where.split("=")[0];
					fields.remove(key);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("SQLServer数据库更新异常，可能需要单独处理！");
				}
			}

			// 字段值
			Object[] cols = fields.keySet().toArray();

			Object[] values = new Object[cols.length];
			for (int i = 0; i < cols.length; i++) {
				values[i] = fields.get(cols[i]);
				cols[i] = quoteCol(cols[i].toString()) + "=?";
			}

			sql = "UPDATE " + table + " SET " + StringUtil.implode(", ", cols)
					+ " WHERE " + where;

			simpleJdbcTemplate.update(sql, values);
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		}
	}

	public String buildPageSql(String sql, int page, int pageSize) {
		String sql_str = null;

		String db_type = EopSetting.DBTYPE;
		if (db_type.equals("1")) {
			db_type = "mysql";
		} else if (db_type.equals("2")) {
			db_type = "oracle";
		} else if (db_type.equals("3")) {
			db_type = "sqlserver";
		}

		if (db_type.equals("mysql")) {
			sql_str = sql + " LIMIT " + (page - 1) * pageSize + "," + pageSize;
		} else if (db_type.equals("oracle")) {
			StringBuffer local_sql = new StringBuffer(
					"SELECT * FROM (SELECT t1.*,rownum sn1 FROM (");
			local_sql.append(sql);
			local_sql.append(") t1) t2 WHERE t2.sn1 BETWEEN ");
			local_sql.append((page - 1) * pageSize + 1);
			local_sql.append(" AND ");
			local_sql.append(page * pageSize);
			sql_str = sql.toString();
		} else if (db_type.equals("sqlserver")) {
			StringBuffer local_sql = new StringBuffer();
			// 找到order by 子句
			String order = SqlPaser.findOrderStr(sql);

			// 剔除order by 子句
			sql = sql.replaceAll(order, "");

			// 拼装分页sql
			local_sql.append("select * from (");
			local_sql.append(SqlPaser.insertSelectField("ROW_NUMBER() Over("
					+ order + ") as rowNum", sql));
			local_sql.append(") tb where rowNum between ");
			local_sql.append((page - 1) * pageSize + 1);
			local_sql.append(" AND ");
			local_sql.append(page * pageSize);

			// System.out.println(sql);
			return sql.toString();

		}
		return sql_str.toString();
	}

	/**
	 * 格式化列名 只适用于Mysql
	 * 
	 * @param col
	 * @return
	 */
	private String quoteCol(String col) {
		if (col == null || col.equals("")) {
			return "";
		} else {
			return col;
		}
	}

	/**
	 * 格式化值 只适用于Mysql
	 * 
	 * @param value
	 * @return
	 */
	private String quoteValue(String value) {
		if (value == null || value.equals("")) {
			return "''";
		} else {
			return "'" + value.replaceAll("'", "''") + "'";
		}
	}

	/**
	 * 去除sql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

	public T queryForObject(String sql, ParameterizedRowMapper mapper,
			Object... args) {
		try {
			T t = (T) this.simpleJdbcTemplate.queryForObject(sql, mapper, args);
			return t;
		} catch (RuntimeException e) {
			return null;
		}
	}
}
