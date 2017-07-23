package kay.com.web.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kay.com.entity.Account;
import kay.com.service.AccountService;
import kay.com.service.impl.AccountServiceImpl;
import kay.com.utils.SecurityUtil;

/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/loginCheck")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/**
	 * 接收用户密码，匹配正确存入session中跳转到主页面
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=(String) request.getParameter("username");
		String password=(String) request.getParameter("password");
		AccountService accountService=new AccountServiceImpl();
		Account account = accountService.checkPassword(username, password);
		if(account!=null){
			request.getSession().setAttribute("accountInfo", account);
			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
		}else {
			
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

}
