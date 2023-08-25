package co.yedam.variable;

public class VariableExe {
	public static void main(String[] args) {
		// 변수 선언
		// 정수형 변수의 유형 : byte, short(2byte), int(4byte), long(8byte)
		// byte: -128 ~ 127
		// short: -32768 ~ 32767
		// int: -2147483648 ~ 2147483647(담을 수 있는 범위를 넘어서면 다시 음수부터 시작해서 연산)
		
		int num1 = 5;	// 초기화(좌, 우 데이터의 타입이 일치해야함)
		int num2 = 2147483647;
		
		//num1 = 10;
		//num2 = 20;
		
		int result = num1 + num2;
		System.out.println("result: " + result);
		
		byte b1 = 10;
		byte b2 = 20;
		
		int b3 = b1 + b2; 
		// byte b3 = b1 + b2; (+)연산자를 사용시 int로 인식
		// Type mismatch: cannot convert from int to byte
		
		int i1 = 100;
		byte b4 = 10;
		b3 = i1 + b4; // 자동형변환해서 연산 b3 = i1 + (int)b4;
		
		// 실수타입
		// float, double
		double dbl1 = 10.23223;
		double dbl2 = 20;
		double dbl3 = dbl1 + dbl2;
		
		float f1 = 10.1f;
		float f2 = 10.001f;
		float f3 = f1 + f2;
		System.out.println("f3: " + f3); // f3: 20.101002 근사치로 계산을해서 정확한 값을 가져올 수 없다.
	}
}
