/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipbattle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author Mateusz
 */
public class Game extends User{
    int x;
    int y;
    int ship1 = 4;
    int ship2 = 3;
    int ship3 = 2;
    int ship4 = 1;
    boolean newG;
    int[][] battleField;
    //comit
    
    public Game(String login, String password){
        this.login = login;
        this.password = password;
        
    }
    public void CreateGame(String login) throws IOException{
        try {
            File file = new File(login);
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            this.battleField = new int[18][18];
            bw.write("\r\nbattle2");
            bw.flush();
            for(int i = 0 ; i<=17 ; i++){
                for(int j = 0; j<=17 ; j++){
                    this.battleField[i][j]=2;
                    bw.write("\r\n" + this.battleField[i][j]);
                    bw.flush();
                }
            }
            for(int i = 4 ; i<14 ; i++){
                for(int j = 4; j<14 ; j++){
                    this.battleField[i][j]=0;
                    bw.write("\r\n" + this.battleField[i][j]);
                    bw.flush();
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
        
        }
    }
    
    public void SaveGame(String login, int battle[][]) throws IOException{
        PrintWriter save = new PrintWriter(login);
        save.println(this.password);
        for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                save.println(this.battleField[i][j]);
                save.flush();
            }
        }
        for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                save.println(battle[i][j]);
                save.flush();
            }
        }
        save.close();
    }
    
    
    public void LoadGame(String login, boolean value) throws FileNotFoundException{
        this.battleField = new int[18][18];
        File Load = new File(login);
        Scanner write = new Scanner(Load);
        this.password = write.nextLine();
        if(value == true){
            for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                this.battleField[i][j] = Integer.valueOf(write.nextLine());
            }
        }   
        }
        if(value == false){
            for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                this.battleField[i][j] = Integer.valueOf(write.nextLine());
            }
        }
        for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                this.battleField[i][j] = Integer.valueOf(write.nextLine());
            }
        }
        }
        write.close();
    }
    
    public void LoadGame2(String login) throws FileNotFoundException{
        File Load = new File(login + ".txt");
        Scanner write = new Scanner(Load);
        write.nextLine();
        for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                write.nextLine();
            }
        }
        for(int i = 0 ; i<=17 ; i++){
            for(int j = 0; j<=17 ; j++){
                this.battleField[i][j] = Integer.valueOf(write.nextLine());
            }
        }
    }
    
    public void AddShip(int size, int x, int y, int direction) throws IOException{
        x=x+4;
        y=y+4;
        int standard;
        int vertical;
        int step;
        int X=x;
        int Y=y;
        step = 1;
        boolean node= true;
        boolean warning = true;
        
        if(size==1 && ship1 != 0){
            if(this.battleField[x][y] == 2){
                node=false;
                System.out.println("Proba wprowadzenia okretu poza mape!");
            }
            vertical=Y-1;
            standard=X-1;
            while(vertical <= Y+1){
                if(this.battleField[standard][vertical]==1){
                    node=false;
                    System.out.println("Nie mozna tu wprowadzic okretu!");
                }
                if(standard < X+1){
                    standard++;
                } else if(vertical <= Y+1){
                    vertical++;
                    standard=X-1;
                }
            }
            if(node==true){
                this.battleField[x][y]=1;
                ship1--;
                System.out.println("Wprowadzono okret!");
            }
        }else if((size==2 && ship2 != 0) || (size==3 && ship3 != 0) || (size==4 & ship4 != 0)){
            while(step<=size){
                
                if(step > 1 && direction == 1){
                    X=X-1;
                }else if(step > 1 && direction == 2){
                    Y=Y-1;
                }else if(step > 1 && direction == 3){
                    X=X+1;
                }else if(step > 1 && direction == 4){
                    Y=Y+1;
                }
                
                vertical=Y-1;
                standard=X-1;
                while(vertical <= Y+1){
                    if(this.battleField[standard][vertical]==1){
                        node=false;
                        System.out.println("Nie mozna tu wprowadzic okretu!");
                    }
                    if(standard < X+1){
                        standard++;
                    } else if(vertical <= Y+1){
                        vertical++;
                        standard=X-1;
                    }
                }
                step++;
            }
        }
        if(node != false){
            if(size == 2 || size == 3 || size == 4){
                for(int i = 1;i< size;i++){
                    switch (direction) {
                        case 1:
                            if(this.battleField[x-i][y] ==2){
                                warning = false;
                            }
                            break;
                        case 2:
                            if(this.battleField[x][y-i] ==2){
                                warning = false;
                            }
                            break;
                        case 3:
                            if(this.battleField[x+i][y] ==2){
                                warning = false;
                            }
                            break;
                        case 4:
                            if(this.battleField[x][y+i] ==2){
                                warning = false;
                            }
                            break;
                        default:
                            break;
                }
                if(warning != false){
                    this.battleField[x][y] = 1;
                    System.out.println("Wprowadzono okret!");
                    if(size == 2){
                        ship2--;
                    }
                    if(size == 3){
                        ship3--;     
                    }
                    if(size==4){
                        ship4--;
                    }
                    for(int j = 1;j < size;j++){
                    switch (direction) {
                        case 1:
                            this.battleField[x-i][y] = 1;
                            break;
                        case 2:
                            this.battleField[x][y-i] = 1;
                            break;
                        case 3:
                            this.battleField[x+i][y] = 1;
                            break;
                        case 4:
                            this.battleField[x][y+i] = 1;
                            break;
                        default:
                            break;
                    }
                    } 
                } else
                {
                    System.out.println("Proba wprowadzenia okretu poza mape!");
                }
            }
        }
    }
    }   
    
    public void Shoot(int x, int y){
        if(this.battleField[x+4][y+4]==1){
            System.out.println("Trafienie!");
            this.battleField[x+4][y+4]=3;
        }
        else
        {
            this.battleField[x+4][y+4]=2;
            System.out.println("Pudło!");
        }
    }
    
    public void Field(boolean value){
        int licz =0;
        System.out.println("---------------------------------");
        System.out.print("    A  B  C  D  E  G  H  I  J  K");
        System.out.println("");
        for(int i = 4;i < 14;i++){
            for(int j=4;j<14;j++){
                if(j==4){
                    System.out.print(licz + " |");
                }
                if(battleField[i][j]==0){
                    System.out.print("[_]");
                }
                if(battleField[i][j]==1 && value == true){
                    System.out.print("[X]");
                }
                if(battleField[i][j]==1 && value == false){
                    System.out.print("[_]");
                }
                if(battleField[i][j]==2){
                    System.out.print("[0]");
                }
                
                if(battleField[i][j]==3){
                    System.out.print("[T]");
                }
            }
            System.out.println("");
            licz++;
        }
        System.out.println("---------------------------------");
    }
    
    public void howManyShips(){
        System.out.println("Pozostało: jednomasztowce: " + ship1 + " dwumasztowce: " + ship2 + " trzymasztowce: " + ship3 + " czteromasztowce: " + ship4);
    }
    
    public void Legend(){
        System.out.println("---------------------------------");
        System.out.println("[_] - pole puste");
        System.out.println("[X] - pole ze statkiem");
        System.out.println("[0] - pudło");
        System.out.println("[T] - trafienie");
        System.out.println("---------------------------------");
    }
    
    public void LegendFaze(){
        System.out.println("---------------------------------");
        System.out.println("Typ okretu: 1 - jednomasztowiec, 2, 3, 4");
        System.out.println("Koordynaty: [0...9] , [a...k]");
        System.out.println("Kierunek: 1 - góra, 2 - lewo, 3 - dół, 4 - prawo");
    }
}
