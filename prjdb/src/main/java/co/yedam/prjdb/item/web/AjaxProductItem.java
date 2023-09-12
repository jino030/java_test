package co.yedam.prjdb.item.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.prjdb.item.service.ItemService;
import co.yedam.prjdb.item.service.ItemVO;
import co.yedam.prjdb.item.serviceImpl.ItemServiceImpl;

@WebServlet("/AjaxProductItem.do")
public class AjaxProductItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxProductItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemService dao = new ItemServiceImpl();
		List<ItemVO> items = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		items = dao.itemTopSelectList();
		
		if(items != null) {
			String json = objectMapper.writeValueAsString(items);
			response.setContentType("text/json; charset=UTF-8");
			response.getWriter().print(json);
		} else {
			System.out.println("AjaxProductItem.do 오류발생");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
