import java.util.Random;

public class MyJITDemo {
    private static int myMult(final int a) {
        return a * 16;
    }

    public static int myInc(int a) {
        a = myMult(a);
        return a + 1;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 1_000_000; i++) {
            MyJITDemo.myInc(i);
        }
    }
}