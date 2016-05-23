package marcin.augustyn.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//opcje w mySQL: PrimaryKey, NotNull, Unique, Auto Incerement
	@Column(name = "position_id")
	private int positionId;
	@Column(name = "position_name")
	private String positionName;
	@Column(name = "department")
	private String department;
	@Column(name = "supervisor")
	private String supervisor;
	
	public Position(){
		
	}
	
	public Position(String positionName, String department, String supervisor) {
		this.positionName = positionName;
		this.department = department;
		this.supervisor = supervisor;
	}

	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "Position name: " + positionName + ", department: " + department + ", supervisor: " + supervisor;
	}
}
