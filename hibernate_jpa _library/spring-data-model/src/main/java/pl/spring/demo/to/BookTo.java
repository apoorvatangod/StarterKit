package pl.spring.demo.to;

public class BookTo {
    private Long id;
    private String title;
    private String authors;
    private Long version;
    public BookTo() {
    }

    public BookTo(Long id, String title, String authors, Long version) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.version = version;
    }
    public BookTo(Long id, String title, String authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
    
}
