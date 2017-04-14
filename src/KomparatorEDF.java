import java.util.Comparator;

public class KomparatorEDF implements Comparator<Proces> {
    public int compare(Proces p1, Proces p2) {
	if (p1.czyMaDeadline == true && p2.czyMaDeadline == true)
	    return Integer.compare(p1.deadline, p2.deadline);
	else if (p1.czyMaDeadline == true && p2.czyMaDeadline == false)
	    return -1;
	else if (p1.czyMaDeadline == false && p2.czyMaDeadline == true)
	    return 1;
	else
	    return Integer.compare(p1.czasZgloszenia, p2.czasZgloszenia);
    }
    // public int compare(Proces p1, Proces p2){
    // return Integer.compare(p1.deadline, p2.deadline);
    // }

}
