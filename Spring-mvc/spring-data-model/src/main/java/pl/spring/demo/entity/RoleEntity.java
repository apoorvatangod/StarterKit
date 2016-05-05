package pl.spring.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.spring.demo.enumerations.UserRole;

@Entity
@Table(name = "ROLEENTITY")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 429445579215515378L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	private String userName;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	// for hibernate
	protected RoleEntity() {
	}

	public RoleEntity(Long id, String user, UserRole userRole) {
		this.id = id;
		this.userName = user;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
