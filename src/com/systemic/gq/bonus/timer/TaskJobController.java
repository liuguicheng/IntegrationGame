package com.systemic.gq.bonus.timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.systemic.gq.bonus.settlement.SettlementHelper;



public class TaskJobController  {
	
	
    public void aTask(){
    	try{
        
         DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
         System.out.println(sdf.format(new Date())+"*********每小时检测申请审核时间限制限制");   
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }  
}
