package com.enation.eop.sdk.database;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.Page;
import com.enation.framework.database.impl.JdbcDaoSupport;
import com.enation.framework.util.ReflectionUtil;

/**
 * Saas式的数据库操作类，包装了JdbcDaoSupport<br/>
 * 自动设置的表名
 * 
 * @author kingapex 2010-1-10下午07:30:59
 * @param <T>
 */
public class BaseJdbcDaoSupport<T> extends JdbcDaoSupport<T> {
	private IDBRouter dbRouter;

	public void setDbRouter(IDBRouter dbRouter) {
		this.dbRouter = dbRouter;
	}

	public void insert(String table, Object po) {
		Map poMap = ReflectionUtil.po2Map(po);
		table = this.dbRouter.getTableName(table);
		super.insert(table, poMap);
	}

	public void execute(String sql, Object... args) {
		sql = wrapExeSql(sql);
		super.execute(sql, args);
	}

	public int getLastId(String table) {
		table = dbRouter.getTableName(table);
		return super.getLastId(table);
	}

	public void insert(String table, Map fields) {
		table = this.dbRouter.getTableName(table);
		super.insert(table, fields);
	}

	public int queryForInt(String sql, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForInt(sql, args);
	}

	public List<Map> queryForList(String sql, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForList(sql, args);
	}

	public List<T> queryForList(String sql, RowMapper mapper, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForList(sql, mapper, args);
	}

	public List<T> queryForList(String sql, Class clazz, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForList(sql, clazz, args);
	}

	public List<Map> queryForListPage(String sql, int pageNo, int pageSize,
			Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForListPage(sql, pageNo, pageSize, args);
	}

	public List<T> queryForList(String sql, int pageNo, int pageSize,
			RowMapper mapper) {
		sql = wrapSelSql(sql);
		return super.queryForList(sql, pageNo, pageSize, mapper);
	}

	public long queryForLong(String sql, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForLong(sql, args);
	}

	public String queryForString(String sql) {
		sql = wrapSelSql(sql);
		return super.queryForString(sql);
	}

	public Map queryForMap(String sql, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForMap(sql, args);
	}

	public T queryForObject(String sql, Class clazz, Object... args) {
		sql = wrapSelSql(sql);
		// System.out.println(sql);
		return super.queryForObject(sql, clazz, args);
	}

	public T queryForObject(String sql, ParameterizedRowMapper mapper,
			Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForObject(sql, mapper, args);
	}

	public Page queryForPage(String sql, int pageNo, int pageSize,
			Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForPage(sql, pageNo, pageSize, args);
	}

	public Page queryForPage(String sql, int pageNo, int pageSize,
			RowMapper rowMapper, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForPage(sql, pageNo, pageSize, rowMapper, args);
	}

	public Page queryForPage(String sql, int pageNo, int pageSize,
			Class<T> clazz, Object... args) {
		sql = wrapSelSql(sql);
		return super.queryForPage(sql, pageNo, pageSize, clazz, args);
	}

	public void update(String table, Map fields, Map where) {
		table = this.dbRouter.getTableName(table);
		super.update(table, fields, where);
	}

	public void update(String table, Map fields, String where) {
		table = this.dbRouter.getTableName(table);
		super.update(table, fields, where);
	}

	public void update(String table, T po, Map where) {
		table = this.dbRouter.getTableName(table);
		super.update(table, po, where);
	}

	public void update(String table, T po, String where) {
		table = this.dbRouter.getTableName(table);
		super.update(table, po, where);
	}

	/**
	 * 读取当前登录用户id
	 * 
	 * @return
	 */
	private Integer getCurrentUserId() {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		return userid;
	}

	/**
	 * 读取当前管理的站点
	 * 
	 * @return
	 */
	private Integer getCurrentSiteId() {
		Integer id = EopContext.getContext().getCurrentSite().getId();
		return id;
	}

	public String wrapExeSql(String sql) {
		String pattern;
		if (sql.indexOf("update") >= 0) {
			pattern = "(update\\s+)(\\w+)(.+)";

		} else if (sql.indexOf("delete") >= 0) {
			pattern = "(delete\\s+from\\s+)(\\w+)(.+)";
		} else if (sql.indexOf("insert") >= 0) {
			pattern = "(insert\\s+into\\s+)(\\w+)(.+)";
		} else if (sql.indexOf("truncate") >= 0) {
			pattern = "(truncate\\s+table\\s+)(\\w+)(.*?)";
		} else {
			return sql;
		}

		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		if (m.find()) {
			String tname = m.group(2);
			sql = m.replaceAll("$1 " + this.dbRouter.getTableName(tname)
					+ " $3");
		}

		return sql;
	}

	public static void main(String args[]) {
		// //on tb1.a1=tb2.a2 where w=1 group by g order by o
		//
		// String sql = "select * from tb1 left join tb2 ";
		// // select * from tb
		// String pattern = "(.*)from(\\s+)(\\w+)(\\s+)()";
		// // select *from tb
		// //sql=addWhereUid(sql);
		// System.out.println(sql);

		// String sql ="delete from bac  where b=2";
		// sql=wrapExeSql(sql);
		// System.out.println(sql);

		String pattern = "(truncate\\s+table\\s+)(\\w+)(.*?)";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher("truncate table menu");
		StringBuffer sb = new StringBuffer();

		if (m.find()) {
			String tname = m.group(2);
			System.out.println(m.replaceAll("$1 es_" + tname + "_2 $3"));
		}
	}

	/**
	 * 替换join句里的表名
	 * 
	 * @param sql
	 * @return
	 */
	public String rpJoinTbName(String sql) {

		String pattern = "(join\\s+)(\\w+)(\\s+)";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();

		if (m.find()) {
			String tname = m.group(2);

			m.appendReplacement(sb, "join " + this.dbRouter.getTableName(tname)
					+ " ");
		}
		m.appendTail(sb);

		return sb.toString();
	}

	/**
	 * 替换from句里的表名
	 * 
	 * @param sql
	 * @return
	 */
	public String rpFromTbName(String sql) {

		String pattern = "(from\\s+)(\\w+)(\\s*)";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();

		if (m.find()) {
			String tname = m.group(2);
			m.appendReplacement(sb, "from " + this.dbRouter.getTableName(tname)
					+ " ");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 替换select语句里的表名
	 * 
	 * @param sql
	 * @return
	 */
	public String rpSelTbName(String sql) {
		sql = rpJoinTbName(sql);
		sql = rpFromTbName(sql);
		return sql;
	}

	public String addUidTerm(String sql) {

		String term;
		if (sql.indexOf("where") > 1) {
			term = " and userid=" + getCurrentUserId() + " and siteid="
					+ getCurrentSiteId() + " ";
		} else {
			term = " where userid=" + getCurrentUserId() + " and siteid="
					+ getCurrentSiteId() + " ";
		}

		String pattern;
		if (sql.indexOf("group") > 1) {
			pattern = "(.+)(group\\s+by)";
		} else if (sql.indexOf("order") > 1) {
			pattern = "(.+)(order\\s+by)";
		} else {
			pattern = "(.+)($)";
		}

		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(sql);
		if (m.find()) {
			sql = m.replaceAll("$1 " + term + " $2");
		}
		return sql;
	}

	/**
	 * 将select语句包装为相应的saas sql
	 * 
	 * @param sql
	 * @return
	 */
	public String wrapSelSql(String sql) {
		sql = rpSelTbName(sql);
		// sql=addUidTerm(sql);
		return sql;
	}
}
