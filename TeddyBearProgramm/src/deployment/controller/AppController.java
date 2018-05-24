package deployment.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import deployment.Observable;
import deployment.Observer;
import deployment.model.StockQuoteTimeLapseService;
import deployment.model.StockQuoteTimeLapseServicePortType;
import deployment.model.WSDLStage1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppController /* subject */ extends Application implements Initializable, Observable {

	@FXML
	private TextField txtFieldSymbol;

	@FXML
	public Button btnGraph;

	@FXML
	LineChart<String, Number> lineChart;

	private Set<Observer> observers;
	private WSDLStage1 WSDLobj;
	private String symb;
	private Stage primaryStage;
	private MonitorController monitor;
	private GraphController graphMon;
	// private WSDLStage2 WSDLobj2;

	private boolean isInitialized = false; // deprecated

	public AppController() {
		this.observers = new HashSet<>();

	}

	/*
	 * this code is complete (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("../view/MainFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Stock Monitor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	public void HandleButtonAction(ActionEvent evt) throws Exception // new monitor needs to be created
	{
		// try {

		symb = txtFieldSymbol.getText();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/StockMonitor.fxml")); // this might be the cause
		try {
			loader.load(); // crashes here
		} catch (Exception e) {
			System.out.println("File error" + e.getMessage());
		}
		monitor = loader.getController();
		monitor.setParent(this);
		monitor.setSymbol(symb);
		WSDLStage1 ws = new WSDLStage1();
		ws.set(symb);
		monitor.setWSDL(ws);
		monitor.setObservable(this);
		isInitialized = true;

		Parent p = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.setTitle("Monitor " + symb);
		stage.show(); // creates a Monitor away from our eyes

		// } catch (Exception e) {
		// System.out.println("Button exception \n");
		// System.exit(-666); // EXTERMINATED
		// }

	}

	@FXML
	public ComboBox<String> combobox;

	@FXML
	public void getGraph(ActionEvent evt) throws IOException {

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("../view/MainGraph.fxml")); // this might be the cause
		Parent root;
		root = loader.load();

		StockQuoteTimeLapseService service2 = new StockQuoteTimeLapseService(
				new URL("http://viper.infotech.monash.edu.au:8180/axis2/services/StockQuoteTimeLapseService?wsdl"));
		StockQuoteTimeLapseServicePortType port2 = service2.getStockQuoteTimeLapseServiceHttpSoap12Endpoint();
		List<String> avlSym = port2.getSymbols().getReturn();
		// combobox.getItems().addAll(avlSym);
		System.out.println(avlSym);

		// monGraph = loader.getController();
		// monGraph.setParent(this);
		// monGraph.setObservable(this);
		isInitialized = true;
		Stage stage = new Stage();
		// setGraph();

		stage.setScene(new Scene(root));
		stage.show();
		//
		// Stage primaryStage = new Stage();
		// Platform.runLater(new Runnable() {
		// public void run() {
		// try {
		// new Main().start(primaryStage);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });

	}

	protected String getSymbol() {
		return symb;
	}

	public Set<Observer> getMonitors() {
		if (isInitialized)
			return observers;
		else
			return null; // no monitors initialized.
		// catch null in AppDriver and handle the exception
	}

	public boolean isInitialized() {
		return isInitialized();
	}

	public boolean hasMonitors() {
		if (!observers.isEmpty()) // exception at this line
			return true;
		else
			return false;
	}

	public void pushNotification() {
		// notify observers to update
		System.out.println("notifying!\n");
		notifyObs();
	}

	public void startup(String[] args) {
		Application.launch(args); // launch point
	}

	// Getting the List<String> which contains the 6 valid stock symbols.

	public List<String> getSymbolData() throws MalformedURLException {

		StockQuoteTimeLapseService service2 = new StockQuoteTimeLapseService(
				new URL("http://viper.infotech.monash.edu.au:8180/axis2/services/StockQuoteTimeLapseService?wsdl"));
		StockQuoteTimeLapseServicePortType port2 = service2.getStockQuoteTimeLapseServiceHttpSoap12Endpoint();
		List<String> avlSym = port2.getSymbols().getReturn();

		return avlSym;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// This needs to be empty for first window to initialize
		// But the code below needs to initialize to set the combobox values with the
		// values from getSymbolData() method above. Like in the WorkingGraph example.

		// try {
		// List<String> sym = getSymbolData();
		// ObservableList<String> list = FXCollections.observableArrayList(sym);
		// combobox.setItems(list);
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public void notifyObs() {
		// TODO Auto-generated method stub
		for (Observer observer : observers)
			observer.update();

	}

	@Override
	public void addObs(Observer obs) {
		// TODO Auto-generated method stub
		this.observers.add(obs);
	}

	@Override
	public void removeObs(Observer obs) {
		// TODO Auto-generated method stub
		this.observers.remove(this);
	}
}
