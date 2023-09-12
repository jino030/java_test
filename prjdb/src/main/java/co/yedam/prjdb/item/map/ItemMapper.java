package co.yedam.prjdb.item.map;

import java.util.List;

import co.yedam.prjdb.item.service.ItemVO;

public interface ItemMapper {
	List<ItemVO> itemSelectList();
	List<ItemVO> itemTopSelectList();
	ItemVO itemSelect(ItemVO vo);
	int itemInsert(ItemVO vo);
	int itemUpdate(ItemVO vo);
	int itemDelete(ItemVO vo);
}