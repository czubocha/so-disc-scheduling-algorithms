public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {

	Algorytmy a = new Algorytmy();
	Proces.tworzenieDysku(20);
	Proces.tworzenieProcesow(10, 20, 10);
	Proces.kopiowanie();
	// Proces.wydrukujListe();
	System.out.println();
	a.EDF();
	a.FCFS();
	a.SSTF();
	a.SCAN();
	System.out.println();
	a.CSCAN();
	System.out.println();
	a.wyniki();

	Proces.tworzenieDysku(50);
	Proces.tworzenieProcesow(40, 60, 30);
	Proces.kopiowanie();
	// Proces.wydrukujListe();
	System.out.println();
	a.EDF();
	a.FCFS();
	a.SSTF();
	a.SCAN();
	a.CSCAN();
	System.out.println();
	a.wyniki();

	Proces.tworzenieDysku(100);
	Proces.tworzenieProcesow(10, 100, 100);
	Proces.kopiowanie();
	// Proces.wydrukujListe();
	System.out.println();
	a.EDF();
	a.FCFS();
	a.SSTF();
	a.SCAN();
	a.CSCAN();
	System.out.println();
	a.wyniki();

	Proces.tworzenieDysku(200);
	Proces.tworzenieProcesow(100, 200, 100);
	Proces.kopiowanie();
	// Proces.wydrukujListe();
	System.out.println();
	a.EDF();
	a.FCFS();
	a.SSTF();
	a.SCAN();
	a.CSCAN();
	System.out.println();
	a.wyniki();

	Proces.tworzenieDysku(500);
	Proces.tworzenieProcesow(100, 500, 250);
	Proces.kopiowanie();
	// Proces.wydrukujListe();
	System.out.println();
	a.EDF();
	a.FCFS();
	a.SSTF();
	a.SCAN();
	a.CSCAN();
	System.out.println();
	a.wyniki();
	// Proces.wydrukujListe();

	Proces.tworzenieDysku(1000);
	Proces.tworzenieProcesow(700, 100, 1000);
	Proces.kopiowanie();
	// Proces.wydrukujListe();
	System.out.println();
	a.EDF();
	a.FCFS();
	a.SSTF();
	a.SCAN();
	a.CSCAN();
	System.out.println();
	a.wyniki();
    }

}
