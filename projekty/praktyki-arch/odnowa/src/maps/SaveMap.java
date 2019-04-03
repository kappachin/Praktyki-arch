package maps;

import game.Mapa;
import logic.MapElement;

import java.io.*;

public class SaveMap extends Mapa {
    UserFileInput input = new UserFileInput();

    public SaveMap(String sname) {
        super(sname);
    }

    @Override
    protected void create(String sname) {

        File input = new File("/home/praktyki/mapy/save");
        if (input.exists()){
            try {
                FileReader rd = new FileReader(input);
                BufferedReader read = new BufferedReader(rd);
                int d = 0;
                while (read.ready()) {
                    String plik = read.readLine();

                    for (int i = 0; i < plik.length(); i++ ) {
                        String s = String.valueOf(plik.charAt(i));
                        int r = Integer.parseInt(s);
                        MapElement[] kk = MapElement.values();

                        for (int a = 0; a<kk.length; a++){

                            MapElement zmienna = kk[a];
                            if (r == zmienna.rep){
                                map[d][i] = zmienna;

                            }

                        }

                        //map[d][i] = Integer.valueOf(s);
                    }
                    d++;

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    protected MapElement[][] initMap(String sname) {

        File input = new File("/home/praktyki/mapy/save");
        if (input.exists()){
            try {
                FileReader rd = new FileReader(input);
                BufferedReader read = new BufferedReader(rd);
                int d = 0;
                int y =0;
                while (read.ready()) {
                    String plik = read.readLine();
                    d = plik.length();
                    y++;
                }
                return  new MapElement[y][d];
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new MapElement[0][0];

    }

}
