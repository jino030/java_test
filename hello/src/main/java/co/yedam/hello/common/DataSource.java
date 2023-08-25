package co.yedam.hello.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//DAO
public class DataSource {
//	// 싱글톤 클래스 : 메모리에 인스턴스를 하나만 만드는것. (외부에서 생성하지 못하도록)
//	private static DataSource dataSource = new DataSource();
//	private DataSource() {}
//	
//	public static DataSource getInstance() { // 외부에서는 이걸 사용해서 인스턴스를 사용.
//		// 필요한 사항 정의
//		return dataSource;
//	}
	
	private static SqlSessionFactory sqlSessionFactory;
	private DataSource() {} // (외부에서 생성하지 못하도록)
	
	public static SqlSessionFactory getInstance() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
	}
	
	
	
}
