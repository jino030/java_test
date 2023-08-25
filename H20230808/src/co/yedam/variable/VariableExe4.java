package co.yedam.variable;

import java.io.IOException;

public class VariableExe4 {
	public static void main(String[] args) {
		// 이름은 홍길동, 나이는 20살 입니다.
		String name = "홍길동";
		int age = 20;
		double weigth = 67.8;
		
		System.out.println("이름은 " + name + ", 나이는 " + age + "살 입니다.");
		System.out.printf("이름은 %s,\t나이는 %d살,\n몸무게는 %.1f입니다.\n", name, age, weigth);
		// %s : 1번째 매개변수(스트링)를 받아오겠습니다.
		// %d : 2번째 매개변수(숫자,digit)를 받아오겠습니다.
		// %.1f : 3번째 매개변수(실수)를 소수점 한자리까지 받아오겠습니다.
		
		
		try {
			
			System.out.println("값을 입력 >>>");
			while(true) {
				int result = System.in.read();

				if(result == 113) {
					// q입력시 반복문 종료
					break;
				} else if (result == 10 || result == 13) {
					// ENTER(cr:13, lf:10)입력 시 담기만하고 출력은 안한다
				} else {
					System.out.println("키코드 = " + result);
//					System.out.println("값을 입력 >>>");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end of prog.");
		
		
	}	//end of main.
}// end of class.
