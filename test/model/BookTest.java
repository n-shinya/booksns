package model;

import models.Book;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BookTest extends UnitTest{

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("data.yml");
	}
	
	/**
	 * 単純な検索をテストします。
	 */
	@Test
	public void simpleFind() {
		Book book = Book.find("byTitle", "たのしいRuby第3版").first();
		assertNotNull(book);
		assertEquals(book.author, "高橋 征義");
		assertNotNull(book.users);
		assertEquals(book.users.get(0).name, "n-shinya");
	}

}
