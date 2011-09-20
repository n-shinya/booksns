package model;

import java.util.List;

import models.Status;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class StatusTest extends UnitTest {

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
		List<Status> statuses = Status.findAll();
		assertEquals(statuses.size(), 3);
		Status status = Status.find("byStatus", "2").first();
		assertNotNull(status);
		assertNotNull(status.user);
		assertNotNull(status.book);
		assertEquals(status.user.name, "n-shinya");
		assertEquals(status.book.title, "Effective Java 第2版 (The Java Series)");
	}
}
