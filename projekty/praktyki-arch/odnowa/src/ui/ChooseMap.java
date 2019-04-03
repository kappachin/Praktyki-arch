package ui;

import game.Mapa;
import maps.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by praktyki on 17.07.17.
 */
public class ChooseMap{

        private Scanner input = new Scanner(System.in);

    ArrayList<Mapa> mapas = new ArrayList<Mapa>(2);


        public Mapa mapChooser(String k){
            Mapa mapa1 = new TwoByTwo();
            Mapa mapa2 = new TenByTen();
            mapas.add(0,new TwoByTwo());
            mapas.get(0).display();
            mapas.add(1,new TenByTen());
            System.out.println("1- mapa 1");
            mapa1.display();
            System.out.println("");
            System.out.println("");
            System.out.println("2- mapa 2");
            mapa2.display();
            System.out.println("");
            System.out.println("");
            System.out.println("3- mapa z pliku");
            System.out.println("");
            System.out.println("");
            System.out.println("4- ostatni zapis");




            int in = input.nextInt();

            return mapChooser(in,k);
        }


        protected Mapa mapChooser(int in,String u){
            switch (in){
                case 1:  return new TwoByTwo();

                case 2: return new TenByTen();

                case 3: return new FileMap(u);

                case 4: return new SaveMap(u);

                case 5: return mapas.get(1);

                case 6: return mapas.get(0);

                default: return new TwoByTwo();
            }



        }


}
