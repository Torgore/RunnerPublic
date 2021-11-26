import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class HelloWorld {

    public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {

            primaryStage.setTitle("Hello world");
            Group root = new Group();
            Pane pane = new Pane(root);
            Scene theScene = new Scene(pane, 600, 400, true);
            primaryStage.setScene(theScene);

            primaryStage.show();
        }


        public  void main(String[] args) {
            launch(args);
        }
    }

}