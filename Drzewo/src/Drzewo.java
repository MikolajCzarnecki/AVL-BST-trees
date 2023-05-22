public class Drzewo<T extends Comparable>{
    private WierzcholekDrzewa<T> korzen;


    public Drzewo(WierzcholekDrzewa korzen) {
        this.korzen = korzen;
    }

    public Boolean czyWartoscWDrzewie(T szukana) {
        return this.korzen.czyElement(szukana);
    }
    public WierzcholekDrzewa pierwszyNiezrownowazony(T wartosc) {
        return this.korzen.znajdzPierwszyNiezrownowazony(wartosc);
    }

    public WierzcholekDrzewa getKorzen() {
        return korzen;
    }

    public void dodajBST(T nowaWartosc) {
        korzen.dodajDalejBST(nowaWartosc);
    }

    public void dodajAVL(T nowaWartosc) {
        this.dodajBST(nowaWartosc);
        if (!this.sprawdzCzyZrownowazone()) this.wywazDrzewo(nowaWartosc);
    }

    public Boolean sprawdzCzyZrownowazone() {
        return this.korzen.czyPoddrzewaAVL();
    }

    public void wywazDrzewo(T nowaWartosc) {
        WierzcholekDrzewa pierwszyRotacja = this.pierwszyNiezrownowazony(nowaWartosc);
        WierzcholekDrzewa drugiRotacja;
        int[] jakiCase = new int[2];

        if (pierwszyRotacja.getWartoscElementu().compareTo(nowaWartosc) < 0) {
                jakiCase[0] = 1;
                drugiRotacja = pierwszyRotacja.getPrawySyn();
        } else {
            jakiCase[0] = 0;
            drugiRotacja = pierwszyRotacja.getLewySyn();
        }

        if (drugiRotacja.getWartoscElementu().compareTo(nowaWartosc) < 0) {
            jakiCase[1] = 1;
        } else {
            jakiCase[1] = 0;
        }

        if (jakiCase[0] == 0 && jakiCase[1] == 0) {
            pierwszyRotacja.pojedynczaRotacjaLewo();
        } else if (jakiCase[0] == jakiCase[1]) {
            pierwszyRotacja.pojedynczaRotacjaPrawo();
        } else if (jakiCase[0] == 1) {
            pierwszyRotacja.rotacjaPrawoLewo();
        } else {
            pierwszyRotacja.rotacjaLewoPrawo();
        }
    }
}
