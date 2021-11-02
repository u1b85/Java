import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Task_JDBC {
	Connection con=null;
	Statement stmt=null;
	Scanner scn=new Scanner(System.in);
	public Task_JDBC() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 완료..!");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##scott","tiger");
			System.out.println("DB접속 완료..!");
			stmt=con.createStatement();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void disconnect() throws Exception{
		if(con!=null) con.close();
		if(stmt!=null) stmt.close();
		scn.close();
		System.out.println("close 완료..!");
	}
	public void insert() throws Exception{
		System.out.println("[회원가입]");
		System.out.print("아이디 입력 : ");
		String id=scn.next();
		if(!idSearch(id)) {
			System.out.print("비밀번호 입력 : ");
			String pwd=scn.next();
			System.out.print("이메일 입력 : ");
			String email=scn.next();
			System.out.print("전화번호 입력 : ");
			String phone=scn.next();
			String sql="insert into mems values('"+id+"','"+pwd+
					"','"+email+"','"+phone+"',sysdate)";
			stmt.executeUpdate(sql);
			System.out.println("회원가입 완료..!");
		}else {
			System.out.println(id+"는 이미 사용 중인 아이디입니다.");
		}
	}
	public void select() throws Exception{
		System.out.println("[회원조회]");
		System.out.print("조회할 회원의 아이디 입력 : ");
		String id=scn.next();
		if(idSearch(id)) {
			ResultSet rs=stmt.executeQuery("select * from mems where id='"+id+"'");
			rs.next();
			System.out.println("아이디:"+id+", 비밀번호:"+rs.getString("pwd")+
					", 이메일:"+rs.getString("email")+", 전화번호:"+rs.getString("phone")+
					", 가입일:"+rs.getDate("regdate"));
			rs.close();
		}else {
			System.out.println(id+"는 존재하지 않는 회원입니다.");
		}
	}
	public void delete() throws Exception{
		System.out.println("[회원삭제]");
		System.out.print("삭제할 회원의 아이디 입력 : ");
		String id=scn.next();
		if(idSearch(id)) {
			stmt.executeUpdate("delete from mems where id='"+id+"'");
			System.out.println("삭제 완료..!");
		}else {
			System.out.println(id+"는 존재하지 않는 회원입니다.");
		}
	}
	public void update() throws Exception{
		System.out.println("[회원정보 수정]");
		System.out.print("수정할 회원의 아이디 입력 : ");
		String id=scn.next();
		if(idSearch(id)) {
			System.out.print("비밀번호 입력 : ");
			String pwd=scn.next();
			System.out.print("이메일 입력 : ");
			String email=scn.next();
			System.out.print("전화번호 입력 : ");
			String phone=scn.next();
			stmt.executeUpdate("update mems set pwd='"+pwd+"',email='"+
					email+"',phone='"+phone+"'where id='"+id+"'");
			System.out.println("수정 완료..!");
		}else {
			System.out.println(id+"는 존재하지 않는 회원입니다.");
		}
	}
	public void allSelect() throws Exception{
		ResultSet rs=stmt.executeQuery("select * from mems");
		while(rs.next()) {
			System.out.println("아이디:"+rs.getString("id")+", 비밀번호:"+rs.getString("pwd")+
					", 이메일:"+rs.getString("email")+", 전화번호:"+rs.getString("phone")+
					", 가입일:"+rs.getDate("regdate"));
		}
		rs.close();
	}
	public boolean idSearch(String id) throws Exception{
		ResultSet rs=stmt.executeQuery("select * from mems where id='"+id+"'");
		if(rs.next()) {
			rs.close();
			return true;
		}else {
			rs.close();
			return false;
		}
	}
}
