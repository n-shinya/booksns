package model;

import java.util.List;

import models.Comment;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class CommentTest extends UnitTest {

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
		List<Comment> comments = Comment.findAll();
		assertNotNull(comments);
		assertEquals(comments.size(), 3);
		assertEquals(comments.get(0).user.name, "n-shinya");
		assertEquals(comments.get(1).user.name, "a-testuser");
	}

}
