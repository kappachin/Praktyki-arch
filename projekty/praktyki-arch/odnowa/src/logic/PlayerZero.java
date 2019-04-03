package logic;

/**
 * Created by praktyki on 17.07.17.
 */
public class PlayerZero extends Players{
    @Override
    public void displayplayer() {

        System.out.print(chName);
    }

    @Override
    public void createplayer() {
        key = 0;
        moves = 0;
        chName = "Player_test_sub";


    }
}
