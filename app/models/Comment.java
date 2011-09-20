package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;


/**
 * コメントのモデルです。
 * @author nishinaka_s
 */
@Entity
public class Comment extends Model {

	/**
	 * 関連する本です。
	 */
	@ManyToOne
	public Book book;
	
	/**
	 * 関連するユーザです。
	 */
	@ManyToOne
	public User user;
	
	/**
	 * コメントです。
	 */
	public String comment;
}
