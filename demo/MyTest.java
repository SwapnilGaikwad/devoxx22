public class MyTest {
    public static int myInc(int a) {
        return a + 1;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 1_000_000; i++) {
            MyTest.myInc(i++);
        }
    }
}
