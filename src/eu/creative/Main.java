package eu.creative;

public class Main {

    /**
     * @user KT
     * @email megoldas@gmail.com
     * @param args
     */
    public static void main(String[] args) {
	    Telepules egy = new Telepules("Nyírbátor;Tolna;6 072;63,57");
        System.out.println(egy);
    }
}

class Telepules{
    String nev;
    String megye;
    int lakos;
    double terulet;

    public Telepules(String nev, String megye, int lakos, double terulet) {
        this.nev = nev;
        this.megye = megye;
        this.lakos = lakos;
        this.terulet = terulet;
    }

    public Telepules(String sor) {
        String [] reszek = sor.split(";");
        this.nev = reszek[0];
        this.megye = reszek[1];
        this.lakos = Integer.parseInt(reszek[2].replaceAll(" ",""));
        this.terulet = Double.parseDouble(reszek[3].replaceAll(",","."));
    }

    @Override
    public String toString() {
        return "Telepules{" +
                "nev='" + nev + '\'' +
                ", megye='" + megye + '\'' +
                ", lakos=" + lakos +
                ", terulet=" + terulet +
                '}';
    }
}
