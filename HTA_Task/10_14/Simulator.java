import java.util.Random;

class Cash{
	private long[] balance=new long[3];
	private int month;
	
	public Cash(long balance, int month) {
		for(int i=0;i<3;i++) {
			this.balance[i]=balance;
		}
		this.month=month;
	}
	public long getBalance(int i) {
		return balance[i];
	}
	public void setBalance(int i, long cash) {
		balance[i]=cash;
	}
}

class Financing implements Runnable{
	private long[] fund=new long[3];
	private Cash c;
	
	public Financing(Cash c, long fund0, long fund1, long fund2) {
		this.c=c;
		this.fund[0]=fund0;
		this.fund[1]=fund1;
		this.fund[2]=fund2;
	}
	public void run() {
		while(true) {
			synchronized(c) {
				for(int i=0;i<3;i++) {
					long cash=c.getBalance(i)+fund[i];
					c.setBalance(i, cash);
					System.out.println();
				}
				try {
					Thread.sleep(2000);
				}catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
			}
		}
	}
}

class Investment implements Runnable{
	private String[] goods=new String[3];
	private Cash c;
	
	public Investment(Cash c, String goods0, String goods1, String goods2) {
		this.c=c;
		this.goods[0]=goods0;
		this.goods[1]=goods1;
		this.goods[2]=goods2;
	}
	public double rate(String goods) {
		Random r=new Random();
		int n=r.nextInt(1000)-500;
		if(goods.equals("은행")) { //수익률 0.5%
			return 0.5;
		}else if(goods.equals("주식")) { //수익률 -5~5%
			return (double)n/100;
		}else if(goods.equals("코인")) { //수익률 -50~50%
			return (double)n/10;
		}else return 0;
	}
	public void run() {
		while(true) {
			synchronized(c) {
				for(int i=0;i<3;i++) {
					double rate=rate(goods[i]);
					long cash=(long)(c.getBalance(i)+(c.getBalance(i)/rate));
					c.setBalance(i, cash);
					System.out.println(goods[i]+"투자 수익률 : "+rate+", 보유금액 : "+c.getBalance(i));
				}
				try {
					Thread.sleep(2000);
				}catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
			}
		}
	}
}
public class Simulator {
	public static void main(String[] args) {
		Cash c=new Cash(1000000,12);
		Thread th1=new Thread(new Financing(c,0,0,0));
		Thread th2=new Thread(new Investment(c,"은행","주식","코인"));
		th1.start();
		th2.start();
	}
}
