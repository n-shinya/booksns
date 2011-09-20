package helper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import play.test.UnitTest;

public class SignedRequestsHelperTest extends UnitTest {
	
	@Test
	public void makeUrl() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Operation", "ItemSearch");
		params.put("Keywords", "たのしいRuby");
		params.put("ResponseGroup", "Medium");
		params.put("SearchIndex", "Books");
		SignedRequestsHelper srh = new SignedRequestsHelper();
		String url = srh.sign(params);
		// とりあえず
		assertNotNull(url);
	}
}
