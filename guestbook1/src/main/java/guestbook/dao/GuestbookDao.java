package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import guestbook.vo.GuestbookVo;

public class GuestbookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.64.3:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

	public int insert(GuestbookVo vo) {
		int result = 0;

		try (Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement(
						"insert into guestbook(name, password, contents, reg_date) values (?, ?, ?, now())");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");) {
			pstmt1.setString(1, vo.getName());
			pstmt1.setString(2, vo.getPassword());
			pstmt1.setString(3, vo.getContents());
			result = pstmt1.executeUpdate();

			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1) : null);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return result;
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select no, name, contents, date_format(reg_date, '%Y-%m-%d %h:%i:%s') 'reg_date' from guestbook order by no desc");
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return result;
	}

	public int deleteByNo(GuestbookVo vo) {
		int result = 0;

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("delete from guestbook where no = ? and password = ?");) {
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return result;
	}
}
