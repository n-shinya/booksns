package models;

import helper.SignedRequestsHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import play.db.jpa.Model;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.XPath;
import dto.ResultDto;

/**
 * 本のモデルです。
 * @author nishinaka_s
 */
@Entity
public class Book extends Model {

	/**
	 * タイトルです。
	 */
	public String title;
	
	/**
	 * 作者です。
	 */
	public String author;
	
	/**
	 * 出版社です。
	 */
	public String publisher;
	
	/**
	 * 電子書籍かどうかを表します。
	 */
	public boolean ebook;
	
	/**
	 * 画像URLです。
	 */
	public String imageUrl;
	
	/**
	 * ISBNです。
	 */
	public String isbn;
	
	/**
	 * 関連するユーザです。
	 */
	@ManyToMany
	public List<User> users;
	
	/**
	 * 関連する本のメモです。
	 */
	@OneToMany
	public List<BookMemo> bookMemos;
	
	/**
	 * キーワードで本を検索します。
	 * 
	 * @param keywords
	 * @param pageNumber
	 * @return
	 */
	public ResultDto itemSearchByKeywords(String keywords, Integer pageNumber) {
		
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
			book.isbn = XPath.selectText("ItemAttributes/ISBN", node);
			books.add(book);
		}
		
		Integer totalPage = Integer.parseInt(XPath.selectText("/ItemSearchResponse/Items/TotalPages", doc));
		Integer totalResult = Integer.parseInt(XPath.selectText("/ItemSearchResponse/Items/TotalResults", doc));
		
		ResultDto dto = new ResultDto();
		dto.books = books;
		dto.totalPage = totalPage;
		dto.totalResult = totalResult;
		return dto;
	}
	
	
    /**
     * ISBNで本を検索します。
     * 
     * @param isbn
     * @return
     */
    public Book itemLookupByISBN(String isbn) {

    	Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Operation", "ItemLookup");
		params.put("ItemId", isbn);
		params.put("SearchIndex", "Books");
		params.put("IdType", "ISBN");
		params.put("ResponseGroup", "Medium");
	
		HttpResponse res = WS.url(new SignedRequestsHelper().sign(params)).get();
		Document doc = res.getXml();
		
		Node node = XPath.selectNode("/ItemLookupResponse/Items/Item", doc);
		Book book = new Book();
		book.title = XPath.selectText("ItemAttributes/Title", node);
		book.author = XPath.selectText("ItemAttributes/Author", node);
		book.publisher = XPath.selectText("ItemAttributes/Manufacturer", node);
		book.imageUrl = XPath.selectText("MediumImage/URL", node);
		book.isbn = XPath.selectText("ItemAttributes/ISBN", node);

		return book;
}

}
