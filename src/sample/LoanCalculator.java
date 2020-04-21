package sample;

import javafx.application.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.stage.*;

public class LoanCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();

        GridPane pane = new GridPane();
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        BackgroundFill bf = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(1),null);
        pane.setBackground(new Background(bf));

        Scene scene = new Scene(pane, 400, 400);

        Text sceneTitle = new Text("Loan Calculator");
        sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD,26));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Label amount = new Label("Loan Amount $: (1000)");
        pane.add(amount, 0, 1);
        TextField amountInput = new TextField();
        pane.add(amountInput, 1, 1);

        Label rate = new Label(  "Interest Rate %: (3.65)");
        pane.add(rate,0,2);
        TextField rateInput = new TextField();
        pane.add(rateInput, 1, 2);

        Label months = new Label("Months of loan: (36)");
        pane.add(months,0,3);
        Spinner<Integer> monthsInput = new Spinner<>(1, 360, 36);
//        TextField monthsInput = new TextField();
        pane.add(monthsInput, 1, 3);

        Button calculate = new Button("Calculate");
        HBox hbox = new HBox(10);
//        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(calculate);
        pane.add(hbox, 1, 6);

        Text monthlyPayment = new Text();
        monthlyPayment.setFont(Font.font("Arial", FontWeight.BOLD,16));
        pane.add(monthlyPayment, 0, 8, 2, 1);

        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try{
                    double loanAmount = Double.parseDouble(amountInput.getText());
                    double monthlyRate = (Double.parseDouble(rateInput.getText())/100)/12.0;
                    int loanMonths = monthsInput.getValue(); // 
//                    double loanMonths = Integer.parseInt(monthsInput.getText());
                    double totalPayments = (loanAmount * monthlyRate) / (1-Math.pow(1+monthlyRate, -loanMonths));
                    monthlyPayment.setStroke(Color.GRAY);
                    monthlyPayment.setText("Monthly Payment:    $"  + String.format("%.02f", totalPayments));
                }
                catch(NumberFormatException ex){
                    monthlyPayment.setStroke(Color.RED);
                    monthlyPayment.setText("Invalid entry!");
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

