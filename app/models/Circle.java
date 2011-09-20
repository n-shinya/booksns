package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * サークルのモデルです。
 * @author nishinaka_s
 */
@Entity
public class Circle extends Model {

	/**
	 * 名前です。
	 */
	public String name;
	
	/**
	 * サークルを利用するためのキーです。
	 */
	public String secretKey;
}
