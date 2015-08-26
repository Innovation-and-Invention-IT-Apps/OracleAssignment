package org.bs5lndk.persistent.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.bs5lndk.persistent.beans.HaadMpatientVerifyCode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DBEventsManager {
	
	private static DBEventsManager instance = null;
 
  	private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction tx;
    private static Query query;
    
    private static String status;
	
	protected DBEventsManager() {
	}
	
	public static DBEventsManager getInstance() {
		if(instance == null) {
				instance = new DBEventsManager();
				sessionFactory = new Configuration().configure().buildSessionFactory();
        		session = sessionFactory.openSession();
        		tx = session.beginTransaction();
		}
		return instance;
	}
	
	public static String makeNewEvent(HaadMpatientVerifyCode hmvc){
		try{
    		tx = session.beginTransaction();
	        session.save(hmvc);
	        tx.commit();
	        status = "SUCCESS";
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    	status = "FAILURE";
	    }
		return status;
	}
	
	public static String updateEvent(HaadMpatientVerifyCode hmvc){
		try{
    		tx = session.beginTransaction();
	        session.update(hmvc);
	        tx.commit();
	        status = "SUCCESS";
	    }catch(Exception e){
	        tx.rollback();
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    	status = "FAILURE";
	    }
        return status;
	}
	
	public static List<HaadMpatientVerifyCode> listEvent(String index){
		try{
    		tx = session.beginTransaction();
	        Query query=session.createQuery("from HaadMpatientVerifyCode");
	        query.setFirstResult(Integer.parseInt(index) - 1);
	        query.setMaxResults(1);
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
        return query.list();
	}
	
	public static List<HaadMpatientVerifyCode> listEvents(){
		try{
    		tx = session.beginTransaction();
	        query = session.createQuery("from HaadMpatientVerifyCode");
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
        return query.list();
	}
	
	public static String deleteEvent(String index){
		try{
    		tx = session.beginTransaction();
	        Iterator items = listEvent(index).iterator();
	        while(items.hasNext()){
	        	session.delete((HaadMpatientVerifyCode)items.next());
	        	tx.commit();
	        	status = "SUCCESS";
	        }
	    }catch(Exception e){
	        tx.rollback();
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    	status = "FAILURE";
	    }
        return status;
	}
	
	public static String deleteEvent(){
		try{
    		tx = session.beginTransaction();
	        Query query=session.createQuery("delete from HaadMpatientVerifyCode");
	        query.executeUpdate(); 
	        tx.commit();
	        status = "SUCCESS";
	    }catch(Exception e){
	        tx.rollback();
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    	status = "FAILURE";
	    }
		return status;
	}
	
}
