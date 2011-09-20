package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

/**
 * ユーザのモデルです。
 * @author nishinaka_s
 */
@Entity
public class User extends Model {

	/**
	 * 名前です。
	 */
	public String name;
	
	/**
	 * パスワードです。
	 */
	public String password;
	
	/**
	 * 所属するサークルです。
	 */
	@ManyToMany
	public List<Circle> circles;
}
