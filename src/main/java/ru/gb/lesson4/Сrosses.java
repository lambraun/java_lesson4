package ru.gb.lesson4;

import java.util.Random;
import java.util.Scanner;

public class Сrosses {


    // Поле
    private static char[][] map;
    private static final int SIZE_X = 3;
    private static final int SIZE_Y = 3;
    private static final int DOTS_TO_WIN = 3;

    // Ячейки поля
    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
    private static void setSymb(int y, int x, char symb) {
        map[y][x] = symb;
    }

    // Проверка победы
    private static boolean checkWin ( char symb ) {
//        if ( map [ 0 ][ 0 ] == symb && map [ 0 ][ 1 ] == symb && map [ 0 ][ 2 ] == symb ) return true ;
//        if ( map [ 1 ][ 0 ] == symb && map [ 1 ][ 1 ] == symb && map [ 1 ][ 2 ] == symb ) return true ;
//        if ( map [ 2 ][ 0 ] == symb && map [ 2 ][ 1 ] == symb && map [ 2 ][ 2 ] == symb ) return true ;
//        if ( map [ 0 ][ 0 ] == symb && map [ 1 ][ 0 ] == symb && map [ 2 ][ 0 ] == symb ) return true ;
//        if ( map [ 0 ][ 1 ] == symb && map [ 1 ][ 1 ] == symb && map [ 2 ][ 1 ] == symb ) return true ;
//        if ( map [ 0 ][ 2 ] == symb && map [ 1 ][ 2 ] == symb && map [ 2 ][ 2 ] == symb ) return true ;
//        if ( map [ 0 ][ 0 ] == symb && map [ 1 ][ 1 ] == symb && map [ 2 ][ 2 ] == symb ) return true ;
//        if ( map [ 2 ][ 0 ] == symb && map [ 1 ][ 1 ] == symb && map [ 0 ][ 2 ] == symb ) return true ;
//        return false ;
//    }
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (checkLine(i, j, 0, 1,  symb)) return true;   // проверим линию по х
                if (checkLine(i, j, 1, 1,  symb)) return true;   // проверим по диагонали х у
                if (checkLine(i, j, 1, 0,  symb)) return true;   // проверим линию по у
                if (checkLine(i, j, -1, 1, symb)) return true;  // проверим по диагонали х -у
            }
        }
        return false;
    }
    // проверка линии
    private static boolean checkLine(int y, int x, int vy, int vx, char symb) {
        int wayX = x + (DOTS_TO_WIN - 1) * vx;
        int wayY = y + (DOTS_TO_WIN - 1) * vy;
        if (wayX < 0 || wayY < 0 || wayX > SIZE_X - 1 || wayY > SIZE_Y - 1) return false;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int itemY = y + i * vy;
            int itemX = x + i * vx;
            if (map[itemY][itemX] != symb) return false;
        }
        return true;
    }
    private static boolean isMapFull() {
        for (int i = 0; i < SIZE_X; i++){
            for (int j = 0; j < SIZE_Y; j++){
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    // Ход компьютера
    private static void aiTurn(){
        int x, y;
        do {
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_Y);
        }while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    // Ход человека
    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));  // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    // Проверка ячеек
    private static boolean isCellValid(int x, int y){
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    // Инициализация поля
    private static void initMap() {
        map = new char[SIZE_X][SIZE_Y];
        for (int i = 0; i <SIZE_X; i++){
            for (int j = 0; j < SIZE_Y; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    // Вывод поля в консоль
    private static void printMap() {
        for (int i = 0; i <= SIZE_X;  i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < SIZE_X; i++){
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE_Y; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
