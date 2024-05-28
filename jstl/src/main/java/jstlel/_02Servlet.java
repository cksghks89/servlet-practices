package jstlel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.UserVo;

public class _02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1. 객체의 Scope (존속범위) 객체의 존재 범위
		 *
		 * 2. 객체가 오래 존속하는 순서 Application(Context) Scope > Session Scope > request Scope
		 * > page Scope
		 *
		 * 3. EL이 이름으로 객체를 찾는 순서 Application(Context) Scope < Session Scope < request
		 * Scope < page Scope ![주의] 같은 이름으로 여러 범위에 객체를 저장하는 경우 주의가 필요하다.
		 */

		// application scope
		UserVo vo1 = new UserVo();
		vo1.setNo(3L);
		vo1.setName("둘리3");
		request.getServletContext().setAttribute("vo", vo1);

		// session scope
		UserVo vo2 = new UserVo();
		vo2.setNo(2L);
		vo2.setName("둘리2");
		request.getSession(true).setAttribute("vo", vo2);

		// request scope
		UserVo vo3 = new UserVo();
		vo3.setNo(1L);
		vo3.setName("둘리1");
		request.setAttribute("vo", vo3);

		request.getRequestDispatcher("/WEB-INF/views/02.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
