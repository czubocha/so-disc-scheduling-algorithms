import java.util.ArrayList;
import java.util.Random;

public class Proces implements Comparable<Proces>, Cloneable {
    int czasZgloszenia;
    int deadline;
    int blok;
    static int rozmiarDysku;
    boolean czyZrobiony;
    boolean czyMaDeadline;
    boolean czyPusty;
    static int iloscProcesow;

    Random r = new Random();

    static ArrayList<Proces> dysk = new ArrayList<Proces>();
    static ArrayList<Proces> dyskFCFS = new ArrayList<Proces>();
    static ArrayList<Proces> dyskSSTF = new ArrayList<Proces>();
    static ArrayList<Proces> dyskSCAN = new ArrayList<Proces>();
    static ArrayList<Proces> dyskCSCAN = new ArrayList<Proces>();
    static ArrayList<Proces> dyskEDF = new ArrayList<Proces>();

    static ArrayList<Proces> tworzenieDysku(int rozmiar) {
	rozmiarDysku = rozmiar;
	for (int i = 1; i < rozmiarDysku + 1; i++) {
	    dysk.add(new Proces(i));
	}
	return dysk;
    }
    
    static void tworzenieProcesow(int ilosc, int czaszgloszenia, int deadline) {
	iloscProcesow = ilosc;
	for (int i = 0; i < ilosc; i++)
	    new Proces(czaszgloszenia, deadline);
    }

    static void kopiowanie() throws CloneNotSupportedException {
	for (int i = 0; i < dysk.size(); i++) {
	    dyskFCFS.add((Proces) dysk.get(i).clone());
	    dyskSSTF.add((Proces) dysk.get(i).clone());
	    dyskSCAN.add((Proces) dysk.get(i).clone());
	    dyskCSCAN.add((Proces) dysk.get(i).clone());
	    dyskEDF.add((Proces) dysk.get(i).clone());
	}
    }

    Proces(int czaszgloszenia, int czasdeadline) {
	czasZgloszenia = r.nextInt(czaszgloszenia);
	int zmiennaDeadline = r.nextInt(5);
	if (zmiennaDeadline == 0) {
	    deadline = czasZgloszenia + r.nextInt(czasdeadline) + czasdeadline;
	    czyMaDeadline = true;
	} else {
	    deadline = 1000000000;
	    czyMaDeadline = false;
	}
	int zmiennaBlok = r.nextInt(rozmiarDysku);
	while (dysk.get(zmiennaBlok).czasZgloszenia != -1)
	    zmiennaBlok = r.nextInt(rozmiarDysku);
	blok = zmiennaBlok;
	czyZrobiony = false;
	czyPusty = false;
	dysk.set(zmiennaBlok, this);
    }

    Proces(int x) {
	czasZgloszenia = -1;
	deadline = -1;
	blok = x;
	czyZrobiony = true;
	czyMaDeadline = false;
	czyPusty = true;
    }

    public String toString() {
	return "Czas zgloszenia: " + czasZgloszenia + ", blok: " + blok + ", " + ", deadline: " + deadline;
    }

    public int compareTo(Proces p) {
	return Integer.compare(czasZgloszenia, p.czasZgloszenia);
    }

    public int compareTo2(Proces p) {
	return Integer.compare(blok, p.blok);
    }

    static void wydrukujListe() {
	for (int j = 0; j < Proces.dysk.size(); j++)
	    System.out.println(Proces.dysk.get(j));
    }

}
