package ms.ynov.articleapi.dto;

import java.sql.Date;

import ms.ynov.articleapi.model.Category;
import ms.ynov.articleapi.model.Comment;
import ms.ynov.articleapi.model.User;

public class ArticleR {
	 private Integer id;

    private Category category;

    private User user;

    private Date date;
    
    private String content;
    
    private Iterable<Comment> comments;
    
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Iterable<Comment> getComments() {
		return comments;
	}

	public void setComments(Iterable<Comment> comments) {
		this.comments = comments;
	}
}
