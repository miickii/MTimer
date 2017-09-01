package micki;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import micki.Controller.Controller;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("MTimer");

        Controller controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        controller.getBorderPane().requestFocus();
        controller.initializeScene(scene);
        controller.updateEverything();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
