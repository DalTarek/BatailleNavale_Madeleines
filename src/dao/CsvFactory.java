package dao;

public class CsvFactory extends AbstractDAOFactory {

	@Override
	public BatailleDAO getBatailleDAO() {
		return CsvDAO.getInstance();
	}
}
