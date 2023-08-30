package co.yedam.band.board.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoardVO {
	private int boardNumber;
	private String boardTitle;
	private String boardContent;
	private String memberId;
	private LocalDate boardWriteDate;
	private LocalDate boardUpdateDate;
	private String clubName;
	private int boardLike;
	private int boardHate;
	
	public void boardPrint() {
		System.out.println("  " + boardNumber + ". 제목(" + boardTitle + ") / 작성자(" + memberId + ")");
	}
}
