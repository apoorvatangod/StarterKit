package marcin.augustyn.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;  

//u�ywamy javax annotations a nie hibernate annotations - bo sa bardziej standardowe, bo gdyby firma zdecydowa�a si� 
//wyj�� z hibernate to trzeba by zmienia� wszystkie anotacje na z hibernate na  javax 
//@Entity m�wi, �e chcemy stworzy� tabel� z tej klasy
@Entity 
@Table(name = "person")
public class Person {
	//by klasa mog�a byc beanem musi mie� private instance variables+gettery/settery
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private int personId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "pesel", unique = true)
	private long pesel;
	
	
	public Person(String firstName, String lastName, long pesel){
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
	}
	public Person(){//musi by� default contructor by m�c wykonac zapytanie session2.createQuery("FROM Person").list(); 
		//System.out.println("Default contructor");
	}
	
	//@Id//mo�na tu (nad getterem) lub nad instance variable kt�re ma by� primary key
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPesel() {
		return pesel;
	}
	public void setPesel(long pesel) {
		this.pesel = pesel;
	}
	@Override
	public String toString() {
		return "Name: " + firstName + " " + lastName + ", PESEL: " + pesel;
	}
}
