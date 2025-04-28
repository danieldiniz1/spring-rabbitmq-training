package br.com.sh.appanalisecredito.util;

import java.util.Random;

public class ValueRandom {

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static Integer randomInteger(int min, int max) {
        return new Random().nextInt(min,max);
    }
}
