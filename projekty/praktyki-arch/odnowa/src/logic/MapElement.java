package logic;

/**
 * Created by praktyki on 17.07.17.
 */
public enum MapElement {


    PLAYER(3,"☺"),WALL(2,"▇"),DOORS(5,"①"),FLOOR(1,"░"),FINISH(4,"K"),KEY(6,"⑴"),GEM(7,"✧"),L2DOORS(8,"②"), L2KEY(0,"⑵"), MONSTER(9,"M");


    public int rep;
    public String disp;

    MapElement(int r, String d){
        rep = r;
        disp = d;

    }
}
