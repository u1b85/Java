import java.util.Scanner;

public class Task_Main {
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		Task_JDBC jdbc=new Task_JDBC();
		boolean power=true;
		while(power) {
			System.out.println("[메뉴] 1.회원가입 2.조회 3.삭제 4.수정 5.전체조회 0.종료");
			int n=scn.nextInt();
			try {
				switch(n) {
				case 1:
					jdbc.insert();
					break;
				case 2:
					jdbc.select();
					break;
				case 3:
					jdbc.delete();
					break;
				case 4:
					jdbc.update();
					break;
				case 5:
					jdbc.allSelect();
					break;
				case 0:
					jdbc.disconnect();
					scn.close();
					power=false;
					System.out.println("[프로그램을 종료합니다]");
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
