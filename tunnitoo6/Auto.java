package Soidukid;

public class Auto extends Soiduk {
    @Override
    public int getHind() {
        return hind;
    }

    private int hind;

    public Auto(String number) {
        this.setNumber(number);
    }

    public Auto() {
    }

    public void setHind() {
        if (this.getMaxKiirus() < 50 ) {
            this.hind = 400;
        } else if (this.getMaxKiirus() >= 50 ) {
            this.hind = 1000;
        }

    }

    public void show() {
        System.out.println("Auto number: " + this.getNumber() +
                " Max Kiirus: " + this.getMaxKiirus() +
                " Hind: " + this.getHind());
    }
}
