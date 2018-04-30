package dao;

public abstract class AbstractDAOFactory {
	
	// A modifier si on ajoute une nouvelle factory
	public static AbstractDAOFactory getAbstractDAOFactory() {
		return new CsvFactory();
	}

	public abstract BatailleDAO getBatailleDAO();
}
