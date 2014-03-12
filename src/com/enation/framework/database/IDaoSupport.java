package com.enation.framework.database;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * 数据库操作支撑接口
 * @author kingapex
 * 2010-1-6下午01:33:13
 * @param <T>
 */
public interface IDaoSupport<T> {
	
	/**执行sql语句**/
	public void execute(String sql, Object... args) ;
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>int</code>型返回
	 * @param sql 查询的sql语句，确定结果为一行一列，且为数字型
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public int queryForInt(String sql, Object... args);	
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>long</code>型返回
	 * @param sql 查询的sql语句，确保结果为一行一列，且为数字型
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public long queryForLong(String sql, Object... args);
	
	public String queryForString(String sql);
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>T</code>对象返回
	 * @param sql 查询的sql语句,确保结果列名和对象属性对应
	 * @param clazz  <code>T</code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public T queryForObject(String sql, Class clazz, Object... args);
 
	public T queryForObject(String sql, ParameterizedRowMapper mapper, Object... args) ;
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>Map</code>对象返回
	 * @param sql 查询的sql语句
	 * @param args 对应sql语句中的参数值
	 * @return 以结果集中的列为key，值为value的<code>Map</code>
	 */
	@SuppressWarnings("unchecked")
	public Map queryForMap(String sql, Object... args) ;
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List<Map></code>
	 * @param sql 查询的sql语句
	 * @param args 对应sql语句中的参数值
	 * @return  列表中元素为<code>Map</code>的<code>List</code>,<br/>Map结构：以结果集中的列为key，值为value,
	 */
	@SuppressWarnings("unchecked")
	public List<Map> queryForList(String sql, Object... args);
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List<T></code>
	 * @param sql  查询的sql语句
	 * @param mapper 列和对象属性的Mapper
	 * @param args 对应sql语句中的参数值
	 * @return 列表中元素为<code>T</code>的<code>List</code>
	 */
	public List<T> queryForList(String sql, RowMapper mapper, Object... args) ;
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List<T></code>
	 * @param sql 查询的sql语句
	 * @param clazz <code><T></code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return  列表中元素为<code>T</code>的<code>List</code>
	 */
	public List<T> queryForList(String sql, Class clazz, Object... args);
	
	/**
	 * 分页查询多行结果集<br/>
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize  每页数量
	 * @param args  对应sql语句中的参数值
	 * @return 列表中元素为<code>Map</code>的<code>List</code>,<br/>Map结构：以结果集中的列为key，值为value,
	 */
	@SuppressWarnings("unchecked")
	public List<Map> queryForListPage(String sql, int pageNo, int pageSize, Object... args);
	
	/**
	 * 分页查询多行结果集<br/>
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param mapper 列和对象属性的Mapper
	 * @return 列表中元素为<code>T</code>的<code>List</code>
	 */ 
	public List<T> queryForList(String sql,  int pageNo, int pageSize,RowMapper mapper);
	
	/**
	 * 分页查询
	 * @param sql  查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize  每页数量
	 * @param args  对应sql语句中的参数值
	 * @return 分页结果集对象
	 */
	public Page queryForPage(String sql, int pageNo, int pageSize, Object... args) ;
	
	/**
	 *  分页查询
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param rowMapper 列和对象属性的Mapper
	 * @param args 对应sql语句中的参数值
	 * @return 分页结果集对象
	 */
	public Page queryForPage(String sql,int pageNo, int pageSize, RowMapper rowMapper, 	Object... args);
	
	/**
	 * 分页查询
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param clazz  <code><T></code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public Page queryForPage(String sql, int pageNo, int pageSize, Class<T> clazz, Object... args);
	
	/**
	 * 更新数据
	 * @param table 表名
	 * @param fields 字段-值Map
	 * @param where 更新条件(字段-值Map)
	 */
	@SuppressWarnings("unchecked")
	public void update(String table, Map fields, Map where);
	
	/**
	 * 更新数据
	 * @param table  表名
	 * @param fields 字段-值Map
	 * @param where 更新条件,如"a='1' AND b='2'"
	 */
	@SuppressWarnings("unchecked")
	public void update(String table, Map fields, String where);
	
	/**
	 * 更新数据
	 * @param table 表名
	 * @param po 要更新的对象，保证对象的属性名和字段名对应
	 * @param where 更新条件(字段-值Map)
	 */
	@SuppressWarnings("unchecked")
	public void update(String table, T po, Map where);
	
	/**
	 * 更新数据
	 * @param table 表名
	 * @param po 要更新的对象，保证对象的属性名和字段名对应
	 * @param where 更新条件,如"a='1' AND b='2'"
	 */
	public void update(String table, T po, String where) ;
	
	/**
	 * 新增数据
	 * @param table  表名
	 * @param fields 字段-值Map
	 */
	@SuppressWarnings("unchecked")
	public void insert(String table, Map fields);
		
	/**
	 * 新增数据
	 * @param table 表名
	 * @param po 要新增的对象，保证对象的属性名和字段名对应
	 */
	public void insert(String table, Object po);
		
	/**
	 * 获取当前事务最后一次更新的主键值
	 * @return
	 */
	public int getLastId(String table);
	
 
	/**
	 * 
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public String buildPageSql(String sql, int page, int pageSize);
}
