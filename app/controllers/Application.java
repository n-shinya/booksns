package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Book;
import models.BookMemo;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import dto.ResultDto;

@With(Secure.class)
public class Application extends Controller {
	
	@Before
	static void setConnectedUser() {
		if(Security.isConnected()) {
			renderArgs.put("user", Security.connected());
		}
	}
	
	// TODO ちゃんとやる
	public static void index() {
		List<BookMemo> unreads 
			= BookMemo.find("select b from BookMemo b where b.status=?", "1").fetch();
		List<BookMemo> readings
			= BookMemo.find("select b from BookMemo b where b.status=?", "2").fetch();
		List<BookMemo> reads 
			= BookMemo.find("select b from BookMemo b where b.status=?", "3").fetch();
		List<BookMemo> wants 
			= BookMemo.find("select b from BookMemo b where b.status=?", "4").fetch();
		List<BookMemo> allbooks = new ArrayList<BookMemo>();
		allbooks.addAll(unreads);
		allbooks.addAll(readings);
		allbooks.addAll(reads);
		allbooks.addAll(wants);
		render(unreads, readings, reads, wants, allbooks);
	}

	public static void search() {
		render();
	}

	public static void results(Integer pageNumber, String keywords) {

		if(pageNumber == null) {
			pageNumber = 1;
		}
		ResultDto result = new Book().itemSearchByKeywords(keywords, pageNumber);
		render(keywords, result, pageNumber);
	}
	
	public static void register(String isbn) {
		Book book = new Book().itemLookupByISBN(isbn);
		render(book);
	}
	
	public static void save(String isbn, String comment, String status) {
		Book book = new Book().itemLookupByISBN(isbn);
		book.save();
		BookMemo bookMemo = new BookMemo();
		bookMemo.book = book;
		bookMemo.status = status;
		bookMemo.comment = comment;
		bookMemo.user = User.find("byName", Security.connected()).first();
		bookMemo.save();
		index();
	}
}