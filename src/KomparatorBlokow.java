import java.util.Comparator;

public class KomparatorBlokow implements Comparator<Proces> {
    static int poprzedni;

    public int compare(Proces p1, Proces p2) {
	return Integer.compare(Math.abs(p1.blok - poprzedni), Math.abs(p2.blok - poprzedni));
    }
}
