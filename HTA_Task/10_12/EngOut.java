import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EngOut {
	public static void main(String[] args) {
		try {
			BufferedReader br=null;
			Scanner scn=new Scanner(System.in);
			while(true) {
				System.out.print("영어단어 [0입력시 종료] : ");
				String in=scn.next();
				if(in.equals("0")) break;
				br=new BufferedReader(new FileReader("eng.txt"));
				while(true) {
					String search=br.readLine();
					if(search==null) {
						System.out.println("[해당 단어가 파일 내에 존재하지 않습니다]");
						break;
					}
					String[] s=search.split("/");
					if(s[0].equals(in)) {
						System.out.println(in+"의 뜻 : "+s[1]);
						break;
					}
				}
			}
			scn.close();
			br.close();
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}
