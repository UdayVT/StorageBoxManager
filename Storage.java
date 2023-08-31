/**
 * a fully documented class named Storage that contains three private data 
 * fields: int id, String client, String contents, along with public getter 
 * and setter methods for each of these fields. This class will be used to 
 * represent a storage box registered with the company.
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #6 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    April 15th, 2023
 */
import java.io.Serializable;
 public class Storage implements Serializable
{
    private int id;//id of the storage
    private String client;//the client of the storage unit
    private String contents;//the contents in the storage unit

    /**
     * Arg-constructor of Storage
     * @param id
     *      the id of the storage
     * @param client
     *      the client of the storage
     * @param contents
     *      the contents of the storage
     */
    public Storage(int id, String client, String contents)
    {
        this.id = id;
        this.client = client;
        this.contents = contents;
    }

    /**
     * getter of Id
     * @return
     *      storage's id
     */
    public int getId() 
    {
        return id;
    }

    /**
     * Getter of Clients
     * @return
     *      storage's client 
     */
    public String getClient() 
    {
        return client;
    }

    /**
     * Getter of Contents
     * @return
     *      storage's contents
     */
    public String getContents() 
    {
        return contents;
    }

    /**
     * A toString method for Storage
     * @return
     *      the storage in a neat formated method
     */
    public String toString()
    {
        return String.format("%-14d%-31s%s", id,contents,client);
    }
}