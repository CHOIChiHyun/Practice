package calculator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import calculator.utility.Log;
import calculator.utility.WindowDraggable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    @FXML private Button btnClose;
    @FXML private Button btnDiv;
    @FXML private Button btnClear;
    @FXML private Label result;
    @FXML private Button btn8;
    @FXML private Button btn9;
    @FXML private Button btn6;
    @FXML private Button btnSub;
    @FXML private Button btn7;
    @FXML private Button btnAdd;
    @FXML private Button btn4;
    @FXML private Button btn5;
    @FXML private Button btn2;
    @FXML private Button btnMul;
    @FXML private Button btn3;
    @FXML private Button btn0;
    @FXML private Button btn1;
    @FXML private Button btnResult;
    @FXML private Button btnBackspace;
    @FXML private HBox windowBar;
    @FXML private Button btnPoint;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnClose.setOnAction(event -> Platform.exit());
        btnClear.setOnAction(this::handleBtnClearAction);
        btnResult.setOnAction(this::handleBtnResultAction);
        btnBackspace.setOnAction(this::handleBtnBackspaceAction);

        btn0.setOnAction(this::handleBtn0Action);
        btn1.setOnAction(this::handleBtn1Action);
        btn2.setOnAction(this::handleBtn2Action);
        btn3.setOnAction(this::handleBtn3Action);
        btn4.setOnAction(this::handleBtn4Action);
        btn5.setOnAction(this::handleBtn5Action);
        btn6.setOnAction(this::handleBtn6Action);
        btn7.setOnAction(this::handleBtn7Action);
        btn8.setOnAction(this::handleBtn8Action);
        btn9.setOnAction(this::handleBtn9Action);
        btnPoint.setOnAction(this::handleBtnPointAction);

        btnAdd.setOnAction(this::handleBtnAddAction);
        btnSub.setOnAction(this::handleBtnSubAction);
        btnMul.setOnAction(this::handleBtnMulAction);
        btnDiv.setOnAction(this::handleBtnDivAction);

        // 상단 Bar 누르면 창 이동 가능
        WindowDraggable windowDraggable = new WindowDraggable();
        windowDraggable.stageDraggableMoveWindow(windowBar);

        // 키보드 입력 설정
        Platform.runLater(() -> result.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            boolean isShiftPressed = event.isShiftDown();

            switch (code) {
                case DIGIT0:
                case NUMPAD0:
                    handleBtn0Action(null);
                    break;
                case DIGIT1:
                case NUMPAD1:
                    handleBtn1Action(null);
                    break;
                case DIGIT2:
                case NUMPAD2:
                    handleBtn2Action(null);
                    break;
                case DIGIT3:
                case NUMPAD3:
                    handleBtn3Action(null);
                    break;
                case DIGIT4:
                case NUMPAD4:
                    handleBtn4Action(null);
                    break;
                case DIGIT5:
                case NUMPAD5:
                    handleBtn5Action(null);
                    break;
                case DIGIT6:
                case NUMPAD6:
                    handleBtn6Action(null);
                    break;
                case DIGIT7:
                case NUMPAD7:
                    handleBtn7Action(null);
                    break;
                case DIGIT8:
                    if (isShiftPressed) {
                        handleBtnMulAction(null);
                    } else {
                        handleBtn8Action(null);
                    }
                    break;
                case NUMPAD8:
                    handleBtn8Action(null);
                    break;
                case DIGIT9:
                case NUMPAD9:
                    handleBtn9Action(null);
                    break;
                case PERIOD:
                    handleBtnPointAction(null);
                    break;
                case ADD:
                case PLUS:
                    handleBtnAddAction(null);
                    break;
                case EQUALS:
                    if (isShiftPressed) {
                        handleBtnAddAction(null);
                    } else {
                        handleBtnResultAction(null);
                    }
                    break;
                case SUBTRACT:
                case MINUS:
                    handleBtnSubAction(null);
                    break;
                case MULTIPLY:
                case ASTERISK:
                    handleBtnMulAction(null);
                    break;
                case DIVIDE:
                case SLASH:
                    handleBtnDivAction(null);
                    break;
                case ENTER:
                    handleBtnResultAction(null); // 엔터 키 → = 버튼 실행
                    break;
                case ESCAPE:
                case DELETE:
                    handleBtnClearAction(null); // 백스페이스 → Clear 버튼 실행
                    break;
                case BACK_SPACE:
                    handleBtnBackspaceAction(null);
                    break;
                case F4:
                    Platform.exit();
                    break;
                default:
                    break;
            }
        }));
    }

    private double calResult = 0;  //연산 결과
    ArrayList<Double> number = new ArrayList<>(); // 입력된 숫자 배열
    ArrayList<String> strNumber = new ArrayList<>();  // 입력된 숫자 배열(String)
    ArrayList<String> oper = new ArrayList<>();   // 입력된 연산자 배열
    Calculator calculator = new Calculator();   // Calculator Model

    private void handleBtn0Action(ActionEvent event) { displayNumber(btn0); }
    private void handleBtn1Action(ActionEvent event) { displayNumber(btn1); }
    private void handleBtn2Action(ActionEvent event) { displayNumber(btn2); }
    private void handleBtn3Action(ActionEvent event) { displayNumber(btn3); }
    private void handleBtn4Action(ActionEvent event) { displayNumber(btn4); }
    private void handleBtn5Action(ActionEvent event) { displayNumber(btn5); }
    private void handleBtn6Action(ActionEvent event) { displayNumber(btn6); }
    private void handleBtn7Action(ActionEvent event) { displayNumber(btn7); }
    private void handleBtn8Action(ActionEvent event) { displayNumber(btn8); }
    private void handleBtn9Action(ActionEvent event) { displayNumber(btn9); }
    private void handleBtnPointAction(ActionEvent event) { displayNumber(btnPoint); }

    private void handleBtnAddAction(ActionEvent event) { handleOperator(btnAdd); }
    private void handleBtnSubAction(ActionEvent event) { handleOperator(btnSub); }
    private void handleBtnMulAction(ActionEvent event) { handleOperator(btnMul); }
    private void handleBtnDivAction(ActionEvent event) { handleOperator(btnDiv); }

    private void handleBtnResultAction(ActionEvent event) { calculate(); }

    private void handleBtnClearAction(ActionEvent event) {
        result.setText("");
        arrayClear();
        Log.info("CLEAR SUCCESS!!");
    }

    private void handleBtnBackspaceAction(ActionEvent event) {
        int length = strNumber.size();
        strNumber.remove(length - 1);
        StringBuilder backspaceNumber = new StringBuilder();
        for (String s : strNumber) {
            backspaceNumber.append(s);
        }
        result.setText(backspaceNumber.toString());
    }

    private void calculate() {
        saveNumber();

        // 입력된 숫자가 2개가 안되면 연산 진행 안하고 calculate 매서드 종료
        if (number.size() < 2) { return; }

        calculateResult(); // 연산자별 연산 진행

        // 계산 후 단순 반복 작업
        number.clear();
        number.add(calResult);
        Log.info("계산 결과 = " + calResult);

        // Integer 형/Double 형 판별 후 형태에 맞게 setText()
        checkResultFormat();
    }

    private void checkResultFormat() {
        String strResult;
        if (calResult == (int) calResult) {
            strResult = Integer.toString((int) calResult);
        } else {
            strResult = Double.toString(calResult);
        }
        result.setText(strResult);
    }

    private void calculateResult() {
        switch (oper.get(0)) {
            case "+":
                calResult = calculator.add(number.get(0), number.get(1));
                break;
            case "-":
                calResult = calculator.sub(number.get(0), number.get(1));
                break;
            case "X":
                calResult = calculator.mul(number.get(0), number.get(1));
                break;
            case "/":
                try {
                    calResult = calculator.div(number.get(0), number.get(1));
                } catch (ArithmeticException e) {
                    Log.info("Arithmetic Exception 발생!");
                }
                break;
            default:
                break;
        }
    }

    private void displayNumber(Button button) {
        List<String> operators = Arrays.asList("+", "-", "X", "/");

        if (operators.contains(result.getText())) {
            result.setText("");
        }

        String inputNumber = button.getText();
        strNumber.add(inputNumber);

        StringBuilder setNumber = new StringBuilder();
        for (String s : strNumber) {
            setNumber.append(s);
        }

        result.setText(setNumber.toString());
    }

    private void saveNumber() {
        try {
            StringBuilder num = new StringBuilder();
            for (String s : strNumber) {
                num.append(s);
            }
            strNumber.clear();
            number.add(Double.parseDouble(num.toString()));
        } catch (NumberFormatException e) {
            Log.info("NumberFormatException 발생!");
        }
    }

    private void arrayClear() {
        number.clear();
        oper.clear();
        strNumber.clear();
    }

    private void handleOperator(Button button) {
        saveNumber();

        result.setText(button.getText());

        if (!oper.isEmpty()) {
            calculate();
            oper.clear();
        }

        oper.add(button.getText());
    }
}
