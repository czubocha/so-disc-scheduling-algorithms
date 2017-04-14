import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorytmy {

    double czas = 0;
    int FCFS;
    int SSTF;
    int SCAN;
    int CSCAN;
    int EDF;

    void FCFS() {
	int licznik = 0;
	int liczbaPustych = 0;
	int poprzedni = 0;

	Collections.sort(Proces.dyskFCFS);
	for (int k = 0; k < Proces.dyskFCFS.size(); k++)
	    if (Proces.dyskFCFS.get(k).czyPusty == true)
		liczbaPustych++;
	while (licznik < liczbaPustych) {
	    Proces.dyskFCFS.remove(0);
	    licznik++;
	}

//	 System.out.println(Proces.dyskFCFS);
	int liczbaPrzejsc = Proces.dyskFCFS.get(0).blok;
	for (int i = 0; i < Proces.dyskFCFS.size(); i++) {
	    // System.out.println("Czas przyjscia:"+Proces.dyskFCFS.get(i).czasZgloszenia +", liczba przejsc :"
	    // +liczbaPrzejsc+", blok :" +Proces.dyskFCFS.get(i).blok);
	    liczbaPrzejsc += Math.abs(Proces.dyskFCFS.get(i).blok - poprzedni);
	    poprzedni = Proces.dyskFCFS.get(i).blok;
	}

	FCFS = liczbaPrzejsc;

    }

    void SSTF() {
	int licznik = 0;
	int iloscZajetychBlokow = 0;
	int liczbaPrzejsc = 0;
	int poprzedni = 0;
	KomparatorBlokow.poprzedni = 0;
	ArrayList<Proces> gotowe = new ArrayList<Proces>();
	KomparatorBlokow komparator = new KomparatorBlokow();
	
	for (int k = 0; k < Proces.dyskSSTF.size(); k++)
	    if (Proces.dyskSSTF.get(k).czyZrobiony == false)
		iloscZajetychBlokow++;
	
	Collections.sort(Proces.dyskSSTF);
	for (int i = 0; i < Proces.dyskSSTF.size(); i++)
	    if (Proces.dyskSSTF.get(i).czyPusty == true) {
		Proces.dyskSSTF.remove(i);
		i--;
	    }
	
	czas = Proces.dyskSSTF.get(0).czasZgloszenia;

	while (licznik < iloscZajetychBlokow) {
	    for (int i = 0; i < Proces.dyskSSTF.size(); i++) {
		if (czas >= Proces.dyskSSTF.get(i).czasZgloszenia && Proces.dyskSSTF.get(i).czyZrobiony == false) {
		    gotowe.add(Proces.dyskSSTF.get(i));
		    Proces.dyskSSTF.get(i).czyZrobiony = true;
		}
	    }

	    if (gotowe.isEmpty() == false) {
		Collections.sort(gotowe, komparator);
		liczbaPrzejsc += Math.abs(gotowe.get(0).blok - poprzedni);
//		 System.out.println("Czas :"+czas +", liczba przejsc :" +liczbaPrzejsc+", poprzedni :" + poprzedni + " " + gotowe.get(0));
//		 System.out.println(gotowe);
		czas += Math.abs(gotowe.get(0).blok - poprzedni);
		KomparatorBlokow.poprzedni = gotowe.get(0).blok;
		poprzedni = gotowe.get(0).blok;
		gotowe.remove(0);
		licznik++;
	    } else
		czas++;
	}
	SSTF = liczbaPrzejsc;
    }

    void SCAN() {
	int licznik = 0;
	int iloscZajetychBlokow = 0;
	for (int k = 0; k < Proces.dyskSCAN.size(); k++)
	    if (Proces.dyskSCAN.get(k).czyZrobiony == false)
		iloscZajetychBlokow++;
	int liczbaPrzejsc = 0;
	int poprzedni = 0;
	int wynik = 0;
	int ktorePrzejscie = 1;
	boolean czySkonczone = false;
	Collections.sort(Proces.dyskSCAN);
	czas = Proces.dyskSCAN.get(iloscZajetychBlokow).czasZgloszenia;
	Collections.sort(Proces.dyskSCAN, new Comparator<Proces>() {
	    @Override
	    public int compare(Proces p1, Proces p2) {
		return Integer.compare(p1.blok, p2.blok);
	    }
	});
	while (licznik < iloscZajetychBlokow) {
	    for (int i = 0; i < Proces.dyskSCAN.size(); i++) {
		if (czas >= Proces.dyskSCAN.get(i).czasZgloszenia && Proces.dyskSCAN.get(i).czyZrobiony == false) {
		    // liczbaPrzejsc+=Proces.dysk.get(i).blok-poprzedni;
		    // poprzedni=Proces.dysk.get(i).blok;
		    Proces.dyskSCAN.get(i).czyZrobiony = true;
		    licznik++;
//		     System.out.println("Czas przyjscia :"
//		     +Proces.dyskSCAN.get(i).czasZgloszenia+", blok: "+Proces.dyskSCAN.get(i).blok
//		     +", aktualny czas: " +czas+", Petla :"+ktorePrzejscie+", Liczba przejsc :"+liczbaPrzejsc);
		}
		if (licznik == iloscZajetychBlokow && czySkonczone == false) {
		    wynik = liczbaPrzejsc;
		    czySkonczone = true;
		}
		liczbaPrzejsc++;
		czas++;
	    }
	    ktorePrzejscie++;
	    for (int j = Proces.dyskSCAN.size() - 1; j > 0; j--) {
		if (czas >= Proces.dyskSCAN.get(j).czasZgloszenia && Proces.dyskSCAN.get(j).czyZrobiony == false) {
		    // liczbaPrzejsc+=poprzedni-Proces.dysk.get(j).blok;
		    // poprzedni=Proces.dysk.get(j).blok;
		    Proces.dyskSCAN.get(j).czyZrobiony = true;
		    licznik++;
//		     System.out.println("Czas przyjscia :"
//		     +Proces.dyskSCAN.get(j).czasZgloszenia+", blok: "+Proces.dyskSCAN.get(j).blok
//		     +", aktualny czas: " +czas+", Petla :"+ktorePrzejscie+", Liczba przejsc :"+liczbaPrzejsc);
		}
		if (licznik == iloscZajetychBlokow && czySkonczone == false) {
		    wynik = liczbaPrzejsc;
		    czySkonczone = true;
		}
		liczbaPrzejsc++;
		czas++;
	    }
	    ktorePrzejscie++;
	}
	SCAN = wynik;
    }

    void CSCAN() {
	// System.out.println("Algorytm CSCAN");
	int licznik = 0;
	int iloscZajetychBlokow = 0;
	int ktorePrzejscie = 1;
	for (int k = 0; k < Proces.dyskCSCAN.size(); k++)
	    if (Proces.dyskCSCAN.get(k).czyZrobiony == false)
		iloscZajetychBlokow++;
	int liczbaPrzejsc = 0;
	// int poprzedni=0;
	int wynik = 0;
	boolean czySkonczone = false;
	Collections.sort(Proces.dyskCSCAN);
	czas = Proces.dyskCSCAN.get(iloscZajetychBlokow).czasZgloszenia;
	Collections.sort(Proces.dyskCSCAN, new Comparator<Proces>() {
	    @Override
	    public int compare(Proces p1, Proces p2) {
		return Integer.compare(p1.blok, p2.blok);
	    }
	});
	while (licznik < iloscZajetychBlokow) {
	    for (int i = 0; i < Proces.dyskCSCAN.size(); i++) {

		if (czas >= Proces.dyskCSCAN.get(i).czasZgloszenia && Proces.dyskCSCAN.get(i).czyZrobiony == false) {
		    // liczbaPrzejsc+=Proces.dysk.get(i).blok-poprzedni;
		    // poprzedni=Proces.dysk.get(i).blok;
		    Proces.dyskCSCAN.get(i).czyZrobiony = true;
		    licznik++;
//		     System.out.println("Czas przyjscia :"
//		     +Proces.dyskCSCAN.get(i).czasZgloszenia+", blok: "+Proces.dyskCSCAN.get(i).blok
//		     +", aktualny czas: " +czas+", Petla :"+ktorePrzejscie+", Liczba przejsc :"+liczbaPrzejsc);
		}
		if (licznik == iloscZajetychBlokow && czySkonczone == false) {
		    wynik = liczbaPrzejsc;
		    czySkonczone = true;
		}
		if (i == Proces.dyskCSCAN.size() - 1) {
		    // liczbaPrzejsc+=10-poprzedni+10;
//		     poprzedni=0;
		    liczbaPrzejsc += Proces.dyskCSCAN.size();
		    czas += Proces.dyskCSCAN.size() * 0.1;
		}

		liczbaPrzejsc++;
		czas++;
	    }
	    ktorePrzejscie++;
	}
	CSCAN = wynik;
    }

    void EDF() {
	int licznik = 0;
	int iloscZajetychBlokow = 0;
	int liczbaPrzejsc = 0;
	int poprzedni = 0;
//	int iloscUsunietych = 0;
	ArrayList<Proces> gotowe = new ArrayList<Proces>();
	KomparatorEDF komparator = new KomparatorEDF();

	for (int k = 0; k < Proces.dyskEDF.size(); k++)
	    if (Proces.dyskEDF.get(k).czyZrobiony == false)
		iloscZajetychBlokow++;
	
	Collections.sort(Proces.dyskEDF, new Comparator<Proces>() {
	    @Override
	    public int compare(Proces p1, Proces p2) {
		return Integer.compare(p1.blok, p2.blok);
	    }
	});

	while (licznik < iloscZajetychBlokow) {
	    for (int i = 0; i < Proces.dyskEDF.size(); i++) {
		if (czas >= Proces.dyskEDF.get(i).czasZgloszenia && Proces.dyskEDF.get(i).czyZrobiony == false) {
		    gotowe.add(Proces.dyskEDF.get(i));
		    Proces.dyskEDF.get(i).czyZrobiony = true;
		}
	    }
//	     System.out.println(gotowe);
	    if (gotowe.isEmpty() == false) {
		Collections.sort(gotowe, komparator);
//		if (gotowe.get(0).deadline < czas + Math.abs(gotowe.get(0).blok - poprzedni)) {
		    liczbaPrzejsc += Math.abs(gotowe.get(0).blok - poprzedni);
		    czas += Math.abs(gotowe.get(0).blok - poprzedni);
		    poprzedni = gotowe.get(0).blok;
		    gotowe.remove(0);
		    licznik++;
//		    iloscUsunietych++;

//		} else {
//		    liczbaPrzejsc += Math.abs(gotowe.get(0).blok - poprzedni);
		    // System.out.println("Czas :"+czas +", liczba przejsc :" +liczbaPrzejsc+", poprzedni :"
		    // +poprzedni);
		    // System.out.println(gotowe);
//		    czas += Math.abs(gotowe.get(0).blok - poprzedni);
//		    poprzedni = gotowe.get(0).blok;
//		    gotowe.remove(0);
//		    licznik++;
//		}
	    } else
		czas++;
	}
	EDF = liczbaPrzejsc;
//	System.out.println(iloscUsunietych);
    }

    void wyniki() {
	System.out.println("Rozmiar dysku: " + Proces.rozmiarDysku + ", Ilosc procesow: " + Proces.iloscProcesow);
	System.out.println("FCFS: " + FCFS + ", SSTF: " + SSTF + ", SCAN: " + SCAN + ", CSCAN: " + CSCAN + ", EDF: "
		+ EDF);
    }
}
