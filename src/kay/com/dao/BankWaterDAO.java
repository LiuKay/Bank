package kay.com.dao;

import kay.com.dto.Pager;
import kay.com.entity.BankWater;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BankWaterDAO {
	
	/**
	 * 查询所有流水
	 * @param accountId 账户ID
	 * @return 流水单
	 */
	List<BankWater> queryBankWaters(String accountId);

    /**
     * 查询分页流水数据
     * @param accountId
     * @param pageNum  第几页
     * @param pageSize  每页大小
     * @return
     */
	Pager<BankWater> querySubBankWaters(String accountId,int pageNum,int pageSize);

	
	/**
	 * 插入流水记录
	 * @param bankWater
	 * @param connection  由于金额变动与流水记录应该属于同一个事物，这里将connection传入
	 * 						异常留给调用放处理
	 * @return
	 */
	int insertBankWater(BankWater bankWater, Connection connection) throws SQLException;
}
