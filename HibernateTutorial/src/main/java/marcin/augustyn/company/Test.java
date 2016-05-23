package marcin.augustyn.company;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test {
	private static SessionFactory factory;
	
	
	public static void main(String[] args) {
		try{
	        factory = new Configuration().
	                  configure().
	                  addPackage("marcin.augustyn.company").//dodawanie pakiet�w
	                  addAnnotatedClass(Department.class).//dodawanie klas z anotacjami
	                  addAnnotatedClass(Employee.class).
	                  buildSessionFactory();
	     }catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	     }
		
		final SessionTransactions<Department> sessionDepartment = new SessionTransactions<Department>();
		final SessionTransactions<Employee> sessionEmployee = new SessionTransactions<Employee>();
		

		System.out.println(sessionDepartment.getObjbyId(2, Department.class, factory));

		//Employee pracownik = new Employee(osoba, pozycja, 2500);
		

		//sessionEmployee.addObjToDb(pracownik, factory);
		//sessionEmployee.readObjsFromDb(pracownik.getClass().getSimpleName(), factory);

	}

}
