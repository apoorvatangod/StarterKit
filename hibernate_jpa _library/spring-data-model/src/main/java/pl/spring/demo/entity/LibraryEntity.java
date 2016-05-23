package pl.spring.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity implements Serializable {
	private static final long serialVersionUID = 639558456599631097L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "library" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BookEntity> books = new HashSet<>();

    // for hibernate
    protected LibraryEntity() {
}

    public LibraryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setAuthors(Set<BookEntity> books) {
        this.books = books;
    }
}
