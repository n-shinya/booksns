package model;

import java.util.List;

import models.BookMemo;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BookMemoTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("data.yml");
	}
	
	/**
	 * 単純検索をテストします。
	 */
	@Test
	public void simpleFind() {
		List<BookMemo> bookMemos = BookMemo.findAll();
		assertEquals(bookMemos.size(), 3);
		BookMemo bookMemo = BookMemo.find("byStatus", "2").first();
		assertNotNull(bookMemo);
		assertNotNull(bookMemo.user);
		assertNotNull(bookMemo.book);
		assertEquals(bookMemo.user.name, "n-shinya");
		assertEquals(bookMemo.book.title, "Effective Java 第2版 (The Java Series)");
	}
}
