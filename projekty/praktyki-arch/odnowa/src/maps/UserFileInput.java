package maps;

import ui.Menu;

import java.util.Scanner;

/**
 * Created by praktyki on 18.07.17.
 */
public class UserFileInput{


    public String getPlik() {
        return plik;
    }

    public void setPlik(String plik) {


        this.plik = plik;

    }


    public String plik;

    public String getZapis() {
        return zapis;
    }

    public String zapis = "/home/praktyki/mapy/save";


    public void disclamer(){
        System.out.println("czy chcesz załadować mapę z pliku? Jeśli nie wciśnij ENTER");
    }
    public void displayInput(){
        System.out.print(plik);
    }


    }

