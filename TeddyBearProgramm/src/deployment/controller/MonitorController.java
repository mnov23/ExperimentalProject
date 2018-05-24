package deployment.controller;

import java.net.URL;
import java.util.ResourceBundle;

import deployment.Observable;
import deployment.Observer;
import deployment.model.WSDLStage1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MonitorController /* the one being observed */ implements Observer, Initializable {

	@FXML
	private Label label1;

	@FXML
	private Button btnClose;

	private String symbol;
	private AppController parent;
	private WSDLStage1 getservice;
	private String str;

	@FXML
	public void closeButtonAction(ActionEvent ae) {

		Stage stage = (Stage) btnClose.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setParent(Object obj) {
		if (obj instanceof AppController) {
			parent = (AppController) obj;
		}
	}

	public void setWSDL(WSDLStage1 obj) {
		if (obj instanceof WSDLStage1) {
			getservice = obj;
			label1.setText(getservice.get());
			symbol = parent.getSymbol();
		}
	}

	@Override
	public void update() {

		System.out.println("updating");
		// update GUI
		try {
			getservice = new WSDLStage1();
			getservice.set(parent.getSymbol());
			str = getservice.get();
			label1.setText("LOLOLOLOLOLO");
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}

	@Override
	public void setObservable(Observable subj) {

		// parent = subj;
		// subj.addObs(this);

		if (subj instanceof AppController) {
			parent = (AppController) subj;
			subj.addObs(this);
		}

		// if (subject /* local */ instanceof SubjectAccount) {
		// /* global */account = (SubjectAccount) subject /* local */;
		// /* local */subject.addObserver(this);
		// }

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

}
