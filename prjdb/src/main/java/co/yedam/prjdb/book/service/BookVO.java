package co.yedam.prjdb.book.service;

import lombok.Data;

@Data
public class BookVO {
	private int bookId;
	private String bookCode;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private int bookPrice;
}
