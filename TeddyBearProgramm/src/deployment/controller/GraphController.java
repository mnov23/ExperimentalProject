package deployment.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import deployment.Observable;
import deployment.Observer;
import deployment.model.StockQuoteTimeLapseService;
import deployment.model.StockQuoteTimeLapseServicePortType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;

public class GraphController implements Observer, Initializable {

	@FXML
	LineChart<String, Number> lineChart;

	@FXML
	public ComboBox<String> combobox;

	private String symbol;
	private AppController parent;
	// private WSDLStage2 getservice;
	private String str;

	public void setParent(Object obj) {
		if (obj instanceof AppController) {
			parent = (AppController) obj;
		}
	}

	public List<String> getSymbolData() throws MalformedURLException {

		StockQuoteTimeLapseService service2 = new StockQuoteTimeLapseService(
				new URL("http://viper.infotech.monash.edu.au:8180/axis2/services/StockQuoteTimeLapseService?wsdl"));
		StockQuoteTimeLapseServicePortType port2 = service2.getStockQuoteTimeLapseServiceHttpSoap12Endpoint();
		List<String> avlSym = port2.getSymbols().getReturn();

		return avlSym;
	}

	// Fill the combo box with the 6 valid stock symbols
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			List<String> sym = getSymbolData();
			ObservableList<String> list = FXCollections.observableArrayList(sym);
			combobox.setItems(list);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void comboChanged(ActionEvent event) throws MalformedURLException {

		String value = combobox.getValue();
		String chosenSym = value.substring(0, 3);
		System.out.println(chosenSym);
		getData(chosenSym);
	}

	@Override
	public void update() {
		System.out.println("Updating");

	}

	@Override
	public void setObservable(Observable subj) {

		if (subj instanceof AppController) {
			parent = (AppController) subj;
			subj.addObs(this);
		}
	}

	// this line of code gave you the error
	// public void setSymbol(ActionEvent event) throws MalformedURLException {
	// String value = combobox.getValue();
	// String chosenSym = value.substring(0, 3);
	// getData(chosenSym);
	// // System.out.println(chosenSym);
	// }

	// getting info of timelapse of stock and available symbols to be used.
	public void getData(String val) throws MalformedURLException {

		StockQuoteTimeLapseService service2 = new StockQuoteTimeLapseService(
				new URL("http://viper.infotech.monash.edu.au:8180/axis2/services/StockQuoteTimeLapseService?wsdl"));
		StockQuoteTimeLapseServicePortType port2 = service2.getStockQuoteTimeLapseServiceHttpSoap12Endpoint();

		List<String> currentStockInfo = port2.getStockQuote(val); // get stock quote time lapse info

		// 0=symbol 1=lastTrade 2=Date 3=Time 4=Change 5=Open 6=DayHigh 7=daylow
		// 8=Volume

		String name = currentStockInfo.get(0);
		String lastTradeVal = currentStockInfo.get(1);
		String date = currentStockInfo.get(2);

		// close off get data

		// populate data
		// getdata + this following piece of code.

		// ADD data
		Timeline time = new Timeline();

		time.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) -> {

			int lastVal;
			try {

				int count = 1;
				lastVal = NumberFormat.getNumberInstance(Locale.ENGLISH).parse(lastTradeVal).intValue();
				XYChart.Series<String, Number> series = new XYChart.Series<>();
				series.getData().add(new XYChart.Data<String, Number>(date, lastVal));

				// int count = 1;
				// lineChart.setLegendVisible(false);

				String chosenSym = name.substring(0, 3);

				series.setName(chosenSym);
				lineChart.getData().add(series);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}));

		time.setCycleCount(1000);
		time.setAutoReverse(false);
		time.play();

		// int lastVal =
		// NumberFormat.getNumberInstance(Locale.ENGLISH).parse(lastTradeVal).intValue();

		// XYChart.Series<String, Number> series = new XYChart.Series<>();

		// lineChart.getData().add(series);

		// series.getData().add(new XYChart.Data<String, Number>("Jan", 4000));
		// //example

	}
}
