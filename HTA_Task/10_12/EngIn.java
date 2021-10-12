import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EngIn {
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		try {
			PrintWriter pw=new PrintWriter("eng.txt");
			while(true) {
				System.out.println("영어단어와 뜻을 입력하세요[0입력시 종료] ex:task/일,과제");
				String s=scn.next();
				if(s.equals("0")) break;
				pw.println(s);
			}
			pw.close();
			scn.close();
			System.out.println("[eng.txt로 저장 완료]");
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}
