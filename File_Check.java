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

public class File_Check{

    public static void main(String[] args){

        // File F = new File();
        
        int Run = 1;

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
        
    }

}
