import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StaticThing {
    private double x;
    private double y;
    private ImageView sprite;
    private int oX;
    private int oY;
    private int cropX;
    private int cropY;
    private Camera myCamera;  //la camera va nous etre utile pour update les backgrounds

    //private long dt;
    //private long prevtime =0;

    public StaticThing(String fileName, double x, double y, int oX, int oY, int cropX, int cropY, Camera myCamera){
        this.x = x;
        this.y = y;
        this.oX = oX;
        this.oY = oY;
        this.cropX = cropX;
        this.cropY = cropY;

        this.myCamera = myCamera;

        Image myImage = new Image(fileName);
        this.sprite = new ImageView(myImage);
        sprite.setX(x);
        sprite.setY(y);
        sprite.setViewport(new Rectangle2D(oX,oY,cropX,cropY));
    }


    public ImageView getImageView() {
        return sprite ;
    }

    public void updateleft() {
        this.oX = myCamera.getX();
        this.oY = myCamera.getY();
        this.cropX = myCamera.getCameraCropX();
        this.cropY = myCamera.getHigh();

        this.sprite.setViewport(new Rectangle2D(oX,oY,cropX,cropY));
            //System.out.println(this.x);
    }

    public void updateright() {
        this.oX = 0;
        this.oY = myCamera.getY();
        this.cropX = myCamera.getWidth() - myCamera.getCameraCropX();
        this.cropY = myCamera.getHigh();

        this.sprite.setX(myCamera.getCameraCropX());  //c'est le cropX1 du GameScene
        this.sprite.setViewport(new Rectangle2D(oX,oY,cropX,cropY));
        //System.out.println(myCamera.getCameraCropX());
    }

}


