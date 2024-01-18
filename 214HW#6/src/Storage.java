/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#6
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * A storage box containing an ID, client, and contents.
 */
import java.io.Serializable;
public class Storage implements Serializable{
    private int id;
    private String client;
    private String contents;
    /**
     * This constructor intializes the storage object and gives it an ID, client name, and content details.
     * @param id
     *  The ID of the storage box.
     * @param client
     *  The string of the client's name.
     * @param contents
     *  A string of the contents of the storage box.
     */
    public Storage(int id, String client, String contents) {
        this.id = id;
        this.client = client;
        this.contents = contents;
    }
    /**
     * @return
     *  Returns the ID of the storage boc.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the storage box to a different number.
     * @param id
     *  The new ID of the box. 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return
     *  Returns the client's name.
     */
    public String getClient() {
        return client;
    }
    /**
     * Sets the clients name to a new name.
     * @param client
     *  The new name of the client.
     */
    public void setClient(String client) {
        this.client = client;
    }
    /**
     * @return
     *  Returns the contents of the storage box.
     */
    public String getContents() {
        return contents;
    }
    /**
     * Sets the contents of the storage box.
     * @param contents
     *  Returns the contents of the box.
     */
    public void setContents(String contents) {
        this.contents = contents;
    }
    /**
     * @return
     *  Returns a formatted string that includes the ID, contents, and clients.
     */
    public String toString() {
        String result = String.format("%-13d%-32s%-19s", id, contents, client);
        return result;
    }
}
