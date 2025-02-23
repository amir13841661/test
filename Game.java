package snake;
import java.util.Random;
public class Game {
    private static int killerTime;
    private static Difficulity difficulity=Difficulity.EASY;
    private static int[] position={5,1};
    private static int[] lastPosition={0,0};
    private static int gridSize=10;
    private static int move=0;
    public static int holder=0;
    private static int moveCount=0;
    private static int secPassed=0;
    private static Direction lastMove;
    public static int getRandomNum(int x ,int y){
        Random random=new Random();
        return  random.nextInt(y-x+1)+x;
    }
    public static void setRandomTime(){
        killerTime=getRandomNum(1,20);
    }

    public static int getKillerTime() {
        return killerTime;
    }
    public static Difficulity getDifficulity() {
        return difficulity;
    }
    public static void setDifficulity(Difficulity difficulity) {
        Game.difficulity = difficulity;
    }
    public static void setKillerTime(int killerTime) {
        Game.killerTime = killerTime;
    }
    public static void startGame(){
        position[0]=1;
        position[1]=1;
        moveCount=0;
        lastMove=Direction.DOWN;
        killerTime=getRandomNum(10, 30);
        // killerTime=30;
        switch (difficulity) {
            case EASY:
                // killerTime=20;
                gridSize=getRandomNum(30, 60);
                break;
            case MEDIUM:
                gridSize=getRandomNum(50, 80);
                break;
            case HARD:
                gridSize=getRandomNum(70, 100);
            

        }
        while(true){
            System.out.print("1.go left\n2.go right\n3.go down\n4.go up\n");
            System.out.println("your position:(row:"+position[0]+",column:"+position[1]+")");
            System.out.println("you have "+killerTime+" seconds left.");
            System.out.println(gridSize);
            if(killerTime>0){
                System.out.println("\033[34;42mgreen light.\033[0m");
            }
            System.out.print("your move:");
            move=ForceNumericInput.force();
            switch (move) {

                case 1:
                    try{
                        holder=goLeft();
                        if(holder==1){
                            System.out.println("\ncongragulations you have won after "+moveCount+" moves!\nyour total score is:"+calculateScore());
                            
                            return;
                        }
                        else if(holder==-1){
                            // System.out.println(killerTime);
                            System.out.println("\nyou have lost after "+moveCount+" moves\nyour score:"+moveCount);
                            return;
                        }
                    }catch(Exception e){
                        e.setStackTrace(null);
                    }
                    break;


                case 2:
                    try{
                        holder=goRight();
                        if(holder==1){
                            System.out.println("\ncongragulations you have won after "+moveCount+" moves!\nyour total score is:"+calculateScore());
                            System.out.println(position[1]);
                            return;
                        }
                        else if(holder==-1){
                            System.out.println("\nyou have lost after "+moveCount+" moves\nyour score:"+moveCount);
                            return;
                        }
                    } catch(Exception e){
                        e.setStackTrace(null);
                    }
                    break;
                case 3:
                    try{
                        holder=goDown();
                        if(holder==1){
                            System.out.println("\ncongragulations you have won after "+moveCount+" moves!\nyour total score is:"+calculateScore());
                            return;
                        }
                        else if(holder==-1){
                            // System.out.println(killerTime);
                            System.out.println("\nyou have lost after "+moveCount+" moves\nyour score:"+moveCount);
                            return;
                        }
                    }catch(Exception e){
                        e.setStackTrace(null);
                    }
                    break;
                
                
            
            }
            
            

        }
    }
    public static int goRight() throws InterruptedException{
        secPassed=0;
        moveCount++;
        lastPosition[0]=position[0];
        lastPosition[1]=position[1];
        // System.out.printf("\033[2J");
        
        System.out.print("\033[34m");
        System.out.println("*");
        // System.out.printf("\033[%d;%dH*",position[0],position[1]);
        System.out.print("\033[0m");
        secPassed++;
        for(int i=1;i<=9;i++){
            holder=gameOver();
            if(holder==1){
                return 1;
            }
            else if(holder==-1){
                return-1;
            }
            position[1]++;
            killerTime--;
            if(killerTime>=0){
                System.out.println("\033[34;42mgreen light:"+secPassed+"\033[0m");

            }
            else{
                System.out.println("\033[34;41mredlight:"+secPassed+"\033[0m");
            }
            
            secPassed++;
            // System.out.printf("\033[%d;%dH*",position[0],position[1]);
            for(int j=1;j<=i+1;j++){
                System.out.print("*");
            }
            System.out.println();

            System.out.flush();
            

            Thread.sleep(220);

            

            
        }
        for(int i=1;i<=9;i++){
            holder=gameOver();
            if(holder==1){
                return 1;
            }
            else if(holder==-1){
                return-1;
            }
            killerTime--;
            if(killerTime>=0){
                System.out.println("\033[34;42mgreen light:"+secPassed+"\033[0m");

            }
            else{
                System.out.println("\033[34;41mredlight:"+secPassed+"\033[0m");
            }
            secPassed++;
            for(int j=1;j<=i;j++){
                System.out.print(" ");
            }
            for(int j=1;j<=10-i;j++){
                System.out.print("*");
            }

            System.out.println();
            // killerTime--;
            Thread.sleep(220);

        }
        System.out.print("\n");
        killerTime=getRandomNum(10, 30);
        lastMove=Direction.RIGHT;
        return 0;
    }



    public static int goDown() throws InterruptedException{

        if(lastMove==Direction.DOWN){
            System.out.println("you have to move left or right first!");
            return -2;
        }
        secPassed=0;
        moveCount++;
        System.out.print("\033[34m");
        System.out.println("*");
        System.out.print("\033[0m");
        secPassed++;
        for(int i=1;i<=9;i++){
            holder=gameOver();
            if(holder==1){
                return 1;
            }
            else if(holder==-1){
                return-1;
            }
            System.out.println("\033[34;42mgreen light:"+secPassed+"\033[0m");
            secPassed++;
            for(int k=1;k<=i+1;k++){
                if(k<=5){
                    for(int j=1;j<k;j++){
                        System.out.print(" ");
                    }
                }
                else{
                    for(int j=1;j<=10-k;j++){
                        System.out.print(" ");
                    }
                }
                System.out.print("*\n");

            }
            if(i<=4){
                position[0]++;
                position[1]++;
            }
            else if(i==5){
                position[0]++;
            }
            else{
                position[0]++;
                position[1]--;
            }


            killerTime--;
            

            Thread.sleep(220);

            

            
        }
        for(int i=1;i<=9;i++){
            holder=gameOver();
            if(holder==1){
                return 1;
            }
            else if(holder==-1){
                return-1;
            }


            System.out.println("\033[34;42mgreen light:"+secPassed+"\033[0m");
            secPassed++;
            for(int k=1;k<=10;k++){
                if(k<=5){
                    for(int j=1;j<k;j++){
                        System.out.print(" ");
                    }
                }
                else{
                    for(int j=1;j<=10-k;j++){
                        System.out.print(" ");
                    }
                }
                if(k>i){
                    System.out.print("*\n");
                }
                else{
                    System.out.println();
                }
                

            }
            



            killerTime--;
            

            Thread.sleep(220);

            

            
        }

        System.out.print("\n");
        killerTime=getRandomNum(10, 30);
        lastMove=Direction.DOWN;
        return 0;
    }



    public static int goLeft() throws InterruptedException{
        if(position[1]<10){
            System.out.println("can't go left it's blocked.");
            return -2;
        }
        secPassed=0;
        moveCount++;
        for(int i=1;i<10;i++){
            System.out.print(" ");
        }
        System.out.print("\033[34m");
        System.out.println("*");
        // System.out.printf("\033[%d;%dH*",position[0],position[1]);
        System.out.print("\033[0m");
        secPassed++;
        for(int i=1;i<=9;i++){
            holder=gameOver();
            if(holder==1){
                return 1;
            }
            else if(holder==-1){
                return-1;
            }
            position[1]--;
            killerTime--;
            System.out.println("\033[34;42mgreen light:"+secPassed+"\033[0m");
            secPassed++;
            // System.out.printf("\033[%d;%dH*",position[0],position[1]);
            for(int j=1;j<=9-i;j++){
                System.out.print(" ");
            }
            for(int j=1;j<=i+1;j++){
                System.out.print("*");
            }
            System.out.println();

            System.out.flush();
            

            Thread.sleep(220);

            

            
        }
        for(int i=1;i<=9;i++){
            holder=gameOver();
            if(holder==1){
                return 1;
            }
            else if(holder==-1){
                return-1;
            }
            System.out.println("\033[34;42mgreen light:"+secPassed+"\033[0m");
            secPassed++;

            for(int j=1;j<=10-i;j++){
                System.out.print("*");
            }

            System.out.println();
            killerTime--;
            Thread.sleep(220);

        }
        System.out.print("\n");
        killerTime=getRandomNum(10, 30);
        lastMove=Direction.LEFT;
        return 0;
    }


    public static int gameOver(){
        
        if((position[0]>gridSize||position[1]>gridSize)&&killerTime>=0){
            return 1;
        }
        else if(killerTime<0){
            return -1;
        }
        else{
            return 0;
        }
    }

    public static int calculateScore(){
        switch (difficulity) {
            case EASY:
                return moveCount+10;
            case MEDIUM:
                return moveCount +20;
            case HARD:
                return moveCount+40;
        
        }
        return 0;
    }

}


