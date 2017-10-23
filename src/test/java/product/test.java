package product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.dascom.product.entity.CpUser;
import com.dascom.product.util.TimeFilter;

public class test {
	
	@Test
	public void test11(){
		
		CpUser u=new CpUser();
		u.setUsername("测试用户"); 
		u.setPassword("ceshimima");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", "1000");
		map.put("msg", "成功");
		map.put("date", u);
		
		JSONObject json=new JSONObject(map);
		System.out.println(json.toString());
	}
	@Test
	public void sdf(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        
        //今天  
        System.out.println("今天"+sdf.format(new Date()));
        
        //昨天 
        c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-1 );
        System.out.println("昨天 "+sdf.format(c.getTime()));
        Date yesterday =c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 1);//本周第一天，以星期日开始        
        System.out.println("本周第一天"+sdf.format(c.getTime()));
        Date startWeek=c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 7);//本周最后一天
        System.out.println("本周最后一天"+sdf.format(c.getTime()));
        Date lastWeek =c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);//本月第一天
        System.out.println("本月第一天"+sdf.format(c.getTime()));
        Date startMonth =c.getTime();
        
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);//本月最后一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("本月最后一天"+sdf.format(c.getTime()));
        Date lastMonth=c.getTime();
        
        List<Date> listDate=new ArrayList<>();
        listDate.add(new Date());
        listDate.add(new Date());
        listDate.add(new Date());
        
        
        int i=TimeFilter.timeFilter(listDate, startMonth, lastMonth);
        System.out.println(i);

	}
	@Test
	public void setfs(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//今天的范围
		System.out.print("今天的范围是 :"+sdf.format(new Date())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getTomorrow()));
		//昨天的范围
		System.out.print("昨天的范围是 :"+sdf.format(TimeFilter.getYesterday())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getDay()));
		//本周的范围
		System.out.print("本周的范围是 :"+sdf.format(TimeFilter.getStartWeek())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getLasttWeek()));
		//本月的范围
		System.out.print("本月的范围是 :"+sdf.format(TimeFilter.getStartMonth())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getLastMonth()));
		
	}
	
	
}
