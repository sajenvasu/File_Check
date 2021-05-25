/*
Features in the Program (List is specified as a Linked List)
1. Read from Files
    a. Read Password_File (This is needed when modifying a list)    ✔
    b. Read PathList_File (This is where the Path of the files are stored)  ✔
2. Menu
    a. Test List    ✔
    b. Add to List  ✔
    c. Delete a path from the List ✔
    d. Clear List Completely ✔
    e. Settings
4. If changes occurred save all the files else skip this step (Use Global Variables to keep track if the files got edited)
    (1) Variable if the List has been moddified
    (2) Pin or Name has been modified

*/

import java.util.*;
import java.io.*;

public class File_Check{

    static String Name;
    static String Pin;
    static int CHG_List = 0;
    static int CHG_PIN = 0;
    static LinkedList<String> File_Paths = new LinkedList<String>();
    static int Longest_String_Length;
    static int Longest_FileNum_Length;

    public static void main(String[] args){
        int Run = 1;

        Read();
        System.out.println("Welcome " + Name + "!\n");
        Scanner Scanner = new Scanner(System.in);

        while (Run == 1){

            System.out.println("----------------------------");
            System.out.println("            Menu"            );
            System.out.println("----------------------------");
            System.out.println("1. Test Paths");
            System.out.println("2. Add a New Path");
            System.out.println("3. Delete an Existing Path");
            System.out.println("4. Clear Entire Path List");
            System.out.println("5. Settings");
            System.out.print("6. Exit\n>> ");
            String choice = Scanner.nextLine();

            if (choice.equals("1")){

                int size = File_Paths.size();
                
                if (size == 0){
                    System.out.println("\nNo Paths to Test");
                }else{
                    System.out.println("\nTesting All Paths\n");
                    Print_Path();
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
                        System.out.println("Path already exists, Try Again!");
                        Add_CheckEXST = 0;
                    }else{
                        System.out.println("Path has been added, Sucessfully!");
                        File_Paths.add(Add_Path);
                        CHG_List = 1;
                        Add_Run = 0;
                    }
                }

            }else if (choice.equals("3")){

                int size = File_Paths.size();
                
                if (size == 0){
                    System.out.println("\nNo Paths to Delete");
                }else{
                 
                    System.out.println("\nDeleting Files\n");
                    Print_Path();
    
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
                                    CHG_List = 1;
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
                }



            }else if (choice.equals("4")){
                
                int size = File_Paths.size();
                
                if (size == 0){
                    System.out.println("\nNo Paths to clear");
                }else{
                    File_Paths.clear();
                }

            }else if (choice.equals("5")){
                int Set_Run = 1;

                while(Set_Run == 1){

                    System.out.println("");
                    System.out.println("----------------------------");
                    System.out.println("          Settings"          );
                    System.out.println("----------------------------");
                    System.out.println("1. Change Name");
                    System.out.println("2. Change Pin");
                    System.out.print("3. Exit\n>> ");
                    String settingschoice = Scanner.nextLine();

                    if (settingschoice.equals("1")){

                        System.out.print("\nEnter New Name >> ");
                        Name = Scanner.nextLine();
                        System.out.println("Name has changed successfully!");
                        CHG_PIN = 1;

                    }else if (settingschoice.equals("2")){
                        int ChangePinRun = 1;
                        
                        while(ChangePinRun == 1){

                            System.out.print("\nEnter User Pin >> ");
                            String temp_pin = Scanner.nextLine();

                            if (temp_pin.equals(Pin)){

                                System.out.print("\nEnter New Pin(1) >> ");
                                String txt1 = Scanner.nextLine();
                                System.out.print("\nEnter New Pin(2) >> ");
                                String txt2 = Scanner.nextLine();

                                if (txt1.equals("-1") || txt2.equals("-1")){
                                    ChangePinRun = 0;
                                }

                                if (txt1.length() == 4 && txt2.length() == 4){
                                    if (txt1.equals(txt2)){
                                        Pin = txt1;
                                        System.out.print("\nPin has been changed succesfully\n");
                                        CHG_PIN = 1;
                                        ChangePinRun = 0;
                                    }else{
                                        System.out.print("Pin does not match\n");
                                    }
                                }else{
                                    System.out.print("Pin must be 4 digits in length!\n");
                                }
                                
                            }else{
                                if (temp_pin.equals("-1")){
                                    ChangePinRun = 0;
                                }
                                System.out.print("Current Pin incorrect, To exit anytime enter -1\n");
                            }
                        }

                    }else if (settingschoice.equals("3")){ 
                        Set_Run = 0; 
                    }else{
                        System.out.println("Invalid Choice!");
                    }
                }
                
            }else if (choice.equals("6")){

                if (CHG_List == 1 && CHG_PIN == 1){
                    Write(null, null, 4);
                }else{

                    if (CHG_List == 1){
                        Write(null, null, 4);
                    }
                    
                    if (CHG_PIN == 1){
                        System.out.println("Code Works");
                        Write(null, null, 4);
                    }
                }

                System.out.println("\nGoodbye " + Name + "!");
                Run = 0;

            }else {

                System.out.println("Invalid Choice!");
                
            }

            System.out.println("");
        }
        Scanner.close();
    }

    public static void Print_Path(){

        // Storing the longest Length of the String
        int size = File_Paths.size();
        int biggestPath = 0;

        for(int i = 0; i < size; i++){
            String temp = File_Paths.get(i);
            if (biggestPath < temp.length()){
                biggestPath = temp.length();
            }
        }
        Longest_String_Length = biggestPath;
        Longest_FileNum_Length = String.valueOf(File_Paths.size() - 1).length();

        
        // Printing the File Path
        // Future Update! : Add Labels like (Path #) (Path Name) (Present) on top of the prints
        for(int i = 0; i < File_Paths.size(); i++){

            int Current_FileNum_Length = String.valueOf(i).length();

            System.out.print("Path " + (i + 1));
            for(int j = 0; j < (Longest_FileNum_Length + 2) - Current_FileNum_Length; j++){
                System.out.print(" ");
            }
            System.out.print("\t"); // Temporary Fix : Remove and look at lines 10, 100, 1000 in the runtime program

            String Path = File_Paths.get(i);
            int Path_Size = Path.length();
            int x = Check(Path);
            System.out.print(Path);

            for(int k = 0; k < Longest_String_Length - Path_Size; k++){
                System.out.print(" ");
            }

            if (x == 1){
                System.out.println("\t\tPresent: Yes");
            }else if (x == 0){
                System.out.println("\t\tPresent: No");
            }
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

    public static void Read(){
        
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
                Scanner Scanner = new Scanner(System.in);

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
                Scanner.close();
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

        }else if (key == 2){

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("File Check Program Files" + File.separator + "path.txt"))){
                writer.close();
            }catch (Exception ex){
                System.out.println("Error: Code 1002\n");
            }

        }else if (key == 3){
            
            int size = File_Paths.size(); 

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("File Check Program Files" + File.separator + "path.txt"))){
                writer.write("");
            }catch (Exception ex){
                System.out.println("Error: Code 1003\n");
            }

            if (size == 0){
                
            }else{
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("File Check Program Files" + File.separator + "path.txt"))){
                    for(int i = 0; i < size; i++){
                        writer.append(File_Paths.get(i) + "\n");
                    }
                }catch (Exception ex){
                    System.out.println("Error: Code 1003\n");
                }

            }
            
        }else if (key == 4){

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("File Check Program Files" + File.separator + "pin.txt"))){
                writer.write( Name + ";" + Pin);
            }catch (Exception ex){
                System.out.println("Error: Code 1003\n");
            }   

        }
    }

}