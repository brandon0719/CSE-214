/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#6
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * A hash map of that contains a storage table.
 */
import java.io.Serializable;
import java.util.HashMap;
public class StorageTable extends HashMap<Integer, Storage> implements Serializable {
    HashMap<Integer, Storage> storageTable = new HashMap<>();
    /**
     * This adds a storage box to the hash table.
     * @param storageId
     *  The key of the table.
     * @param storage
     *  The storage box.
     */
    public void putStorage(int storageId, Storage storage) {
        if(storage == null) throw new IllegalArgumentException();
        storageTable.put(storageId, storage);
    }
    /**
     * Gets a reference the storage box based on the ID.
     * @param storageId
     *  The ID of the storage box.
     * @return
     *  Returns storage box with the inputted storage ID.
     */
    public Storage getStorage(int storageId) {
        return storageTable.get(storageId);
    }
    /**
     * @return
     *  Returns a reference of the storage table.
     */
    public HashMap<Integer,Storage> getStorageTable() {
        return storageTable;
    }
    /**
     * Prints a formatted string of the whole table. 
     */
    public void printTable() {
        for(int key: storageTable.keySet()) {
            System.out.println(getStorage(key).toString());
        }
    }
    /**
     * Prints a formatted string of the whole table. 
     * @param owner
     *  A string of the owner's name.
     */
    public void printTable(String owner) {
        for(int key: storageTable.keySet()) {
            if(getStorage(key).getClient().equals(owner)) {
                System.out.println(getStorage(key).toString());
            }
        }
    }
}
