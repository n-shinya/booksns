package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

/**
 * 本のモデルです。
 * @author nishinaka_s
 */
@Entity
public class Book extends Model {

	/**
	 * タイトルです。
	 */
	public String title;
	
	/**
	 * 作者です。
	 */
	public String author;
	
	/**
	 * 出版社です。
	 */
	public String publisher;
	
	/**
	 * 電子書籍かどうかを表します。
	 */
	public boolean ebook;
	
	/**
	 * 画像URLです。
	 */
	public String imageUrl;
	
	/**
	 * 関連するユーザです。
	 */
	@ManyToMany
	public List<User> users;
}
