package controller.moim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Moim;
import repository.Moims;

@WebServlet("/moim/search")
public class MoimSearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] cates = req.getParameterValues("cate");
		// System.out.println(Arrays.toString(cates));

		String page = req.getParameter("page");
		int p;
		if (req.getParameter("page") == null) {
			p = 1;
		} else {
			p = Integer.parseInt(req.getParameter("page"));
		}

		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");

		SqlSession sqlSession = factory.openSession();

		Map<String, Object> map = new HashMap<>();
		map.put("a", (p - 1) * 6 + 1);
		map.put("b", 6 * p);
		List<Moim> list = sqlSession.selectList("findSomeByAtoB", map);

		sqlSession.close();

//		List<Moim> list =Moims.findByCate(cates);
		req.setAttribute("list", list);

		req.getRequestDispatcher("/WEB-INF/views/moim/search.jsp").forward(req, resp);
	}
}
