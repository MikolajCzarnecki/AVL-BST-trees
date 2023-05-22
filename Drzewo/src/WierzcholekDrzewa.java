import java.lang.*;
import java.lang.Math;
public class WierzcholekDrzewa <T extends Comparable>{
    private T wartoscElementu;
    private WierzcholekDrzewa lewySyn;
    private WierzcholekDrzewa prawySyn;

    public WierzcholekDrzewa(
            T wartoscElementu, WierzcholekDrzewa lewySyn, WierzcholekDrzewa prawySyn
    ) {
        this.wartoscElementu = wartoscElementu;
        this.lewySyn = lewySyn;
        this.prawySyn = prawySyn;
    }

    public T getWartoscElementu() {             //TODO ogarnac czemu trzeba castowac
        return wartoscElementu;
    }

    public void setWartoscElementu(T wartoscElementu) {
        this.wartoscElementu = wartoscElementu;
    }

    public WierzcholekDrzewa getLewySyn() {
        return lewySyn;
    }

    public void setLewySyn(WierzcholekDrzewa lewySyn) {
        this.lewySyn = lewySyn;
    }

    public WierzcholekDrzewa getPrawySyn() { return prawySyn;
    }

    public void setPrawySyn(WierzcholekDrzewa prawySyn) {
        this.prawySyn = prawySyn;
    }

    public void dodajDalejBST(T nowaWartosc) {
        int wynikPorownania = this.wartoscElementu.compareTo(nowaWartosc);
        if (wynikPorownania < 0) {
            if (this.prawySyn == null) {
                this.dodajNowyLisc(LR.PRAWY ,nowaWartosc);
            } else {
                this.prawySyn.dodajDalejBST(nowaWartosc);
            }
        } else {
            if (this.lewySyn == null) {
                this.dodajNowyLisc(LR.LEWY ,nowaWartosc);
            } else {
                this.lewySyn.dodajDalejBST(nowaWartosc);
            }
        }
    }

    public void dodajNowyLisc(LR LewyCzyPrawy, T nowaWartosc) {
        if (LewyCzyPrawy == LR.LEWY) {
            this.setLewySyn(new WierzcholekDrzewa(nowaWartosc, null, null));
        } else {
            this.setPrawySyn(new WierzcholekDrzewa(nowaWartosc, null, null));
        }
    }
    public int wysokosc(){
        if (this.lewySyn == null && this.prawySyn == null) {
            return 1;
        } else if (this.prawySyn == null) {
            return this.lewySyn.wysokosc() + 1;
        } else if (this.lewySyn == null) {
            return this.prawySyn.wysokosc() + 1;
        }
        else return Math.max(this.prawySyn.wysokosc(), this.lewySyn.wysokosc()) + 1;
    }

    public boolean czyPoddrzewaAVL() {
        int wysLewy = 0;
        Boolean czyLewyAVL = true;
        if (this.lewySyn != null) {
            wysLewy = this.lewySyn.wysokosc();
            czyLewyAVL = this.lewySyn.czyPoddrzewaAVL();
        }
//        System.out.println(wysLewy);

        int wysPrawy = 0;
        Boolean czyPrawyAVL = true;
        if (this.prawySyn != null) {
            wysPrawy = this.prawySyn.wysokosc();
            czyPrawyAVL = this.prawySyn.czyPoddrzewaAVL();
        }
//        System.out.println(wysPrawy);

        Boolean czyObecnyAVL = false;
        if (wysPrawy + 1 >= wysLewy && wysLewy + 1 >= wysPrawy) {
            czyObecnyAVL = true;
        }

        return(czyLewyAVL && czyPrawyAVL && czyObecnyAVL);
    }

    public Boolean czyElement(T szukany) {
        int wynikPorownania = this.wartoscElementu.compareTo(szukany);
        if (wynikPorownania == 0) {
            return true;
        } else if (wynikPorownania < 0) {
            if (this.prawySyn == null) return false;
            else return this.prawySyn.czyElement(szukany);
        } else {
            if (this.lewySyn == null) return false;
            else return this.lewySyn.czyElement(szukany);
        }
    }
    public WierzcholekDrzewa znajdzPierwszyNiezrownowazony(T wartosc) {
        int wynikPorownania = this.wartoscElementu.compareTo(wartosc);
        if (wynikPorownania < 0) {
            if (this.prawySyn.czyPoddrzewaAVL()) {
                return this;
            } else {
                return this.prawySyn.znajdzPierwszyNiezrownowazony(wartosc);
            }
        } else {
            if (this.lewySyn.czyPoddrzewaAVL()) {
                return this;
            } else {
                return this.lewySyn.znajdzPierwszyNiezrownowazony(wartosc);
            }
        }
    }

    void pojedynczaRotacjaLewo() {
        WierzcholekDrzewa drugiRotacja = this.getLewySyn();
        WierzcholekDrzewa trzeciRotacja = drugiRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo4 = this.getPrawySyn();
        WierzcholekDrzewa poddrzewo3 = drugiRotacja.getPrawySyn();
        WierzcholekDrzewa poddrzewo2 = trzeciRotacja.getPrawySyn();
        WierzcholekDrzewa poddrzewo1 = trzeciRotacja.getLewySyn();

        this.setPrawySyn(new WierzcholekDrzewa(this.getWartoscElementu(), poddrzewo3, poddrzewo4));
        this.setWartoscElementu((T) drugiRotacja.getWartoscElementu());
        drugiRotacja.setWartoscElementu(trzeciRotacja.getWartoscElementu());
        drugiRotacja.setLewySyn(poddrzewo1);
        drugiRotacja.setPrawySyn(poddrzewo2);
    }

    public void pojedynczaRotacjaPrawo() {
        WierzcholekDrzewa drugiRotacja = this.getPrawySyn();
        WierzcholekDrzewa trzeciRotacja = drugiRotacja.getPrawySyn();
        WierzcholekDrzewa poddrzewo4 = trzeciRotacja.getPrawySyn();
        WierzcholekDrzewa poddrzewo3 = trzeciRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo2 = drugiRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo1 = this.getLewySyn();

        this.setPrawySyn(new WierzcholekDrzewa(this.getWartoscElementu(), poddrzewo3, poddrzewo4));
        this.setWartoscElementu((T) drugiRotacja.getWartoscElementu());
        drugiRotacja.setWartoscElementu(trzeciRotacja.getWartoscElementu());
        drugiRotacja.setLewySyn(poddrzewo1);
        drugiRotacja.setPrawySyn(poddrzewo2);
    }

    public void rotacjaLewoPrawo(){
        WierzcholekDrzewa drugiRotacja = this.getLewySyn();
        WierzcholekDrzewa trzeciRotacja = drugiRotacja.getPrawySyn();
        WierzcholekDrzewa poddrzewo1 = drugiRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo2 = trzeciRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo3 = trzeciRotacja.getPrawySyn();

        WierzcholekDrzewa nowyTrzeciRotacja = new WierzcholekDrzewa(null, poddrzewo1, poddrzewo2);
        nowyTrzeciRotacja.setWartoscElementu(drugiRotacja.getWartoscElementu());
        this.setLewySyn(new WierzcholekDrzewa(trzeciRotacja.getWartoscElementu(), nowyTrzeciRotacja, poddrzewo3));
        this.pojedynczaRotacjaLewo();
    }

    public void rotacjaPrawoLewo(){
        WierzcholekDrzewa drugiRotacja = this.getPrawySyn();
        WierzcholekDrzewa trzeciRotacja = drugiRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo2 = trzeciRotacja.getLewySyn();
        WierzcholekDrzewa poddrzewo3 = trzeciRotacja.getPrawySyn();
        WierzcholekDrzewa poddrzewo4 = drugiRotacja.getPrawySyn();

        WierzcholekDrzewa nowyTrzeciRotacja = new WierzcholekDrzewa(null, poddrzewo3, poddrzewo4);
        nowyTrzeciRotacja.setWartoscElementu(drugiRotacja.getWartoscElementu());
        this.setPrawySyn(new WierzcholekDrzewa(trzeciRotacja.getWartoscElementu(), poddrzewo2, nowyTrzeciRotacja));
        this.pojedynczaRotacjaPrawo();
    }
}
