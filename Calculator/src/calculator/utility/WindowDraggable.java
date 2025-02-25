package calculator.utility;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WindowDraggable {
    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage = null;

    public void stageDraggableMoveWindow(HBox hBox) {
        windowBarPressed(hBox);
        windowBarDragged(hBox);
        windowBarDragDone(hBox);
        mouseReleased(hBox);
    }

    private void windowBarPressed(HBox hBox) {
        hBox.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
    }

    private void windowBarDragged(HBox hBox) {
        hBox.setOnMouseDragged(event -> {
            stage = (Stage) hBox.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            stage.setOpacity(0.8f);
        });
    }

    private void windowBarDragDone(HBox hBox) {
        hBox.setOnDragDone(event -> {
            stage = (Stage) hBox.getScene().getWindow();
            stage.setOpacity(1.0f);
        });
    }

    private void mouseReleased(HBox hBox) {
        hBox.setOnMouseReleased(event -> {
            stage = (Stage) hBox.getScene().getWindow();
            stage.setOpacity(1.0f);
        });
    }
}