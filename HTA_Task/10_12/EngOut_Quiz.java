import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EngOut_Quiz {
	public static void main(String[] args) {
		int tot=0;
		int cor=0;
		try {
			BufferedReader br=new BufferedReader(new FileReader("eng.txt"));
			Scanner scn=new Scanner(System.in);
			while(true) {
				String Line=br.readLine();
				if(Line==null) break;
				tot++;
				String[] s=Line.split("/");
				System.out.print(s[0]+" : ");
				String in=scn.next();
				if(in.equals(s[1])) {
					cor++;
					System.out.println("[정답]");
				}else {
					System.out.println("[오답] (정답:"+s[1]+")");
				}
			}
			br.close();
			scn.close();
			System.out.println("[전체 문제 : "+tot+"개, 맞춘 문제 : "+cor+"개]");
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}
