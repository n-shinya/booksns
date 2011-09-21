package helper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.XPath;
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
		params.put("ItemPage", "1");
		SignedRequestsHelper srh = new SignedRequestsHelper();
		String url = srh.sign(params);
		System.out.println(url);
		HttpResponse res = WS.url(new SignedRequestsHelper().sign(params)).get();
		Document doc = res.getXml();

		for(Node node : XPath.selectNodes("/ItemSearchResponse/Items/Item", doc)) {
			System.out.println(XPath.selectNode("ItemAttributes/Title", node).getTextContent());
			System.out.println(XPath.selectNode("ItemAttributes/Author", node).getTextContent());
			System.out.println(XPath.selectNode("ItemAttributes/Manufacturer", node).getTextContent());
			System.out.println(XPath.selectNode("MediumImage/URL", node).getTextContent());
		}
		// とりあえず
		assertNotNull(url);
	}
}
