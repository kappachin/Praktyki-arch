package maps;

import game.Mapa;
import logic.MapElement;

/**
 * Created by praktyki on 17.07.17.
 */
public class TenByTen extends Mapa {
    public TenByTen() {
        super("");
    }

    @Override
    protected void create(String fname) {
        map[0][1] = MapElement.FLOOR;
        map[0][0] = MapElement.PLAYER;
        map[1][0] = MapElement.WALL;
        map[1][1] = MapElement.FINISH;
    }

    @Override
    protected MapElement[][] initMap(String fname) {
        return new MapElement[2][2];
    }
}
