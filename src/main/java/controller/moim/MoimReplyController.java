//package controller.moim;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import data.Reply;
//
//@WebServlet("/reply")
//public class MoimReplyController extends HttpServlet{
//	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
//
//		SqlSession session = factory.openSession();
//
//		List<Reply> result = session.selectList("reply.findByMoimId");
//
//		req.setAttribute("reply", result);
//
//		req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req, resp);
//	}
//
//}
