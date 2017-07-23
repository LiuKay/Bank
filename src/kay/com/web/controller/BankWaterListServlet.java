package kay.com.web.controller;

import kay.com.dto.Pager;
import kay.com.entity.Account;
import kay.com.entity.BankWater;
import kay.com.service.AccountService;
import kay.com.service.impl.AccountServiceImpl;
import kay.com.utils.JsonBigDecimalProcessor;
import kay.com.utils.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kay on 2017/7/22.
 * 获取流水记录
 */
@WebServlet("/bankWaters")
public class BankWaterListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     *根据账户查询该账户的流水记录
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageNumStr=request.getParameter("pageNum");
        int pageNum=1;
        if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
            pageNum = Integer.parseInt(pageNumStr);
        }
        int pageSize=5;   //每页默认大小

        Account account= (Account) request.getSession().getAttribute("accountInfo");
        String accountId=account.getAccountId();

        //实例AccountService 查询流水
        AccountService accountService=new AccountServiceImpl();
        //查询所有记录
        //List<BankWater> list=accountService.queryBankWaters(accountId);

        //查询分页记录
        Pager<BankWater> bankWaters=accountService.queryBankWaters(accountId,pageNum,pageSize);

        //todo 注册Date转换器+BigDecimal，转换json格式需要

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(java.math.BigDecimal.class, new JsonBigDecimalProcessor());
        JSONArray jsonArray=new JSONArray();

        jsonArray=JSONArray.fromObject(bankWaters,jsonConfig);
        //System.out.println(jsonArray.toString());

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonArray.toString());


    }
}
