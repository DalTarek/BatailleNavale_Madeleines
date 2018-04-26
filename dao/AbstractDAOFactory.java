package dao;

public abstract class AbstractDAOFactory {
	
	public static AbstractDAOFactory getAbstractDAOFactory() {
		return new CsvFactory();
	}

	public abstract BatailleDAO getBatailleDAO();
}
