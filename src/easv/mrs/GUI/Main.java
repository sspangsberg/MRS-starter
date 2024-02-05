package easv.mrs.GUI;

// Project imports
import easv.mrs.GUI.Util.MessageHandler;
import easv.mrs.Util.MRSException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResource("/View/MovieView1.fxml"));

            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            MessageHandler.displayError(new MRSException("Error starting application.", e));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
