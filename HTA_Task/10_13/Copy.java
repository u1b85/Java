import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Copy {
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		while(true){
			System.out.println("[1.파일복사 2.종료]");
			int n=scn.nextInt();
			if(n==2) break;
			System.out.print("복사하는 폴더 및 파일의 경로 : ");
			String path=scn.next();
			search(path);
		}
		scn.close();
		System.out.println("[종료합니다]");
	}
  
	public static void search(String path) {
		File f=new File(path);
		if(f.exists()) {
			Scanner scn=new Scanner(System.in);
			System.out.print("붙여넣기할 폴더의 경로 : ");
			String writePath=scn.next();
			copyFile(f, writePath);
			System.out.println("[복사 완료]");
		}else {
			System.out.println("[해당 폴더 및 파일은 존재하지 않습니다]");
		}
	}
  
	public static void copyFile(File f, String writePath) {
		if(f.isFile()) {
			FileInputStream fis=null;
			FileOutputStream fos=null;
			try {
				fis=new FileInputStream(f);
				fos=new FileOutputStream(writePath+"\\"+f.getName());
				byte[] b=new byte[100];
				int n=0;
				while((n=fis.read(b))!=-1) {
					fos.write(b, 0, n);
				}
				fis.close();
				fos.close();
			}catch(IOException ie) {
				System.out.println(ie.getMessage());
			}
		}else {
			File ff=new File(writePath+"\\"+f.getName());
			ff.mkdir();
			File[] list=f.listFiles();
			for(File fff:list) {
				copyFile(fff, writePath+"\\"+f.getName());
			}
		}
	}
}
