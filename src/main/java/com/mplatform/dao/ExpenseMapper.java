package com.mplatform.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mplatform.domain.ExpenseInfo;

@Mapper
public interface ExpenseMapper {

	@Insert("INSERT INTO mp_expenseLog(expenseName, expenseUser, expenseManager, expenseValue, expenseTime, expenseTips) values(#{expenseName},#{expenseUser},#{expenseManager},#{expenseValue},#{expenseTime},#{expenseTips})")
	int insertExpense(@Param("expenseName") String expenseName, @Param("expenseUser") Integer expenseUser,
			@Param("expenseManager") Integer expenseManager, @Param("expenseValue") Double expenseValue,
			@Param("expenseTime") Timestamp expenseTime, @Param("expenseTips") String expenseTips);

	@Select("SELECT m.* ,n.trueName AS userName,p.trueName AS managerName FROM (mp_expenseLog m LEFT JOIN mp_user n ON m.expenseUser = n.userId )LEFT JOIN mp_user p ON m.expenseManager = p.userId LIMIT #{start},#{limit}")
	List<ExpenseInfo> selectExpense(@Param("start") int start, @Param("limit") Integer limit);

	@Select("SELECT COUNT(*) FROM mp_expenseLog")
	Integer expenseCount();
	
	@Update("UPDATE mp_expenseLog SET expenseStatus=1 WHERE expenseId=#{id}")
	int confirmExpense(@Param("id") Integer id);

	@Update("UPDATE mp_expenseLog SET expenseStatus=2 WHERE expenseId=#{id}")
	int rejectExpense(@Param("id") Integer id);
}
