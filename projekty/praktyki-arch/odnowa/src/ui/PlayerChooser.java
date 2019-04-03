package ui;

import logic.PlayerOne;
import logic.PlayerZero;
import logic.Players;

import java.util.Scanner;

/**
 * Created by praktyki on 17.07.17.
 */
public class PlayerChooser {
    private Scanner input = new Scanner(System.in);
    public Players playersSelect(){
        System.out.println("wybierz gracza");
        System.out.println("0- test_player");
        Players players = new PlayerOne();

        System.out.println("1- Player_1");

        int ii = input.nextInt();
        return selectPlayer(ii);

    }

    private Players selectPlayer(int ii){
        switch (ii){
            case 0: return new PlayerZero();
            case 1: return new PlayerOne();
            default: return new PlayerZero();
        }


    }

}
