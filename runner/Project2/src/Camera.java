public class Camera {
    private int x = 20;
    private int y = 20;
    private int width = 600;
    private int high = 355;
    private double cameraCropX;

    private Hero hero;
    private double v;          //vitesse
    private double a;          //acceleration

    private long dt;
    private long prevtime = 0;


    //La camera fera 600x300 px

    //en hauteur, la camera va de 0 a 45 px pour ne pas dépasser
    public Camera(int coordoneex, int coordonneey, Hero hero) {
        this.hero = hero;

        if (coordonneey > 45) {
            this.y = 45;
            this.x = coordoneex % 800;
        } else if (coordonneey < 0) {
            this.y = 0;
            this.x = coordoneex % 800;
        } else {
            this.y = coordonneey;
            this.x = coordoneex % 800;
        }
    }

    @Override
    public String toString() {
        return (this.x + ", " + this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //largeur de la camera
    public int getWidth() {
        return this.width;
    }

    //hauteur de la camera
    public int getHigh() {
        return this.high;
    }

    //Si la camera est dans le background left, 600
    //sinon, on crop au bon endroit
    public int getCameraCropX() {
        if (800 - x > width) {
            this.cameraCropX = width;
        } else {
            this.cameraCropX = 800 - x;
        }
        return (int) cameraCropX;
    }

    public void update(long time) {

        dt = time - prevtime;
        if (dt > 16e6) {
            this.prevtime = time;

            a = (hero.getX() - this.x - 50) - 1.2 * this.v;
            this.v = this.v + a;
            this.x = (int) (this.x + hero.getV());


            if (this.y > 45) {
                this.y = 45;
                this.x = this.y % 800;
            } else if (this.y < 0) {
                this.y = 0;
                this.x = this.x % 800;
            } else {
                this.x = this.x % 800;
            }

            //System.out.println(this.x);
        }
    }
}

