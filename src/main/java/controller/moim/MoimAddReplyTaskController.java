package controller.moim;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Reply;
import data.User;

@WebServlet("/moim/add-reply-task")
public class MoimAddReplyTaskController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 요청 >>> 인코딩
		req.setCharacterEncoding("utf-8");

		String ment = req.getParameter("ment");

		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession session = factory.openSession();

		Map<String, Object> parameter = new HashMap<>();

		User user = (User) req.getSession().getAttribute("logonUser");
		parameter.put("writer", user.getId());
		parameter.put("ment", ment);

		String moimId = req.getParameter("moimId");
		parameter.put("moimId", moimId);

		int r = session.insert("replys.create", parameter);
		if (r == 1) {
			session.commit();
		}
		session.close();
		
		resp.sendRedirect("/moim/detail?id=" + moimId);

//		Reply r = new Reply();
//		r.setWriter(user.getId());
//		r.setMent(ment);
//		r.setMoimId(moimId);
//		session.insert("replys.creatByVO", r);

	}

}
