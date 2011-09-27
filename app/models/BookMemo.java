package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * 本のユーザメモのモデルです。
 * @author nishinaka_s
 */
@Entity
public class BookMemo extends Model {

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
	 * ステータスです。
	 */
	public String status;
	
	/**
	 * コメントです。
	 */
	public String comment;
	
	
}
