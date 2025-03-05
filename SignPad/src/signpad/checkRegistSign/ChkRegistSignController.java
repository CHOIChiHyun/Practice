package signpad.checkRegistSign;

import javafx.application.Platform;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signpad.utility.Log;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChkRegistSignController implements Initializable {
    @FXML private Button btnCancel;
    @FXML private Canvas canvas;
    @FXML private Label lblText;
    @FXML private Button btnRegist;

    private WritableImage signatureImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegist.setOnAction(this::handleBtnRegistAction);
        btnCancel.setOnAction(this::handleBtnCancelAction);

        lblText.setText("서명을 등록하시겠습니까?");
    }

    public void setSignatureImage(WritableImage image) {
        this.signatureImage = image;
        displaySignature();
    }

    private void displaySignature() {
        if (signatureImage != null) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(signatureImage, 0, 0);
        }
    }

    private void handleBtnRegistAction(ActionEvent actionEvent) {
        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(btnRegist.getScene().getWindow());

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/signpad/utility/SimpleDialog.fxml")));

            Button btnOk = (Button) parent.lookup("#btnOk");
            btnOk.setText("확인");
            btnOk.setOnAction(e -> {
                dialog.close();
                Platform.exit();
            });

            Label lblDialogText = (Label) parent.lookup("#lblDialogText");
            lblDialogText.setText("서명 등록을 완료하였습니다.");

            Scene scene = new Scene(parent);

            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //git test
    private void handleBtnCancelAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}

