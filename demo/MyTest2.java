public class MyTest2 {
    public static int myInc(int a) {
        if(a < 0){
            return a + 5;
        }
        return a + 1;
    }

    public static void main(String[] args) {
        int i = -3;
         for(int j = 0; j < 1_000_000; j++) {
            MyTest2.myInc(i++);
            if(i > 100)
                i = -10;
        }
    }
}
