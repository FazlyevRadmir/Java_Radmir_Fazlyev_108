import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.application.Platform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javax.swing.*;


enum Directions {
    UP,DOWN,LEFT,RIGHT
}
public class SnakeGame extends Application {

    private Pane root;
    private Random random;
    private Rectangle snakeBody;
    private Rectangle appleBody;
    private Text scoreTable;
    private Directions direction;
    private Integer score = 0;

    public static final int widthScreen = 500;
    public static final int heightScreen = 500;
    private static final int step = 20;

    @Override
    public void start(Stage stage) {
        root = new Pane();
        root.setPrefSize(widthScreen, heightScreen);
        random = new Random();
        SnakeBody();
        AppleBody();
        Score();
        Runnable runnable = () -> {
            try {
                for (;;) {
                    movement();
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {}
        };
        Scene scene = new Scene(root);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.W || code == KeyCode.UP){
                snakeBody.setY(snakeBody.getY() - step);
            }
            else if (code == KeyCode.S || code == KeyCode.DOWN){
                snakeBody.setY(snakeBody.getY() + step);
            }
            else if (code == KeyCode.A || code == KeyCode.LEFT){
                snakeBody.setX(snakeBody.getX() - step);
            }
            else if (code == KeyCode.D || code == KeyCode.RIGHT){
                snakeBody.setX(snakeBody.getX() + step);
            }
        });
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void direction(){
        if (direction == Directions.LEFT){
            direction = Directions.LEFT;
        }
        else if (direction == Directions.UP){
            direction = Directions.UP;
        }
        else if (direction == Directions.RIGHT){
            direction = Directions.RIGHT;
        }
        else if (direction == Directions.DOWN){
            direction = Directions.DOWN;
        }
    }

    public Node SnakeBody(){
        snakeBody = new Rectangle(240,240,20, 20);
        snakeBody.setFill(Color.DARKGREEN);
        root.getChildren().add(snakeBody);
        return null;
    }

    private void growSnake() {
        snakeBody.setHeight(snakeBody.getHeight() + 5);
        snakeBody.setWidth(snakeBody.getWidth() + 5);
    }

    private void AppleBody(){
        appleBody = new Rectangle(random.nextInt(SnakeGame.widthScreen - 6), random.nextInt(SnakeGame.heightScreen - 6), 13, 13);
        appleBody.setFill(Color.RED);
        root.getChildren().add(appleBody);
    }

    private void movement() {
        Platform.runLater(() -> {
            direction();
            gameOver();
            if (appleBody.intersects(snakeBody.getBoundsInLocal())){
                score++;
                scoreTable.setText("Your score: " + score + "/10");
                growSnake();
                root.getChildren().remove(appleBody);
                AppleBody();
                if (score == 10){
                    win();
                }
            }
        });
    }

    private void Score() {
        scoreTable = new Text(1,20,"Your score: " + score + "/10");
        scoreTable.setFont(new Font("Digital-7", 20));
        root.getChildren().add(scoreTable);
    }

    private void win() {
        score = 0;
        scoreTable.setText("Победа!");
        root.getChildren().remove(snakeBody);
        root.getChildren().add(SnakeBody());
    }

    private void gameOver() {
        if (snakeBody.getX() < 0 || snakeBody.getY() <  0 || snakeBody.getX() > (widthScreen - 1) || snakeBody.getY() > (widthScreen - 1)) {
            score = 0;
            scoreTable.setText("Проигрыш!");
            root.getChildren().remove(snakeBody);
            root.getChildren().add(SnakeBody());
        }
    }
}
