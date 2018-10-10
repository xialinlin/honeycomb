package com.hs.support.mybatis.tenant;

import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Integer;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

@Intercepts({@Signature(
		type= StatementHandler.class,
		method = "prepare",
		args = {Connection.class ,Integer.class })})
public class TenantPlugin implements Interceptor{


	private SQLFilter sQLFilter;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {




		if(!sQLFilter.isFilter() || sQLFilter.isSuperAdmin())
			return invocation.proceed();

		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分离出最原始的的目标类)    
		while (metaStatementHandler.hasGetter("h")) {  
			Object object = metaStatementHandler.getValue("h");  
			metaStatementHandler = SystemMetaObject.forObject(object);  
		}  

		//BoundSql对象是处理sql语句的。  
		String sql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");  
		//判断sql是否select语句，如果不是select语句那么就出错了。  
		if (sql != null && sql.toLowerCase().trim().indexOf("select") == 0 && !sql.contains("$_$tenant_id=$")) {  
			//获取重新拼装后的SQL
			sql = addTenantId(sql);
			metaStatementHandler.setValue("delegate.boundSql.sql", sql); //重写SQL  
		}  
		return invocation.proceed();//实际就是调用原来的prepared方法，只是再次之前我们修改了sql  
	}


	/**
	 * SQL自动添加tenantId
	 * @param sql
	 * @return
	 */
	protected String addTenantId(String sql) {



		//		String ss = sql.toLowerCase();
		//		Pattern pt=Pattern.compile("(from\\s+\\S+)");
		//		Matcher mt=pt.matcher(ss);
		//		mt.lookingAt();
		//		// mt.matches();
		//		while(mt.find()){
		//			System.out.println(mt.group(1)+"|||"+mt.end());
		//		}
		//		
		//		
		//		return sql;

		String sqlLowerCase = sql.toLowerCase();
		StringBuilder sb=new StringBuilder(sql);
		int index = sqlLowerCase.lastIndexOf("where");//where的位置

		Pattern pt = null;
		Matcher mt = null;


		if(index>0) {//where
			sb.insert(index+5, " "+sQLFilter.getSql()+" AND ");
		}else if(sqlLowerCase.lastIndexOf(") table_count") > 0) {
			sb.insert(sqlLowerCase.length() - 13, " WHERE  "+sQLFilter.getSql());
		}else if(  sqlLowerCase.lastIndexOf("order ") > 0  ) {//判断排序
			sb.insert(sqlLowerCase.indexOf("order "), " WHERE  "+sQLFilter.getSql()+" ");
		} else if(sqlLowerCase.indexOf("limit") > 0) {
			sb.insert(sqlLowerCase.indexOf("limit"), " WHERE  "+sQLFilter.getSql()+" ");
		}else if(sqlLowerCase.indexOf("join") > 0) {
			sb.append(" WHERE  "+sQLFilter.getSql()+" ");
		}else{
			pt=Pattern.compile("(from\\s+\\S+)");
			mt=pt.matcher(sqlLowerCase);
			mt.lookingAt();
			while(mt.find())
				sb.insert(mt.end(), " WHERE  "+sQLFilter.getSql());
		}
		return sb.toString();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}


	public void setTableFilter(SQLFilter sQLFilter) {
		this.sQLFilter = sQLFilter;
	}

}
