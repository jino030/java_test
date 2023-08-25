package co.yedam.variable;

public class VariableExe3 {
	
	int num = 100;
	
	// 메소드 선언
	public void showInfo() {
		int num = 100 + this.num;
		System.out.println("showInfo() num: " + num);
		int num1 = 200;	// 로컬변수는 선언된 실행블럭안에서만 유효값.
	}
	
	public void printNum() {
		System.out.println("printNum() num: " + "num1 showInfo()의 로컬변수는 사용할 수 없습니다!");
	}
	
	public void helloMsg() {
		int num = 200 + this.num;
		System.out.println("helloMsg() num: " + num);
	}
}
