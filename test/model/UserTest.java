package model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserTest extends UnitTest {
	
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
		List<User> users = User.findAll();
		assertEquals(users.size(), 2);
		assertEquals(users.get(0).circles.size(), 2);
		assertEquals(users.get(0).name, "n-shinya");
		assertEquals(users.get(0).circles.get(0).name, "flect");
		assertEquals(users.get(0).circles.get(1).name, "friends");
	}
}
