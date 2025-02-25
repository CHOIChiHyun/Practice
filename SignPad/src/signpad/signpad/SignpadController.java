package signpad.signpad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signpad.checkRegistSign.ChkRegistSignController;

import java.net.URL;
import java.util.ResourceBundle;

public class SignpadController implements Initializable {
    @FXML private Canvas canvas;
    @FXML private Label lblName;
    @FXML private Button btnOk;
    @FXML private Button btnClear;

    private GraphicsContext graphic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setOnMousePressed(this::startDrawing);
        canvas.setOnMouseDragged(this::draw);

        btnOk.setOnAction(this::handleBtnOkAction);
        btnClear.setOnAction(this::handleBtnClearAction);

        lblName.setText("서명을 입력해주세요.");

        graphic = canvas.getGraphicsContext2D();
    }

    private void startDrawing(MouseEvent event) {
        graphic.beginPath();
        graphic.moveTo(event.getX(), event.getY());
        graphic.stroke();
    }

    private void draw(MouseEvent event) {
        graphic.lineTo(event.getX(), event.getY());
        graphic.stroke();
    }

    private void handleBtnOkAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/signpad/checkRegistSign/chkRegistSignView.fxml"));
            Parent root = loader.load();

            ChkRegistSignController chkRegistSignController = loader.getController();

            WritableImage snapshot = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, snapshot);
            chkRegistSignController.setSignatureImage(snapshot);

            loadStage(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBtnClearAction(ActionEvent event) {
        graphic.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // canvas Clear
    }

    private void loadStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}