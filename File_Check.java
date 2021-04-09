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
    static int biggestPathdiv8;

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
                Storing_Largest_String();

                if (size == 0){
                    System.out.println("\nNo Files to Test");
                }else{
                    System.out.println("\nTesting Files\n");
                    Printing_List();
                }

            }else if (choice.equals("2")){
                
                int Add_Run = 1;
                int Add_CheckEXST = 0;
                while (Add_Run == 1){
                    System.out.print("\nEnter File Path >> ");
                    String Add_Path = Scanner.nextLine();
                        
                    for(int i = 0; i < File_Paths.size(); i++){
                        if (Add_Path.equals(File_Paths.get(i))){
                            Add_CheckEXST = 1;
                        }
                    }

                    if (Add_CheckEXST == 1){
                        System.out.println("Path Already Exists Try Again!");
                        Add_CheckEXST = 0;
                    }else{
                        System.out.println("Path has been added Sucessfully!");
                        File_Paths.add(Add_Path);
                        CHG_List = 1;
                        Add_Run = 0;
                    }
                }

            }else if (choice.equals("3")){

                System.out.println("\nDeleting Files\n");
                Storing_Largest_String();
                Printing_List();

                int Del_Run = 1;
                while(Del_Run == 1){
                    
                    System.out.print("\nEnter User Pin >> ");
                    String UIPin = Scanner.nextLine();

                    if(UIPin.equals("-1")){
                        Del_Run = 0;
                    }else if (UIPin.equals(Pin)){
                        int Pick_Del_Run = 1;
                        while(Pick_Del_Run == 1){
                            System.out.print("\nEnter Line Number to Delete the Path >> ");
                            String UI_DEL_Path = Scanner.nextLine();

                            int Del_Path_INT = Integer.parseInt(UI_DEL_Path);  
                            Del_Path_INT--;
                            int Max_ListSize = File_Paths.size() + 1;

                            if (0 < Del_Path_INT || Del_Path_INT < Max_ListSize){
                                File_Paths.remove(Del_Path_INT);
                                Pick_Del_Run = 0;
                                Del_Run = 0;
                            }else{
                                System.out.println("Invalid Line Number Try Again!");
                            }
                        }
                    }else{
                        System.out.println("Incorrect Pin Try Again!");
                        System.out.println("To Exit Enter -1");
                    }
                }

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

    public static void Printing_List(){
        for(int i = 0; i < File_Paths.size(); i++){
            System.out.print("File " + (i+1) + "\t");
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

    public static void Print_Path(String Path, int Diff){
        int x = Check(Path);
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

    public static int Check(String Path){
        File File3 = new File(Path);
        boolean EXST3 = File3.exists();

        if (EXST3 == true){
            return 1;
        }else{
            return 0;
        }
    }


    public static void Storing_Largest_String(){
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
        biggestPathdiv8 = (int) rnd_db_biggestPathdiv8;
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



}