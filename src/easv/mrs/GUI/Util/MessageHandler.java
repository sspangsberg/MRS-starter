package easv.mrs.GUI.Util;

import easv.mrs.Util.MRSException;

import javafx.scene.control.Alert;

public class MessageHandler {

    /**
     *
     * @param ex
     */
    public static void displayError(Exception ex)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(ex.getMessage());
        alert.showAndWait();

        // optionally print stack stace
        ex.printStackTrace();
    }
}
