package ms.ynov.commentapi.dto;

import java.sql.Date;

import ms.ynov.commentapi.model.User;

public class CommentR {
	
	private Integer id;
	
	private User user;
	
	private Date date;
	
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
