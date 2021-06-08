package eu.creative;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * @user KT
     * @email megoldas@gmail.com
     * @param args
     */
    public static void main(String[] args) {
	    List<Telepules> telepulesek = feltolt("varosok.txt");
        System.out.println("1. feladat - osztály kész");
        System.out.println("\n2. feladat - adatbeolvasás kész");
        System.out.println("\n3. feladat");
        System.out.println("Magyarország városainak a száma "+telepulesek.size()+" darab.");

        System.out.println("\n4. feladat");
        Scanner beSzoveg = new Scanner(System.in);
        System.out.println("Adj meg egy megye nevet");
        String megye = beSzoveg.nextLine();
        adottMegyeTelepulesei(telepulesek, megye);

        System.out.println("\n5. feladat");
        int limatAlattiDarab = 0;
        int limit = 5000;
        Telepules limitAlattiMaxVaros = null;
        for( Telepules varos : telepulesek)
            if(varos.limitAlatt(limit)) {
                limatAlattiDarab++;
                if( limitAlattiMaxVaros != null){
                    if( varos.getLakos() > limitAlattiMaxVaros.getLakos())
                        limitAlattiMaxVaros = varos;
                }
                else
                    limitAlattiMaxVaros = varos;
            }
        System.out.println("A "+limit+" lakos alatti városok száma "+limatAlattiDarab+" darab.");
        System.out.println("Ezek közül a legnagyobb: " + limitAlattiMaxVaros.nevLakossal());

        System.out.println("\n6. feladat");
        boolean nincsMegye = false;
        for( Telepules varos : telepulesek)
            if( varos.nincsMegye()) {
                nincsMegye = true;
                System.out.println(varos);
            }
        if( !nincsMegye )
            System.out.println("Minden város tartozik valamely megyéhez");

        System.out.println("\n7. feladat");
        int sorszam = (int) (Math.random()*telepulesek.size());
        System.out.println(telepulesek.get(sorszam).suruseg());

        System.out.println("\n8. feladat");
        System.out.println("Házi feladat");

        System.out.println("\n9. feladat");
        System.out.println("Házi feladat");

        System.out.println("\n10. feladat");
        System.out.println("Házi feladat");

    }

    private static void adottMegyeTelepulesei(List<Telepules> telepulesek, String megye) {
        String filename= megye.substring(0,5);
        try{
            RandomAccessFile kimenet = new RandomAccessFile(filename, "rw");
            kimenet.setLength(0);
            for( Telepules varos : telepulesek)
                if(varos.adottMegye(megye))
                    kimenet.writeBytes(varos.getNev()+"\r\n");
            kimenet.close();
        }catch(IOException exc){
            System.out.println(exc.getMessage());
        }
    }

    private static List<Telepules> feltolt(String file) {
        List<Telepules> result = new ArrayList<>();
        try{
            RandomAccessFile bemenet = new RandomAccessFile(file, "r");
            while( bemenet.getFilePointer() < bemenet.length())
                result.add( new Telepules( bemenet.readLine()));
            bemenet.close();
        }catch(IOException exc){
            System.out.println(exc.getMessage());
        }
        return result;
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
        System.out.println(sor);
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

    public boolean adottMegye(String megye) {
        return this.megye.equals(megye);
    }

    public String getNev() {
        return this.nev;
    }

    public boolean limitAlatt(int limit) {
        return this.lakos < limit;
    }

    public int getLakos() {
        return this.lakos;
    }


    public String nevLakossal() {
        return this.nev + " " + this.lakos;
    }

    public boolean nincsMegye() {
        return this.megye.length() < 1;
    }

    public double nepsuruseg(){
        return Math.round(100.0*lakos/terulet)/100.0; // 2 tizedesre
    }

    public String suruseg() {
        return this.nev+" "+this.nepsuruseg()+" lakos/km^2";
    }
}
