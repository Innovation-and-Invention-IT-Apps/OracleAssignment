package org.bs5lndk.persistent.service;

import java.util.Iterator;
import java.util.List;

import org.bs5lndk.persistent.beans.EventsBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
		}
		return instance;
	}
	
	public String makeNewEvent(EventsBean hmvc){
		try{
    		session = sessionFactory.openSession();
    		tx = session.beginTransaction();
	        session.save(hmvc);
	        tx.commit();
	        session.close();
	        status = "SUCCESS";
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    	status = "FAILURE";
	    }
		return status;
	}
	
	public String updateEvent(EventsBean hmvc, String index){
		try{
	        Iterator items = listEvent(index).iterator();
	        while(items.hasNext()){
	        	EventsBean dirtyObj = (EventsBean)items.next();
	        	hmvc.setId(dirtyObj.getId());
	        	session = sessionFactory.openSession();
	    		tx = session.beginTransaction();
		        session.update(hmvc);
	        	tx.commit();
		        session.close();
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
	
	public List<EventsBean> listEvent(String index){
		try{
    		session = sessionFactory.openSession();
    		tx = session.beginTransaction();
	        query=session.createQuery("from EventsBean");
	        query.setFirstResult(Integer.parseInt(index) - 1);
	        query.setMaxResults(1);
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
		List<EventsBean> result = query.list();
        return result;
	}
	
	public List<EventsBean> listEvents(){
		try{
    		session = sessionFactory.openSession();
    		tx = session.beginTransaction();
	        query = session.createQuery("from EventsBean");
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
		List<EventsBean> result = query.list();
        return result;
	}
	
	public String deleteEvent(String index){
		try{
    		session = sessionFactory.openSession();
    		tx = session.beginTransaction();
	        Iterator items = listEvent(index).iterator();
	        while(items.hasNext()){
	        	EventsBean dirtyObj = (EventsBean)items.next();
	        	session.delete(dirtyObj);
	        	tx.commit();
		        session.close();
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
	
	public String deleteEvents(){
		try{
    		session = sessionFactory.openSession();
    		tx = session.beginTransaction();
	        query=session.createQuery("delete from EventsBean");
	        query.executeUpdate(); 
	        tx.commit();
	        session.close();
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