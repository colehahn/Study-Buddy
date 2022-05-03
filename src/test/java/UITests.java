import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class UITests extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        //stage.setScene(TimerScene.timerScene());
        stage.show();
        stage.toFront();
    }

    @Test
    public void hasHelloWorldButton() {
        //StackPane rootNode = TimerScene.timerScene().getRoot();
        //Button button = from(rootNode).lookup(".button").query();
        assert("Hello World".equals("hello worl"));//button.getText());
    }
}