package icollection;

public class Arr {
    public static void main(String[] args) {
        var arr = new ArrTest();
        // arr.arrTraverse();
        arr.arrOper();
    }
}

class ArrTest {
    public void arrTraverse() {
        int[] ns = { 1, 2, 3, 4 };
        for (int i = 0; i < ns.length; i++) {
            System.out.println(ns[i]);
        }
    }

    public void arrOper() {
        int[] ns = { 1, 2, 3, 4 };
        int i = 0;
        System.out.println(ns[i++]);
        System.out.println(i);
    }
}