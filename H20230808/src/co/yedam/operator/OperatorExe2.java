package co.yedam.operator;

import java.util.Scanner;

/*
 * 은행 App.
 */
public class OperatorExe2 {
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		int balance = 0;	// 10만원 이상은 불가, -금액 불가
		boolean run = true;
		
		while(run) {
			
			System.out.println("1.예금 2.출금 3.잔고 4.종료");
			System.out.print("선택 >>> ");
			
			int menu = Integer.parseInt(scn.nextLine());
			
			if(menu == 1) {
				System.out.print("입금액 입력 >>> ");
				int value = Integer.parseInt(scn.nextLine());
				
				if(balance + value > 100000 || value < 0) {
					System.out.println("입금 불가 ㄴㅇ0ㅇㄱ");
					System.out.println("현재 잔고 = " + balance);
					break;
				} else {
					balance += value;
					System.out.println("현재 잔고 = " + balance);
				}
				
			} else if(menu == 2) {
				System.out.print("출금액 입력 >>> ");
				int value = Integer.parseInt(scn.nextLine());
				
				
				if(balance < value || value < 0) {
					System.out.println("출금 불가 ㅡㅡ;;");
					break;
				} else {
					balance -= value;
					System.out.println("출금하신 금액은 " + value + "입니다.");
					System.out.println("현재 잔고 = " + balance);
				}
				
			} else if(menu == 3) {
				System.out.println("현재 잔액 >>> ");
				System.out.println("현재 잔고 = " + balance);
				
			} else if(menu == 4) {
				System.out.println("종료 >>> ");
				break;
			}
			
		}
		
		System.out.println("end of prog..");
		
	}
}
