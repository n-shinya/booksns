package model;

import models.Circle;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

/**
 * サークルのテストです。
 * @author nishinaka_s
 */
public class CircleTest extends UnitTest {
	
	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}

	/**
	 * 単純な検索テストします。
	 */
	@Test
	public void simpleFind() {
	
		Fixtures.loadModels("data.yml");
		// 検索
		Circle found = Circle.find("byName", "flect").first();
		
		assertNotNull(found);
		assertEquals(found.name, "flect");
		assertEquals(found.secretKey, "secret");
	}
}
