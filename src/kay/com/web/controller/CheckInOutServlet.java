package kay.com.web.controller;

import kay.com.dto.CheckExecution;
import kay.com.entity.Account;
import kay.com.enums.CheckResultEnum;
import kay.com.enums.CheckTypeEnum;
import kay.com.exception.CheckInOutException;
import kay.com.exception.IllegalAccountStateException;
import kay.com.exception.LessMoneyException;
import kay.com.service.CheckService;
import kay.com.service.impl.CheckServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Servlet implementation class CheckInOutServlet
 */
@WebServlet("/checkInOut")
public class CheckInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckInOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkType=request.getParameter("checkType");    //操作类型
		String checkMoney=request.getParameter("checkMoney");  //变动额
		String outAccount=request.getParameter("outAccount");  //转出账户
		Account mainAccount=(Account)request.getSession().getAttribute("accountInfo");
		String mainAccountId=mainAccount.getAccountId();     //主账户
		//记录传入参数
		System.out.println("checkType:"+checkType +"  checkMoney:"+checkMoney +" outAccount:"+outAccount);

		////实例service对象
		CheckService checkService=new CheckServiceImpl();
        CheckExecution result=null;
         //执行结果

		try {
			//判断业务类型,调用service对象
			if(checkType!=null && "CHECK_IN".equals(checkType)){
                result=checkService.checkInOut(mainAccountId, CheckTypeEnum.CHECK_IN, new BigDecimal(checkMoney));
			}
			if(checkType!=null && "CHECK_OUT".equals(checkType)){
                result=checkService.checkInOut(mainAccountId, CheckTypeEnum.CHECK_OUT, new BigDecimal(checkMoney));
			}
			if(checkType!=null && "TRANSFER_CHECK_OUT".equals(checkType)){
                result=checkService.transferCheckInOut(mainAccountId,outAccount,new BigDecimal(checkMoney));
			}
		}catch (IllegalAccountStateException e2){
            result=new CheckExecution(mainAccount,CheckResultEnum.ACCOUNT_ILLEGAL);
		}catch (LessMoneyException e3){
            result=new CheckExecution(mainAccount,CheckResultEnum.LESS_MONEY);
		}catch (CheckInOutException e){
            result=new CheckExecution(mainAccount,CheckResultEnum.INNER_ERROR);
		}

        JSONObject jsonObject=JSONObject.fromObject(result);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        System.out.println(jsonObject.toString());
        response.getWriter().write(jsonObject.toString());
		
	}

}
