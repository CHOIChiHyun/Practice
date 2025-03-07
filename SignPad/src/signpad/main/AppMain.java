package signpad.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class AppMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/signpad/signpad/SignpadView.fxml")));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    // push/pull test(push할때 로그인 인증 해야하는지 test1)
    // 노트북에서 clone 테스트 -> 변경 후 push
    // PC에서 pull 이후 수정 후 다시 push -> 노트북에서 pull 필요
    // 노트북에서 pull 완료 이후 재 push
}
