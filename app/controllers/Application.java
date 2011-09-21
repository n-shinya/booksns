package controllers;

import helper.SignedRequestsHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Book;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.XPath;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	render();
    }
    
    public static void search() {
    	render();
    }
    
    public static void results(Integer pageNumber, String keywords) {

    	if(pageNumber == null) {
    		pageNumber = 1;
    	}
    	
    	Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Operation", "ItemSearch");
		params.put("Keywords", keywords);
		params.put("ResponseGroup", "Medium");
		params.put("SearchIndex", "Books");
		params.put("ItemPage", pageNumber.toString());	

		HttpResponse res = WS.url(new SignedRequestsHelper().sign(params)).get();
		Document doc = res.getXml();
		List<Book> books = new ArrayList<Book>();
		for(Node node : XPath.selectNodes("/ItemSearchResponse/Items/Item", doc)) {
			Book book = new Book();
			book.title = XPath.selectText("ItemAttributes/Title", node);
			book.author = XPath.selectText("ItemAttributes/Author", node);
			book.publisher = XPath.selectText("ItemAttributes/Manufacturer", node);
			book.imageUrl = XPath.selectText("MediumImage/URL", node);
			books.add(book);
		}
		
		Integer totalPage = Integer.parseInt(XPath.selectText("/ItemSearchResponse/Items/TotalPages", doc));
		Integer totalResult = Integer.parseInt(XPath.selectText("/ItemSearchResponse/Items/TotalResults", doc));
		render(keywords, books, pageNumber, totalPage ,totalResult);
    }
    
    public static void register() {
    
    }
}