import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class AnimatedThing {
    protected int x;                   //coordonnées image animée dans la scène
    protected int y;
    protected ImageView sprite;           //selection de l'image animée
    protected int attitude = 0;               //index correspondant à l'attitude de l'objet animé
    protected int deltaAttitude;          //sert à trouver l'origine y de l'image à afficher
    protected int frameY;              //origine y de l'image à afficher
    protected int frameX;                  //origine x de l'image à afficher
    protected int deltaFrame;             //sert à trouver l'origine x de la frame à afficher
    protected int index = 0;                  //avancée de l'animation
    protected int longueurObjet;
    protected int largeurObjet;
    protected int longueurAttitude;
    protected double v;         //vitesse de l'objet
    protected boolean jump = false;
    protected boolean shoot = false;
    protected int y0;
    protected double v0 = 20;
    protected long t = 0;           //ne pas confondre avec dt, t correspond au temps écoulé depuis le début du saut
    private int i=0;

    private long prevtime = 0;
    private long dt;            //temps relatif utile aux boucles temporelles pour reinitialiser la frame.

    public AnimatedThing(String filename, int x, int y, double v, int deltaFrame,
                         int deltaAttitude, int longueurObjet, int largeurObjet, int longueurAttitude){
        this.x = x;
        this.y = y;
        this.longueurAttitude = longueurAttitude;
        this.v = v;

        this.longueurObjet = longueurObjet;
        this.largeurObjet = largeurObjet;
        this.deltaAttitude = deltaAttitude;
        this.deltaFrame = deltaFrame;

        Image spriteSheet = new Image(filename);
        ImageView sprite = new ImageView(spriteSheet);
        this.sprite = sprite;

    }

    public void addX(int a){
        this.x = this.x + a;
    }

    public ImageView getImageView(){

        this.frameX = (index%longueurAttitude)*deltaFrame;
        this.frameY = attitude*deltaAttitude;

        sprite.setX(x);
        sprite.setY(y);
        sprite.setViewport(new Rectangle2D(frameX, frameY , longueurObjet, largeurObjet));
        return this.sprite;
    }


    public void update(long time) {
        this.dt = time - prevtime;
        if(jump) {
            y = (int) ((t * t) - v0 * t + y0);
            System.out.println("y : " + y);

            this.t = (long) (t + 1);
            System.out.println("t : " + t);


            if (t == v0) this.index = this.index + 1;

            if (dt > 8e6) {

                this.frameX = (index % longueurAttitude) * deltaFrame;
                this.frameY = attitude * deltaAttitude;

                sprite.setViewport(new Rectangle2D(frameX, frameY, longueurObjet, largeurObjet));
                sprite.setY(y);

                prevtime = time;
            }

            if ((int) (((t + 1) * (t + 1)) - v0 * (t + 1) + y0) >= y0) {          //quand le héros atteind le sol
                this.t = 0;
                this.y = y0;
                sprite.setY(y);
                jump = false;
                prevtime = time;
                this.attitude = 0;
                this.longueurAttitude = 6;
            }

        }
        if(shoot){

                if (dt > 80e6) {
                    this.index = this.index + 1;

                    this.frameX = (index % longueurAttitude) * deltaFrame;
                    this.frameY = attitude * deltaAttitude;

                    sprite.setViewport(new Rectangle2D(frameX, frameY, longueurObjet, largeurObjet));
                    i++;                //pour que l'animation shoot ne se produise qu'une seule fois
                    if(i==longueurAttitude) {
                        shoot = false;
                        i=0;
                        this.attitude = 0;                         //refait courrir le hero
                        this.longueurAttitude = 6;
                    }
                    //System.out.println(index);
                    prevtime = time;
                }
            }


        else {

            if (dt > 80e6) {

                this.index = this.index + 1;

                this.frameX = (index%longueurAttitude)*deltaFrame;
                this.frameY = attitude*deltaAttitude;

                sprite.setViewport(new Rectangle2D(frameX, frameY , longueurObjet, largeurObjet));

                //System.out.println(index);
                prevtime = time;
            }
        }

    }
}
