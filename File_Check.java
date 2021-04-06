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
    (1) Variable if the List has been moddified
    (2) Pin or Name has been modified

*/

import java.util.*;
import java.io.*;
import java.lang.Math;

public class File_Check{

    static String Name;
    static String Pin;
    static int CHG_List = 0;
    static int CHG_PinorPath = 0;
    static LinkedList<String> File_Paths = new LinkedList<String>();

    public static void main(String[] args){
        int Run = 1;

        Read();
        System.out.println("Welcome " + Name + "!\n");

        while (Run == 1){

            System.out.println("----------------------------");
            System.out.println("            Menu"            );
            System.out.println("----------------------------");
            Scanner Scanner = new Scanner(System.in);
            System.out.println("1. Test");
            System.out.println("2. Add File");
            System.out.println("3. Delete File");
            System.out.println("4. Modify File Path");
            System.out.print("5. Exit\n>> ");
            String choice = Scanner.nextLine();

            if (choice.equals("1")){
                int size = File_Paths.size();
                int biggestPath = 0;

                for(int i = 0; i < size; i++){
                    String temp = File_Paths.get(i);
                    if (biggestPath < temp.length()){
                        biggestPath = temp.length();
                    }
                }
                double db_biggestPath = (double) (biggestPath);
                double db_biggestPathdiv8 = db_biggestPath / 8;
                double rnd_db_biggestPathdiv8 = Math.ceil(db_biggestPathdiv8);
                int biggestPathdiv8 = (int) rnd_db_biggestPathdiv8;

                if (size == 0){
                    System.out.println("\nNo Files to Test");
                }else{
                    System.out.println("\nTesting Files\n");
                    for(int i = 0; i < size; i++){
                        System.out.print("File " + i + "\t");
                        String Path = File_Paths.get(i);
                        int lengthofPath = Path.length();
                        double db_lengthofPath = (double) lengthofPath;
                        double db_lengthofPathdiv8 = db_lengthofPath / 8;
                        double rnd_db_lengthofPathdiv8 = Math.ceil(db_lengthofPathdiv8);
                        int lengthofPathdiv8 = (int) rnd_db_lengthofPathdiv8;
                        int different = biggestPathdiv8 - lengthofPathdiv8;
                        Print_Path(Path, different);
                    }
                }
            }else if (choice.equals("2")){

            }else if (choice.equals("3")){

            }else if (choice.equals("4")){

            }else if (choice.equals("5")){
                System.out.println("\nGoodbye " + Name + "!");
                Run = 0;
            }else {
                System.out.println("Invalid Choice!");
            }

            System.out.println("\n");

        }

    }

    public static int Check(String Path){

        File File1 = new File(Path);
        boolean EXST1 = File1.exists();
        if (EXST1 == true){
            return 1;
        }else{
            return 0;
        }
    }

    public static void Read(){

        Scanner Scanner = new Scanner(System.in);
        
        File File1 = new File("File Check Program Files" + File.separator + "pin.txt");
        boolean EXST1 = File1.exists();
        File File2 = new File("File Check Program Files" + File.separator + "path.txt");
        boolean EXST2 = File2.exists();

        if (EXST1 == true && EXST2 == true){
            
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

            try{
                FileReader fr = new FileReader("File Check Program Files" + File.separator + "path.txt");
                BufferedReader br = new BufferedReader(fr);
                
                String str; 
                while ((str = br.readLine()) != null){
                    File_Paths.add(str);
                }

            }catch(IOException e){
                System.out.println("Error: Code 2002\n");
            }

        }else{
            
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

            System.out.println("");
            Read();
        }

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

    public static void Print_Path(String Path, int Diff){
    
        int x = Check(File_Paths.get(Diff));
        System.out.print(Path);
        for(int i = 0; i < Diff; i++){
            System.out.print("\t");
        }
        System.out.print("\t");

        if (x == 1){
            System.out.println("Present: Yes");
        }else if (x == 0){
            System.out.println("Present: No");
        }
    }

}