package deployment.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GetGraphSymbols {
	
	
	
	List<String> symbols;
	
	
	
	public List<String> getSymbols() throws MalformedURLException {
		StockQuoteTimeLapseService service2 = new StockQuoteTimeLapseService(new URL("http://viper.infotech.monash.edu.au:8180/axis2/services/StockQuoteTimeLapseService?wsdl"));
		StockQuoteTimeLapseServicePortType port2 = service2.getStockQuoteTimeLapseServiceHttpSoap12Endpoint();
		List<String> sym = port2.getSymbols().getReturn();
		return sym;
	}




}
