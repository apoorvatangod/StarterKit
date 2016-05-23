package marcin.augustyn.company;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class SessionTransactions<T> {
	//dodawanie obiekt�w do bazy danych
	public void addObjToDb(T t, SessionFactory factory){
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(t); 
	         tx.commit();
	      }catch (ConstraintViolationException e) {
	         System.out.println("Duplicate value of unique variable.");
	      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		  }finally {
	         session.close(); 
	      }
	}
	//odczyt obiekt�w z bazy danych
	public void readObjsFromDb(String classType, SessionFactory factory){
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List objects = session.createQuery("FROM " + classType).list(); //np "FROM Person"
	         for (Iterator iterator = 
	        		 objects.iterator(); iterator.hasNext();){
	            T tObj = (T) iterator.next(); 
	            System.out.println(tObj); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	//pobranie obiektu z bazy po id
	public T getObjbyId(int id, Class<?> classType, SessionFactory factory ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         T tOut = (T)session.get(classType, id); 
	         return tOut;
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return null;
	   }
}
