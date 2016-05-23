package marcin.augustyn.company;

public enum PositionEnum {
	JAV("Java Developer"),
	JJAV ("Junior Java Developer"),
	SJAV ("Senior Java Developer"),
	DOT(".NET Developer"),
	JDOT("Junior .NET Developer"),
	SDOT("Senior .NET Developer");
	
	private String posName;
	
	PositionEnum(String posName){
		this.posName = posName;
	}

	public String getPositionName() {
		return posName;
	}
}
