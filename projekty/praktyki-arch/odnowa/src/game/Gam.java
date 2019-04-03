package game;

import logic.MapElement;
import logic.Monsters;
import logic.Players;
import maps.FileMap;
import maps.SaveMap;
import maps.TenByTen;
import maps.UserFileInput;
import ui.ChooseMap;
import ui.Menu;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by praktyki on 13.07.17.
 */
public class Gam {

    private Scanner input = new Scanner(System.in);

    ArrayList<Mapa> mapas;

    //private Mapa map;

    private Players player;

    private int score;

    private String pName;



    int currentMap = 0;

    private int playerX = 0;
    private int playerY = 0;
    private int monsterX;
    private int monsterY;
    private int monsterMove = 0;
    private int monsterRandomN;

    private MapElement oldValue = MapElement.FLOOR;
    private MapElement monsterOldValue = MapElement.FLOOR;

    public Gam(Mapa map, Players player) {
        mapas = new ArrayList<Mapa>();
        mapas.add(new TenByTen());

        mapas.add(new FileMap("/home/praktyki/mapy/save"));
        mapas.add(new FileMap("/home/praktyki/mapy/save1"));

        getXY(map.getMap());
       // this.map = map;
        this.player = player;




    }

    private void getXY(MapElement[][] mapa){
        for (int y =0; y<mapa.length; y++ ) {
            MapElement[] dane = mapa[y];
            for (int x =0; x<dane.length; x++ ){
                if (mapa[y][x] == MapElement.PLAYER){
                    playerX = x;
                    playerY = y;

                }


                if (mapa[y][x] == MapElement.MONSTER){
                    monsterY = y;
                    monsterX = x;


                }
            }

        }
    }

    public void start() {

        score = 99999;
        int i =0;
        boolean flag = true;
        int keys = player.isKey();
        int move = player.getMoves();
        while(true){
            clearConsole();
            mapas.get(currentMap);
            mapas.get(currentMap).display();
            String command = input.nextLine();
            i++;
            if("quit".equals(command)){
                System.out.println("podaj pseudonim:");
                pName = input.nextLine();
                saveScore();
                break;
            }
            Mapa map = mapas.get(currentMap);
            map.getMap();

            MapElement[][] mapa = mapas.get(currentMap).getMap();
            if ("up".equals(command)){
                int y = playerY -move;
                if (isPossible(mapa,y,playerX)){
                    makeMove(mapa,playerX,y);
                    monsterRandom();

                }

            }
            if ("down".equals(command)){
                int y = playerY +move;
                if (isPossible(mapa,y,playerX)){
                    makeMove(mapa,playerX,y);
                    monsterRandom();
                }
            }
            if ("left".equals(command)){
                int x = playerX -move;
                if (isPossible(mapa,playerY, x)){
                    makeMove(mapa,x,playerY);
                    monsterRandom();
                }

            }
            if ("right".equals(command)){
                int x = playerX +move;
                if (isPossible(mapa,playerY, x)){
                    makeMove(mapa,x,playerY);
                    monsterRandom();

                }
            }
            if("save".equals(command)){
                saveFile();
            }

        }
    }

    private void makeMove(MapElement[][] mapa, int nX,int nY){
        MapElement nV = mapa[nY][nX];
        mapa[nY][nX] = MapElement.PLAYER;
        mapa[playerY][playerX] = oldValue;
        if (nV == MapElement.KEY){
            nV = MapElement.FLOOR;
        }
       if (nV == MapElement.GEM){
           nV = MapElement.FLOOR;
       }
        if (nV == MapElement.L2KEY){
            nV = MapElement.FLOOR;
        }

       oldValue = nV;
        playerX = nX;
        playerY = nY;
        score-=50;
    }

    private void monsterRandom() {


            Mapa map = mapas.get(currentMap);

        MapElement[][] mapa = mapas.get(currentMap).getMap();

        Random random = new Random();
        monsterRandomN = random.nextInt(4) + 1;
        if (monsterRandomN == 0) {
            monsterMove = 0;
        } else monsterMove = 1;



        if (monsterRandomN == 1) {
            int mY = monsterY - monsterMove;
            if (monsterIsPossible(mapa, mY, monsterX)) {
                monsterMakeMove(mapa, monsterX, mY);

            }

        }

        if (monsterRandomN == 3) {
            int mY = monsterY + monsterMove;
            if (monsterIsPossible(mapa, mY, monsterX)) {
                monsterMakeMove(mapa, monsterX, mY);

            }

        }

        if (monsterRandomN == 2) {
            int mX = monsterX - monsterMove;
            if (monsterIsPossible(mapa, monsterY, mX)) {
                monsterMakeMove(mapa, mX, monsterY);

            }

        }

        if (monsterRandomN == 4) {
            int mX = monsterX + monsterMove;
            if (monsterIsPossible(mapa, monsterY, mX)) {
                monsterMakeMove(mapa, mX, monsterY);

            }

        }



    }

    private void monsterMakeMove(MapElement[][] mapa, int mNX,int mNY){



        MapElement mNV = mapa[mNY][mNX];
        mapa[mNY][mNX] = MapElement.MONSTER;
        mapa[monsterY][monsterX] = monsterOldValue;



        monsterOldValue = mNV;
        monsterX = mNX;
        monsterY = mNY;

    }

    private boolean monsterIsPossible(MapElement[][] mapa, int y , int x){

        int maxY=mapa.length;
        if(maxY==0){
            return false;
        }
        int maxX=mapa[0].length;
        if (!(x > -1 && x < maxX)){return false;}
        if (!(y > -1 && y < maxY)){return false;}
        MapElement value = mapa[y][x];
        if (value == MapElement.WALL){
            return false;
        }

        if (value == MapElement.PLAYER){
            System.out.println("przegrałeś");
            System.out.println("wynik:" +score);
            System.out.println("podaj pseudonim:");
            pName = input.nextLine();
            saveScore();
            System.exit(0);


        }
        return true;
    }


    private boolean isPossible(MapElement[][] mapa, int y , int x){
        int maxY=mapa.length;
        if(maxY==0){
            return false;
        }
        int maxX=mapa[0].length;
        if (!(x > -1 && x < maxX)){return false;}
        if (!(y > -1 && y < maxY)){return false;}
        MapElement value = mapa[y][x];
        if (value == MapElement.WALL){
            return false;
        }

        if (value == MapElement.FINISH){
            System.out.println("Gratulacje dotarłeś do wyjścia");
            System.out.println("wynik:" +score);
            System.out.println("podaj pseudonim:");
            pName = input.nextLine();
            saveScore();
            currentMap++;
            if(currentMap<mapas.size()){
                getXY(mapas.get(currentMap).getMap());
                oldValue = MapElement.FLOOR;
                monsterOldValue = MapElement.FLOOR;
                player.getKeys().clear();
            }else{
                System.exit(0);
            }
            return false;
        }
        if (value == MapElement.KEY){
            System.out.println("podnosisz klucz");
            //if (player.isKey()>1){
              //  player.setKey(player.isKey());
            //}else player.setKey(1);
            player.getKeys().put(1,true);
            //System.out.println("poziom klucza:"+ String.valueOf(player.isKey()));
        }
        if (value == MapElement.L2KEY){
            System.out.println("podnosisz klucz");
            player.getKeys().put(2,true);
            //player.setKey(2);
            //System.out.println("poziom klucza:"+ String.valueOf(player.isKey()));
        }

        if (value==MapElement.DOORS){
            Boolean k = player.getKeys().get(1);
            Boolean d = player.getKeys().get(2);
            if ((k != null && k != false) || (d !=null && d==true)){


            //if (player.isKey()>0 ){
                System.out.println("otwierasz drzwi");
                return true;
            } else System.out.println("drzwi są zamknięte"); return false;
        }
        if (value==MapElement.L2DOORS){
            Boolean d = player.getKeys().get(2);
            if (d != null && d==true){
            //if (player.isKey()>1 ){
                System.out.println("otwierasz drzwi");
                return true;
            } else System.out.println("drzwi są zamknięte"); return false;
        }

        if (value == MapElement.GEM){
            score+=250;
        }
        return true;
    }

    private void clearConsole(){
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveFile(){
        System.out.println("podaj docelewe miejsce zapisu:");
        String gdzie = input.nextLine();

        File file = new File(gdzie);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            MapElement[][] stan = mapas.get(currentMap).getMap();


            String zapis;
            for (int x =0;x<stan.length;x++){
                MapElement[] elements = stan[x];
                for (int y = 0;y<elements.length;y++){
                    MapElement mapElement = elements[y];
                    bufferedWriter.write(String.valueOf(mapElement.rep));
                    bufferedWriter.flush();

                }
                bufferedWriter.newLine();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.println("stan zapisany w:"+gdzie);

    }
    private void saveScore(){
        if (score == 99999){
            score = 0;
        }
        File scores = new File("/home/praktyki/mapy/scores");

        try {
            FileWriter writer = new FileWriter(scores,true);
            BufferedWriter bwriter = new BufferedWriter(writer);

            bwriter.newLine();
            bwriter.write(pName + "-"+ String.valueOf(score));
            bwriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
