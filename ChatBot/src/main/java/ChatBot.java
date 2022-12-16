import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class ChatBot extends Application {
    private final Weather req = new Weather();
    TextField textField = new TextField();
    TextArea textArea = new TextArea();
    Button button = new Button("Send");
    Label weather = new Label();
    VBox v = new VBox();
    HBox h = new HBox();

    @Override
    public void start(Stage stage) {
        button.setOnAction(e -> {
            if (textField.getText().isEmpty()) {
                textArea.appendText("Bot Caleb:" + "\n" + "Please, Enter city" + "\n" + "\n");
            } else {
                try {
                    textArea.appendText("You:" + "\n" + "What is the weather like in " + textField.getText() + "?" + "\n" + "\n");
                    Map<String,String> map = req.req(textField.getText());
                    textArea.appendText("Bot Caleb:" + "\n" + "In " + map.get("name") + " now " + map.get("temperature") + " degrees" + "\n" + "\n");
                    textField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    textArea.appendText("Bot Caleb:" + "\n" + "I dont know, sorry" + "\n" + "\n");
                }
            }
        });

        v.setId("root");
        v.setId("top");
        textField.setPrefSize(250, 25);
        textField.setPromptText("Enter city");
        button.setPrefSize(50, 25);
        textArea.setPrefSize(300, 475);
        textArea.setEditable(false);

        HBox.setMargin(textField, new Insets(0, 0, 0, 0));
        HBox.setMargin(button, new Insets(0, 0, 0, 0));
        h.getChildren().addAll(textField, button);
        VBox.setMargin(weather, new Insets(0, 0, 0, 0));
        v.getChildren().addAll(textArea, h);

        Scene scene = new Scene(v, 300, 500);
        stage.setScene(scene);
        stage.setTitle("Weather Bot");
        stage.show();
    }
}