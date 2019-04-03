package ui;

import game.Gam;
import game.Mapa;
import logic.Monsters;
import logic.Option;
import logic.Players;
import maps.FileMap;
import maps.UserFileInput;

import java.util.Scanner;

/**
 * Created by praktyki on 13.07.17.
 */
public class Menu {
    private Scanner input = new Scanner(System.in);

    public void welcome(){
        System.out.println("witaj w moim programie");
        System.out.println("co chcesz zrobić?");

    }

    public void start(){
        welcome();
        Option option = chooseOption();

        switch (option){
            case END:
                System.out.println("bye");
                break;

            case START:
                UserFileInput fileInput = new UserFileInput();
                fileInput.disclamer();

                String uPlik = input.nextLine().trim();
                fileInput.plik = uPlik;
                System.out.println(fileInput.plik);



                ChooseMap chooseMap = new ChooseMap();
                Mapa mapa = chooseMap.mapChooser(uPlik);

               PlayerChooser playerChooser = new PlayerChooser();
                Players players = playerChooser.playersSelect();

                players.displayplayer();


                Gam gam = new Gam(mapa,players);
                gam.start();

                break;

            case UNKNOWN:
                System.out.println("nieznana komenda");
                start();
                break;

        }


       }

    protected Option chooseOption(){
        //pobranie od inputu od uzytkownika
        String choose = input.nextLine();
        //duze litery
        choose = choose.toUpperCase();
        Option option;
        try {
            option = Option.valueOf(choose);
        }catch (IllegalArgumentException e){
            option = Option.UNKNOWN;

        }

        return option;
    }


    }

