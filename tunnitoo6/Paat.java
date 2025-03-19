package Soidukid;

public class Paat extends Soiduk {
    private double syvis;

    @Override
    public int getHind() {
        return hind;
    }

    private int hind;

    public double getSyvis() {
        return syvis;
    }

    public void setSyvis(double syvis) {
        this.syvis = syvis;
    }


    public Paat() {
    }

    public Paat(String number) {
        setNumber(number);
    }

    public void setHind() {
        if (this.getSyvis() < 0.5) {
            this.hind = 100;
        } else if (this.getSyvis() > 0.5) {
            if (this.getMaxKiirus() < 10) {
                this.hind = 300;
            } else {
                this.hind = 1500;
            }
        }
    }

    public void show() {
        System.out.println("Paadi number: " + this.getNumber() +
                        " Syvis: " + this.getSyvis()+
                        " Kiirus: " + this.getSyvis() +
                " Hind: " + this.getHind()
                );
    }
}
