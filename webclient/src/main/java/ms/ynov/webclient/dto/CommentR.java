package ms.ynov.webclient.dto;

import java.sql.Date;

public class CommentR {
	private Integer id;

	private Integer user;

	private Date date;

	private String content;
	
	private Integer article;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}
	
}
