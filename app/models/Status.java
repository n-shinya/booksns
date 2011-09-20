package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * 本のステータスのモデルです。
 * @author nishinaka_s
 */
@Entity
public class Status extends Model {

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
}
