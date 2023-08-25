package co.yedam.hello;

import co.yedam.hello.product.menu.ProductManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        ProductService dao = new ProductServiceImpl();
//        List<ProductVO> products = new ArrayList<ProductVO>();
//        
//        products = dao.productSelectList();
//        
//        for(ProductVO v : products) {
//        	v.toString();
//        }
    	
    	ProductManager menu = new ProductManager();
    	menu.run();
        
    }
}
