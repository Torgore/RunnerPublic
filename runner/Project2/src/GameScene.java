import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameScene extends Scene {
    static Group root;
    static long prevtime = 0;
    static long dt = 0;

    static long prevtime2 = 0;
    static long dt2 = 0;

    private Hero hero = new Hero("heros.png", 200, 220);
    private Camera myCamera = new Camera(300 % 800, 20, hero);
    private Stage primaryStage;
    private StaticThing right;
    private StaticThing left;

    private int oX1;
    private int oY1;
    private int oX2;
    private int oY2;
    private int cropX1;
    private int cropY1;
    private int cropX2;
    private int cropY2;

    Boule myBalls = new Boule(hero);
    //private boolean bouleCanExist=true;

    private int i = 0;


    public GameScene(Group root, Stage primaryStage) {
        super(root);
        this.root = root;
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(this);

        timer.start();

        //instantiation des caractéristiques des deux backgrounds :
        this.oX1 = myCamera.getX();
        this.oY1 = myCamera.getY();
        this.oX2 = 0;
        this.oY2 = myCamera.getY();
        this.cropX1 = myCamera.getCameraCropX();
        this.cropY1 = myCamera.getHigh();
        this.cropX2 = myCamera.getWidth() - myCamera.getCameraCropX();
        this.cropY2 = myCamera.getHigh();

        this.left = new StaticThing("desert.png", 0, 0, oX1, oY1, cropX1, cropY1, myCamera);
        this.right = new StaticThing("desert.png", myCamera.getCameraCropX(), 0, oX2, oY2, cropX2, cropY2, myCamera);

        //conditions passage de right a left
        if (800 - myCamera.getX() >= myCamera.getWidth()) {
            root.getChildren().add(left.getImageView());
        } else {
            root.getChildren().add(left.getImageView());
            root.getChildren().add(right.getImageView());
        }

        root.getChildren().add(hero.getImageView());
        root.getChildren().add(myBalls.getImageView());

        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE:
                    hero.jump();
                    break;

                case D:
                    hero.run();
                    hero.goFaster();
                    break;

                case Q:
                    hero.run();
                    hero.goLower();
                    break;

                case Z:
                    hero.jumpNshoot();
                    break;

                case E:
                    hero.shoot(myBalls);
                    break;
            }
        });

        this.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                //case SPACE :
                //    hero.run();
                //    break;

                case D:
                    hero.run();
                    hero.posInit();
                    break;

                case Q:
                    hero.run();
                    hero.posInit();
                    break;

                case Z:
                    hero.run();
                    break;

                //case E:
                //    hero.run();
                //    break;
            }
        });

        this.setOnMouseClicked((event) -> {
            System.out.println("Jump");
            hero.jump();
        });
    }


    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long time) {
            myCamera.update(time);
            hero.update(time);
            myBalls.updateboule(time);
            update(time);
            //System.out.println(time);
        }
    };
/*
    public void creationBoule(long time){
        dt2 = time - prevtime2;
        if (dt2 > 80e6) {

            myBalls.init(i);
            myBalls.getImageView();
            //System.out.println(index);
            i++;
            if(i==15){
                myBalls.erase();
                bouleCanExist = true;
            }
            prevtime2 = time;
        }

    }


 */

    public void update(long time) {
        dt = time - prevtime;
        if (time > 0.5e9) {
            prevtime = time;

            this.left.updateleft();
            this.right.updateright();
            this.primaryStage.show();

        }
/*
        if (hero.isShooting() || bouleCanExist) {
            System.out.println("saalut a tous");
            creationBoule(time);
            bouleCanExist = false;
        }

 */

    }
}
