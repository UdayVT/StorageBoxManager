/**
 * a fully-documented class named StorageManager. This class will allow the user to
 * interact with the storage database by listing the storage boxes occupied, allowing 
 * the user to add or remove storage boxes, searching for a box by id, and listing all the 
 * boxes for a user. In addition, the class should provide the functionality to load a 
 * saved (serialized) StorageTable or create a new one if a saved table does not exist.
 * The user can use the commands below to perform operations on <CODE>StorageTable</CODE> object.
 * <dt><b>Commands:</b><dd> 
 *  P - Print all storage boxes
    A - Insert into storage box
    R - Remove contents from a storage box
    C - Select all boxes owned by a particular client
    F - Find a box by ID and display its owner and contents
    Q - Quit and save workspace
    X - Quit and delete workspace
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #6 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    April 15th, 2023
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class StorageManager 
{
    private static StorageTable sTable;//The storage tbale
    private static Scanner input = new Scanner(System.in);//for input

    /**
     * A helper method used to take interger input
     * @return
     */
    public static int nextInt()
    {
        int answer = input.nextInt();
        input.nextLine();
        return answer;
    }

    /**
     * Main function
     */
    public static void main(String[] args)
    {
        File file = new File("storage.obj");
        if(file.exists())
        {
            try 
            {
                FileInputStream fileInput = new FileInputStream(file);
                ObjectInputStream inStream = new ObjectInputStream(fileInput);
                sTable = (StorageTable) inStream.readObject();    
                inStream.close();
                fileInput.close();
            } catch (Exception e) 
            {
                System.out.println("An Error occured trying to read the file.");
            }   
        }
        else
        {
            sTable = new StorageTable();
        }

       

        System.out.println("Hello, and welcome to Rocky Stream Storage Manager");
        System.out.println();
        
        while(true)
        {
            System.out.println("\nP - Print all storage boxes\n"
            +"A - Insert into storage box\n"
            +"R - Remove contents from a storage box\n"
            +"C - Select all boxes owned by a particular client\n"
            +"F - Find a box by ID and display its owner and contents\n"
            +"Q - Quit and save workspace\n"
            +"X - Quit and delete workspace\n");

            System.out.print("Please select an option: ");
            char inp = input.nextLine().toUpperCase().charAt(0);
            System.out.println();
            if(inp == 'P')
            {
                System.out.println(sTable);
            }
            else if(inp == 'A')
            {
                System.out.print("Please enter id: ");
                int id = nextInt();
                System.out.print("Please enter client: ");
                String client = input.nextLine();
                System.out.print("Please enter contents: ");
                String contents = input.nextLine();

                Storage s = new Storage(id, client, contents);
                try
                {
                    sTable.putStorage(id, s);
                    System.out.println("\nStorage "+id+" set!");
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage()+"\n");
                }
                
            }
            else if(inp == 'R')
            {
                System.out.print("Please enter ID: ");
                int id = nextInt();

                Storage s = sTable.remove(id);
                if(s == null)
                    System.out.println("No storage exists with that id");
                else
                    System.out.println("Box "+id+" is now removed");
            }
            else if(inp == 'C')
            {
                System.out.print("Please enter client: ");
                String client = input.nextLine();
                System.out.println(sTable.findAndPrint(client));
            }
            else if(inp == 'F')
            {
                System.out.print("Please enter ID: ");
                int id = nextInt();

                Storage s = sTable.getStorage(id);
                if(s == null)
                    System.out.println("No storage exists with that id");
                else
                    System.out.println("Box "+id+"\nContents: "+s.getContents()+"\nOwner: "+s.getClient()+"\n");
            }
            else if(inp == 'Q')
            {

                try 
                {
                    if(!file.exists())
                        file.createNewFile();

                    FileOutputStream fileOutput = new FileOutputStream("storage.obj");
                    ObjectOutputStream outStream = new ObjectOutputStream(fileOutput);
                    outStream.writeObject(sTable);
                    outStream.close();
                    fileOutput.close();
                    System.out.println("Storage Manager is quitting, current storage is saved for next session.");
                } catch (IOException e) 
                {
                    System.out.println("Some error occured while saving data!!!");
                }
                
                break;
            }
            else if(inp == 'X')
            {
                System.out.println("Storage Manager is quitting, all data is being erased.");
                break;
            }
            else 
            {
                System.out.println("Invalid input, please try again");
            }
        }

        input.close();
    }
}