
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Task_JDBC {
	Connection con=null;
	Scanner scn=new Scanner(System.in);
	public Task_JDBC() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##scott","tiger");
			System.out.println("DB접속 완료..!");
			con.setAutoCommit(false);
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public void insert() {
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		System.out.print("구매자 아이디 입력 : ");
		String id=scn.next();
		System.out.print("상품명 입력 : ");
		String goods=scn.next();
		System.out.print("가격 입력 : ");
		int price=scn.nextInt();
		System.out.print("수량 입력 : ");
		int qt=scn.nextInt();
		System.out.print("결제수단 입력 : ");
		String method=scn.next();
		String sql1="insert into buy values(buy_seq.nextval,?,?,?,?,sysdate)";
		String sql2="DECLARE"
				+ "	SNUM NUMBER(3);"
				+ "BEGIN"
				+ "	SNUM:=BUY_SEQ.CURRVAL;"
				+ "	UPDATE PAY SET METHOD=? WHERE PNUM=SNUM;"
				+ "END;";
		try {
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setString(1, id);
			pstmt1.setString(2, goods);
			pstmt1.setInt(3, price);
			pstmt1.setInt(4, qt);
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setString(1, method);
			pstmt1.executeUpdate();
			int n=pstmt2.executeUpdate();
			System.out.println(n+"건의 제품구매 완료..!");
		}catch(SQLException se) {
			System.out.println("insert 오류 발생[rollback 실행]: ");
			se.printStackTrace();
			try {
				con.rollback();
			}catch(SQLException se2) {
				System.out.println("rollback 오류 발생 : ");
				se2.printStackTrace();
			}
		}finally {
			try {
				if(pstmt1!=null) pstmt1.close();
				if(pstmt2!=null) pstmt2.close();
			}catch(SQLException se) {
				System.out.println("close 오류 발생 : ");
				se.printStackTrace();
			}
		}
	}
	public void update() {
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		System.out.print("수정하는 구매번호 입력 : ");
		int num=scn.nextInt();
		System.out.print("새로 입력할 상품명 : ");
		String goods=scn.next();
		System.out.print("새로 입력할 가격 : ");
		int price=scn.nextInt();
		System.out.print("새로 입력할 수량 : ");
		int qt=scn.nextInt();
		System.out.print("새로 입력할 결제수단 : ");
		String method=scn.next();
		String sql1="update buy set goods=?,price=?,qt=? where num=?";
		String sql2="update pay set method=? where num=?";
		try {
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setString(1, goods);
			pstmt1.setInt(2, price);
			pstmt1.setInt(3, qt);
			pstmt1.setInt(4, num);
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setString(1, method);
			pstmt2.setInt(2, num);
			pstmt1.executeUpdate();
			int n=pstmt2.executeUpdate();
			System.out.println(n+"건의 구매정보 수정 완료..!");
		}catch(SQLException se) {
			System.out.println("update 오류 발생[rollback 실행]: ");
			se.printStackTrace();
			try {
				con.rollback();
			}catch(SQLException se2) {
				System.out.println("rollback 오류 발생 : ");
				se2.printStackTrace();
			}
		}finally {
			try {
				if(pstmt1!=null) pstmt1.close();
				if(pstmt2!=null) pstmt2.close();
			}catch(SQLException se) {
				System.out.println("close 오류 발생 : ");
				se.printStackTrace();
			}
		}
	}
	public void select() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from buy b, pay p where b.num=p.num order by b.num";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					System.out.println("구매 및 결제번호:"+rs.getInt("num")+", 아이디:"+
				rs.getString("id")+", 상품명:"+rs.getString("goods")+", 가격:"+rs.getInt("price")+
				", 수량:"+rs.getInt("qt")+", 결제금액:"+rs.getInt("amount")+", 결제수단:"+
				rs.getString("method")+", 구매일:"+rs.getDate("bdate"));
				}while(rs.next());
			}else System.out.println("구매정보가 존재하지 않습니다.");
		}catch(SQLException se) {
			System.out.println("select 오류 발생[rollback 실행]: ");
			se.printStackTrace();
			try {
				con.rollback();
			}catch(SQLException se2) {
				System.out.println("rollback 오류 발생 : ");
				se2.printStackTrace();
			}
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			}catch(SQLException se) {
				System.out.println("close 오류 발생 : ");
				se.printStackTrace();
			}
		}
	}
	public void delete() {
		PreparedStatement pstmt=null;
		System.out.print("취소하는 구매번호 입력 : ");
		int num=scn.nextInt();
		String sql="delete from buy where num=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int n=pstmt.executeUpdate();
			System.out.println(n+"건의 구매취소 완료..!");
		}catch(SQLException se) {
			System.out.println("delete 오류 발생[rollback 실행]: ");
			se.printStackTrace();
			try {
				con.rollback();
			}catch(SQLException se2) {
				System.out.println("rollback 오류 발생 : ");
				se2.printStackTrace();
			}
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			}catch(SQLException se) {
				System.out.println("close 오류 발생 : ");
				se.printStackTrace();
			}
		}
	}
	public void disconnect() {
		try {
			if(con!=null) con.close();
			System.out.println("disconnect 완료..!");
		}catch(SQLException se) {
			System.out.println("disconnect 오류 발생 : ");
			se.printStackTrace();
		}
	}
}
