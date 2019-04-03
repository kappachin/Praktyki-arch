package game;

import logic.MapElement;

/**
 * Created by praktyki on 17.07.17.
 */



    public abstract class Mapa {


    public MapElement[][] getMap() {
        return map;
    }

    protected MapElement map[][];

        public Mapa(String fname){
            map = initMap(fname);
            create(fname);
        }

        public void  display(){
            System.out.println("");
            for (int i = 0; i < map.length; i++) {

                // Loop and start sub-arrays.
                MapElement[] sub = map[i];
                for (int x = 0; x < sub.length; x++) {
                    MapElement value = sub[x];
                    System.out.print(value.disp);

                }
                System.out.println();
            }


        }
        protected abstract void  create(String fname);
    protected abstract MapElement[][] initMap(String fname);

    }

