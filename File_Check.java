/*
Features in the Program (List is specified as a Linked List)
1. Read from Files
    a. Read Password_File (This is needed when modifying a list)
    b. Read PathList_File (This is where the Path of the files are stored)
2. Menu
    a. Test List
    b. Modify List
3. Settings (AKA Modify List)
    a. Print List
    b. Add to List
    c. Delete a path from the List
    d. Clear List Completely
4. If changes occurred save all the files else skip this step (Use Global Variables to keep track if the files got edited)
*/

import java.util.*;
import java.io.*;

public class File_Check{

    static String Name;
    static String Pin;

    public static void main(String[] args){

        int Run = 1;

        Read();

        System.out.println("|---------------------------|");
        System.out.println("Welcome: " + Name + ",\n");
        
        while (Run == 1){

            Scanner Scanner = new Scanner(System.in);
            System.out.println("1. Test");
            System.out.println("2. Add File");
            System.out.println("3. Delete File");
            System.out.println("4. Modify File Path");
            System.out.print("5. Exit\n>> ");
            String choice = Scanner.nextLine();

            if (choice.equals("1")){

            }else if (choice.equals("2")){

            }else if (choice.equals("3")){

            }else if (choice.equals("4")){

            }else if (choice.equals("5")){
                Run = 0;
            }else {
                System.out.println("Invalid Choice!");
            }

            System.out.println("");

        }

    }

    public static void Read(){

        Scanner Scanner = new Scanner(System.in);

        File File1 = new File("File Check Program Files" + File.separator + "pin.txt");
        boolean EXST1 = File1.exists();

        File File2 = new File("File Check Program Files" + File.separator + "path.txt");
        boolean EXST2 = File2.exists();
        
        if(EXST1 == false){

            System.out.print("Enter your name >> "); 
            Name = Scanner.nextLine();
            System.out.println();

            int x = 1;
            while(x == 1){
                System.out.print("Enter a 4-digit pin >> "); 
                Pin = Scanner.nextLine();
                
                if (Pin.length() == 4){
                    x = 0;
                }else{
                    System.out.println("Please enter a valid 4-digit pin!\n");
                }
            }

            Write(Name, Pin, 1);

        }
        
        if (EXST2 == false){
            Write(null, null, 2); 
        }
        
        try{
            FileReader fr = new FileReader("File Check Program Files" + File.separator + "pin.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String str; 
            int loop = 0;
            while ((str = br.readLine()) != null){
                if (loop == 0){
                    String[] StrArray = str.split(";");
                    Name = StrArray[0];
                    Pin = StrArray[1];
                }
                loop++;     
            }
        }catch(IOException e){
            System.out.println("Error: Code 2001\n");
        }

        System.out.println("");

    }

    public static void Write(String Data, String Data2, int key){
        
        try {
            File DIR = new File("File Check Program Files");
            DIR.mkdir();
        } catch (Exception e) {
            System.out.println("Error: Code 3000\n");
        }

        if (key == 1){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("File Check Program Files" + File.separator + "pin.txt"))){
                writer.write( Data + ";" + Data2);
                writer.close();
            }catch (Exception ex){
                System.out.println("Error: Code 1001\n");
            }
        } else if (key == 2){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("File Check Program Files" + File.separator + "path.txt"))){
                writer.close();
            }catch (Exception ex){
                System.out.println("Error: Code 1002\n");
            }
        }

    }

}