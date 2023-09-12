package co.yedam.prjdb.item.service;

import java.util.List;

public interface ItemService {
	List<ItemVO> itemSelectList();
	List<ItemVO> itemTopSelectList();
	ItemVO itemSelect(ItemVO vo);
	int itemInsert(ItemVO vo);
	int itemUpdate(ItemVO vo);
	int itemDelete(ItemVO vo);
}