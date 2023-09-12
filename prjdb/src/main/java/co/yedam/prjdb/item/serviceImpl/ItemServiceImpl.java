package co.yedam.prjdb.item.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.item.map.ItemMapper;
import co.yedam.prjdb.item.service.ItemService;
import co.yedam.prjdb.item.service.ItemVO;

public class ItemServiceImpl implements ItemService {

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ItemMapper map = sqlSession.getMapper(ItemMapper.class);
	@Override
	public List<ItemVO> itemSelectList() { //전체목록조회
		return map.itemSelectList();
	}

	@Override
	public ItemVO itemSelect(ItemVO vo) { // 한건조회
		return map.itemSelect(vo);
	}

	@Override
	public int itemInsert(ItemVO vo) {
		return 0;
	}

	@Override
	public int itemUpdate(ItemVO vo) {
		return 0;
	}

	@Override
	public int itemDelete(ItemVO vo) {
		return 0;
	}

	@Override
	public List<ItemVO> itemTopSelectList() {
		return map.itemTopSelectList();
	}

}