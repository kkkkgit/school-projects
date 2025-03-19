import Soidukid.*;

import java.util.ArrayList;
import java.util.List;

public class Palmik_T6 {
    public static void main(String[] args) {
        Auto auto1 = new Auto("AUH192");
        Auto auto2 = new Auto();
        Paat paat1 = new Paat("Kala1");
        Paat paat2 = new Paat();

        auto2.setNumber("MEW669");
        paat2.setNumber("Merekuningas");
        auto1.setMaxKiirus(60);
        auto2.setMaxKiirus(30);
        paat1.setMaxKiirus(30);
        paat2.setMaxKiirus(5);
        paat1.setSyvis(0.3);
        paat2.setSyvis(1);
        auto1.setHind();
        auto2.setHind();
        paat1.setHind();
        paat2.setHind();

        ArrayList<Integer> soidukihinnad = new ArrayList<>();
        soidukihinnad.add(auto1.getHind());
        soidukihinnad.add(auto2.getHind());
        soidukihinnad.add(paat1.getHind());
        soidukihinnad.add(paat2.getHind());

        auto1.show();
        auto2.show();
        paat1.show();
        paat2.show();
    }
}
