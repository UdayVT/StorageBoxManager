/**
 * The database of Storages will be stored in a hash table to provide 
 * constant time insertion and deletion. Use the id of Storage 
 * objects as the key for hashing
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #6 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    April 15th, 2023
 */
import java.util.HashMap;
public class StorageTable extends HashMap<Integer,Storage>
{
    /**
     * Default Constructor
     */
    public StorageTable()
    {
        super();
    }

    /**
     * Manually inserts a Storage object into the table using the specified key.
     * @param storageId
     *      The unique key for the Storage object.
     * @param s
     *      The Storage object to insert into the table.
     * @custom.Precondition
     *      storageId ≥ 0 and does not already exist in the table.
     *      Storage ≠ null
     * @custom.Postcondition
     *      The Storage has been inserted into the table with the indicated key.
     * @throws IllegalArgumentException
     *      If any of the preconditions is not met.
     */
    public void putStorage(int storageId, Storage s) throws IllegalArgumentException
    {
        //Precondition check 1
        if(super.containsKey(storageId))
            throw new IllegalArgumentException("Same id already exists in the table");
        //Precondition check 2
        if(s== null)
            throw new IllegalArgumentException("Storage class cannot be null");
        //Precondition check 3
        if(storageId < 0)
            throw new IllegalArgumentException("Id cannot be less than 0");

        super.put(storageId, s);
    }
    
    /**
     * Retrieve the Storage from the table having the indicated storageID. 
     * If the requested storageID does not exist in the StorageTable, return null.
     * @param storageId
     *      Key of the Storage to retrieve from the table.
     * @return
     *      A Storage object with the given key, null otherwise.
     */
    public Storage getStorage(int storageId)
    {
        return super.get(storageId);
    }

    /**
     * A toString method which prints the StorageTable in a neatly formatted manner
     * @return
     *      a String of StorageTable
     */
    public String toString()
    {
        String answer = "";

        answer += "Box#          Contents                       Owner\n";
        answer += "----------------------------------------------------------------\n";
        for (Storage s : this.values())
            answer += s.toString() + "\n";

        return answer;
    }

    /**
     * A method that would find and print all storage from a client
     * @param client
     *      The client
     * @return
     *      a neatly formatted table of all storage from the client
     */
    public String findAndPrint(String client)
    {
        String answer = "";

        answer += "Box#          Contents                       Owner\n";
        answer += "----------------------------------------------------------------\n";
        for(Storage s : this.values())
            if(s.getClient().equals(client))
                answer += s.toString() + "\n";

        return answer;
    }
}