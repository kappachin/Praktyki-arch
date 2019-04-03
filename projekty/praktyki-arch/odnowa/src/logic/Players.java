package logic;

import java.util.HashMap;

/**
 * Created by praktyki on 17.07.17.
 */
public abstract class Players {

    public int getMoves() {
        return moves;
    }

    protected int moves;
    protected String chName;

    public int isKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    protected int key;
    protected HashMap<Integer,Boolean> keys;

    public Players(){
        chName = new String();
        keys = new HashMap<>();
        createplayer();
    }

    public abstract void displayplayer();
    protected abstract void createplayer();

    public HashMap<Integer, Boolean> getKeys() {
        return keys;
    }
}
