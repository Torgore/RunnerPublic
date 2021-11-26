public class Hero extends AnimatedThing {

    //private boolean runFaster = false;
    private int i=0;
    private int i1 = 0;
    private double vInit;


    public Hero(String filename, int x, int y) {
        super(filename, x, y, 10, 85, 163 , 85, 105, 6);

        this.vInit = super.v;

    }


    public double getV(){
        return super.v;
    }

    public void run(){
        super.attitude = 0;
        super.longueurAttitude = 6;
    }

    public void jump(){
        if(!super.jump) {
            super.jump = true;
            super.y0 = super.y;
            //super.v0 = super.v;
            super.attitude = 1;
            super.longueurAttitude = 2;
            System.out.println("y0 = " + y0);
        }
    }

    public void shoot(Boule myballs){
        super.shoot = true;
        myballs.attitude = 2;
        super.attitude = 2;
        super.longueurAttitude = 5;
        myballs.setBouleExist(1);
    }

    public void jumpNshoot(){
        super.attitude = 3;
        super.longueurAttitude = 2;
    }

    public void goFaster(){
        if(v<20) {
            super.x = super.x + 2;
            super.sprite.setX(super.x);
            if(i>10) {
                super.v = super.v + 3;
                System.out.println("v : " + v + "and x :" + x);
                i=0;
            }
            i++;
        }
    }

    public void goLower(){
        if(v>5) {
            super.x = super.x - 4;
            super.sprite.setX(super.x);
            if(i1>10) {
                super.v = super.v - 3;
                System.out.println("v : " + v + "and x :" + x);
                i1=0;
            }
            i1++;
        }
    }

    public void posInit(){
        int j=0;                                        //le j étant très faible, c'est un peu inutile
        while(super.v != this.vInit) {
            if (super.v < this.vInit && j>500) {
                super.v = super.v + 1;
                j=0;
            } if(super.v > this.vInit && j>500) {
                super.v = super.v - 1;
                j=0;
            }
            j++;
        }
    }

    public int getX() {
        return super.x;
    }
    public int getY() {
        return super.y;
    }
    public boolean isShooting(){
        return super.shoot;
    }

}
