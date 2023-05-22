public class Main {
    public static void main(String[] args) {
        Drzewo<Integer> d1 = new Drzewo<Integer>(new WierzcholekDrzewa( 5,null,null));

        d1.dodajBST(4);
        d1.dodajBST(3);
        d1.dodajBST(2);
        d1.dodajBST(6);
        System.out.println("Czy avl - " + d1.sprawdzCzyZrownowazone());

        Drzewo<Integer> d2 = new Drzewo<Integer>(new WierzcholekDrzewa( 5,null,null));
        d2.dodajAVL(4);
        d2.dodajAVL(3);
        d2.dodajAVL(2);
        d2.dodajAVL(6);
        System.out.println("czy avl - " + d2.sprawdzCzyZrownowazone());
    }
}
