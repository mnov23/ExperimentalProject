package deployment.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import deployment.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class WSDLStage2 {

	@FXML
	public ComboBox<String> combobox;
	private String choosen_symbol;
	
	
	
	
	public void set(String sym) {
		this.choosen_symbol = sym;
	}
	
	
	
	public List<String> get(String val) throws MalformedURLException {
		

		StockQuoteTimeLapseService service2 = new StockQuoteTimeLapseService(new URL("http://viper.infotech.monash.edu.au:8180/axis2/services/StockQuoteTimeLapseService?wsdl"));
		StockQuoteTimeLapseServicePortType port2 = service2.getStockQuoteTimeLapseServiceHttpSoap12Endpoint();
		List<String> currentStockInfo = port2.getStockQuote(val);
		return currentStockInfo;
	}
	
	
	
	
	

}
