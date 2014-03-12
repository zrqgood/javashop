package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enation.javashop.core.model.Article;

public class ArticleMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		Article article = new Article();
		article.setTitle(rs.getString("title"));
		article.setContent(rs.getString("content"));
		article.setId(rs.getInt("id"));
		article.setCreate_time(rs.getLong("create_time"));

		return article;
	}

}
