/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipbattle;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Mateusz
 */
public class ShipBattle {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String login;
        String password;
        int choice;
        int size;
        int x;
        String value;
        int y;
        int direction;
        int step;
        int player = 1;
        boolean end = false;
        
        Scanner write = new Scanner(System.in);
        Scanner write2 = new Scanner(System.in);
        Scanner write3 = new Scanner(System.in);
        Scanner choice2 = new Scanner(System.in);
        Scanner choice3 = new Scanner(System.in);
        Scanner choice4 = new Scanner(System.in);
        Scanner choice5 = new Scanner(System.in);
        
        System.out.println("MENU\n Wprowadz: 1 - logowanie\n Wprowadz: 2 - nowe konto");
        choice = write.nextInt();
        if(choice != 1 || choice != 2){
            
            System.out.println("Wprowadz login: ");
            login = write2.nextLine();
            System.out.println("Wprowadz haslo: ");
            password = write3.nextLine();  
            User newUser = new User(login,password);
        switch (choice){
            case 1:
                newUser.LoginUser();
                System.out.println("MENU\n Wprowadz: 1 - nowa gra \n Wprowadz: 2 - kontynuacja ostatniej gry");
                choice = write.nextInt();
                if(choice == 2){
                    System.out.println("Kontynuacja ostatniej gry.");
                } else{
                    System.out.println("Nowa gra.");
                }
                break;
            case 2:
                newUser.CreateUser();
                newUser.LoginUser();
                choice = 2;
                    System.out.println("Nowa gra.");
                break;
            default:
                System.out.println("Nie wybrano zadnej z opcji!");
                break;
        }

        switch (choice){
            case 1:
                Game newGame = new Game(login, password);
                newGame.CreateGame(login);             
                System.out.println("FAZA 1: Wprowadz okręty");
                System.out.println("Gracz " + player);
                newGame.LegendFaze();
                newGame.Field(true);
                while(newGame.ship1 > 0 || newGame.ship2 > 0 || newGame.ship3 > 0 || newGame.ship4 > 0){
                    newGame.howManyShips();
                    System.out.println("Wprowadz typ okrętu:");
                    size = choice2.nextInt();
                    System.out.println("Wprowadz koordynaty okretu:");
                    System.out.print("[");
                    x = choice3.nextInt();
                    System.out.print("[");
                    y = choice4.nextInt();
                    /*
                    switch (value){
                        case "A":
                            y=1;
                        case "B":
                            y=1;
                        case "C":
                            y=1;
                        case "D":
                            y=2;
                        case "E":
                            y=3;
                        case "F":
                            y=4;
                        case "G":
                            y=5;
                        case "H":
                            y=6;
                        case "I":
                            y=7;
                        case "J":
                            y=8;
                        case "K":
                            y=9;      
                    }*/
                    System.out.println("Wprowadz kierunek:");
                    direction = choice5.nextInt();
                    newGame.AddShip(size, x, y, direction);
                    newGame.Field(true);
                }
                for(int i = 0;i < 10;i++){
                    System.out.println("");
                }
                player++;
                System.out.println("Gracz " + player);
                Game secondGame = new Game(login, password);
                secondGame.CreateGame(login);
                System.out.println("FAZA 1: Wprowadz okręty");
                System.out.println("Gracz " + player);
                secondGame.LegendFaze();
                secondGame.Field(true);
                while(secondGame.ship1 > 0 || secondGame.ship2 > 0 || secondGame.ship3 > 0 || secondGame.ship4 > 0){
                    secondGame.howManyShips();
                    System.out.println("Wprowadz typ okrętu:");
                    size = choice2.nextInt();
                    System.out.println("Wprowadz koordynaty okretu:");
                    System.out.print("[");
                    x = choice3.nextInt();
                    System.out.print("[");
                    y = choice4.nextInt();
                    System.out.println("Wprowadz kierunek:");
                    direction = choice5.nextInt();
                    secondGame.AddShip(size, x, y, direction);
                    secondGame.Field(true);
                }
                newGame.SaveGame(login, secondGame.battleField);
                for(int i = 0;i < 10;i++){
                    System.out.println("");
                }
                System.out.println("FAZA 2: BITWA");
                player=1;
                while(end == false){
                    end = true;
                    for(int i = 4;i < 14;i++){
                        for(int j=4;j<14;j++){
                            if(secondGame.battleField[i][j]==1){
                                end = false;
                            }
                        }
                    }
                    if(end == true){
                        System.out.println("Koniec gry, wszystkie okręty zatopione!");
                        System.out.println("GRACZ 1 WYGRYWA!");
                    } else{
               
                    for(int i = 4;i < 14;i++){
                        for(int j=4;j<14;j++){
                            if(newGame.battleField[i][j]==1){
                                end = false;
                            }

                        }
                    }
                    if(end == true){
                        System.out.println("Koniec gry, wszystkie okręty zatopione!");
                        System.out.println("GRACZ 2 WYGRYWA!");
                    }
                    }
                    
                    if(end == false){
                        if(player == 1){
                            System.out.println("Ruch gracza" + player + ". Wprowadz koordynaty strzału:");
                            secondGame.Field(false);
                            System.out.print("[");
                            x = choice3.nextInt();
                            System.out.print("[");
                            y = choice4.nextInt();
                            secondGame.Shoot(x, y);
                            secondGame.Field(false);
                            newGame.SaveGame(login, secondGame.battleField);
                            player++;
                        } else
                        {
                            if(player == 2){
                                System.out.println("Ruch gracza" + player + ". Wprowadz koordynaty strzału:");
                                newGame.Field(false);
                                System.out.print("[");
                                x = choice3.nextInt();
                                System.out.print("[");
                                y = choice4.nextInt();
                                newGame.Shoot(x, y);
                                newGame.Field(false);
                                player--;
                                
                                newGame.SaveGame(login, secondGame.battleField);

                            }
                        }
                    }
                }
                break;
            case 2:
                Game loadGame1 = new Game(login, password);
                Game loadGame2 = new Game(login, password);
                loadGame1.LoadGame(login,true);
                loadGame2.LoadGame(login,false);
                System.out.println("FAZA 2: BITWA");
                player=1;
                while(end == false){
                    end = true;
                    for(int i = 4;i < 14;i++){
                        for(int j=4;j<14;j++){
                            if(loadGame2.battleField[i][j]==1){
                                end = false;
                            }
                        }
                    }
                    if(end == true){
                        System.out.println("Koniec gry, wszystkie okręty zatopione!");
                        System.out.println("GRACZ 1 WYGRYWA!");
                    } else{
               
                    for(int i = 4;i < 14;i++){
                        for(int j=4;j<14;j++){
                            if(loadGame1.battleField[i][j]==1){
                                end = false;
                            }

                        }
                    }
                    if(end == true){
                        System.out.println("Koniec gry, wszystkie okręty zatopione!");
                        System.out.println("GRACZ 2 WYGRYWA!");
                    }
                    }
                    
                    if(end == false){
                        if(player == 1){
                            System.out.println("Ruch gracza" + player + ". Wprowadz koordynaty strzału:");
                            loadGame2.Field(false);
                            System.out.print("[");
                            x = choice3.nextInt();
                            System.out.print("[");
                            y = choice4.nextInt();
                            loadGame2.Shoot(x, y);
                            loadGame2.Field(false);
                            loadGame1.SaveGame(login, loadGame2.battleField);
                            player++;
                        } else
                        {
                            if(player == 2){
                                System.out.println("Ruch gracza" + player + ". Wprowadz koordynaty strzału:");
                                loadGame1.Field(false);
                                System.out.print("[");
                                x = choice3.nextInt();
                                System.out.print("[");
                                y = choice4.nextInt();
                                loadGame1.Shoot(x, y);
                                loadGame1.Field(false);
                                loadGame1.SaveGame(login, loadGame2.battleField);
                                player--;
                            }
                        }
                    }
                }
                break;
            }
        }    
               
    } 
}  
