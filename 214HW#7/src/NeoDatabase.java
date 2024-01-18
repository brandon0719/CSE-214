/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#7
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * The database that uses an arraylist to store the objects.
 */
import java.util.*;
import big.data.DataSource;
public class NeoDatabase {
    public static final String API_KEY = "GL6VorlaNRXMoXaXhvpICbVKjp2mJJGoJiMsceAm";
    public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";
    private static ArrayList<NearEarthObject> list;
    /**
     * Constructor for the neo database. 
     */
    public NeoDatabase() {
        list = new ArrayList<>();
    }
    /**
     * Creates a query URL for the given page number.
     * @param pageNumber
     *  A page number of database.
     * @return
     *  A query URL.
     * @throws IllegalArgumentException
     *  Throws an exception if the page number is not within the valid range.
     */
    public String buildQueryURL(int pageNumber) throws IllegalArgumentException {
        if(pageNumber < 0 || pageNumber > 715) throw new IllegalArgumentException();
        return (API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY);
    }
    /**
     * Adds all of the data extracted from the database by connecting to the page and loading it.
     * @param queryURL
     *  A queryURL generated by buildQueryURL().
     * @throws IllegalArgumentException
     *  Throws an exception if the URL is null.
     */
    public void addAll(String queryURL) throws IllegalArgumentException {
        if(queryURL.equals(null)) throw new IllegalArgumentException("URL is null.");
        DataSource ds = DataSource.connectJSON(queryURL);
        ds.load();
        NearEarthObject[] nearEarthObjects = ds.fetchArray("NearEarthObject", "near_earth_objects/neo_reference_id", "near_earth_objects/name", 
        "near_earth_objects/absolute_magnitude_h", "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min", 
        "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max", "near_earth_objects/is_potentially_hazardous_asteroid", 
        "near_earth_objects/close_approach_data/epoch_date_close_approach", 
        "near_earth_objects/close_approach_data/miss_distance/kilometers", "near_earth_objects/close_approach_data/orbiting_body");
        for(NearEarthObject x: nearEarthObjects) {
            list.add(x);
        }
    }
    /**
     * Sorts the database based on the inputted comparator.
     * @param comp
     *  Comparator object.
     * @throws IllegalArgumentException
     *  Throws an exception if comp is null.
     */
    public void sort(Comparator<NearEarthObject> comp) throws IllegalArgumentException {
        if(comp == null) throw new IllegalArgumentException();
        list.sort(comp);
        //Collections.sort(list,comp); // SAME^
    }
    /**
     * Prints a table of all of the pulled objects from the database.
     */
    public void printTable() {
        System.out.println();
        System.out.println("  ID   |           Name            | Mag. | Diameter | Danger | Close Date | Miss Dist | Orbits");
        System.out.println("================================================================================================");
        for(int i = 0; i < list.size(); i++) {
            NearEarthObject obj = list.get(i);
            System.out.printf("%-9d%-26s  %-8.1f%-10.3f%-9b%-13s%-12.0f%-7s\n", obj.getReferenceID(), obj.getName(),
            obj.getAbsoluteMagnitude(), obj.getAverageDiameter(), obj.isDangerous(), obj.formattedDate(obj.getClosestApproachDate()), 
            obj.getMissDistance(), obj.getOrbitingBody());
        }
    }
} 
