/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deployment.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Baz
 */
public class Main extends Application {
	
	

	public static void main(String[] args) {
		launch(args); // or here
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage) NOT WORKING.
	 * SEE line : Parent root = FXMLLoader.load(...) ...
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainGraph.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("Stock Graph");
    	primaryStage.setScene(scene);
    	primaryStage.show();
		
	}
}
