
import java.util.Scanner;

public class Task_Main {
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		Task_JDBC db=new Task_JDBC();
		boolean power=true;
		while(power) {
			System.out.println("[메뉴] 1.제품구매 2.구매정보수정 3.정보조회 4.구매취소 0.종료");
			int n=scn.nextInt();
				switch(n) {
				case 1:
					db.insert();
					break;
				case 2:
					db.update();
					break;
				case 3:
					db.select();
					break;
				case 4:
					db.delete();
					break;
				case 0:
					db.disconnect();
					scn.close();
					power=false;
					System.out.println("[프로그램을 종료합니다]");
				}
		}
	}
}
