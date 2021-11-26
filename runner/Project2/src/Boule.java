import javafx.geometry.Rectangle2D;

public class Boule extends AnimatedThing {
    private Hero hero;
    private long dt;
    private long prevtime = 0;
    private boolean bouleExist = false;
    private int i = 3;


    public Boule(Hero hero) {
        super("heros.png", -100, -100, 0,
                85, 163, 85, 105, 7);
        this.hero = hero;
        super.index = 6;
    }

    public void init(int i){            //permet de placer la boule
        super.x = hero.getX() + i*85;
        super.y = hero.getY();
    }

    public void erase(){
        super.x = -10;
        super.y = -10;
    }

    public void setBouleExist(int c){
        if(c==0){
            bouleExist = false;
        } else {
            bouleExist = true;
        }
    }

    public void updateboule(long time){
        if(bouleExist){
            dt = time - prevtime;
            if(dt>4e6){
                if(i<50) {                                    //i = durée de vie de la boule (en frame)
                    super.x = hero.getX() + i * 10;
                    super.y = hero.getY();
                    super.getImageView();
                    i++;
                } else {
                    setBouleExist(0);
                    this.erase();
                    i = 3;
                }
                prevtime = time;
            }
        }
    }

}
