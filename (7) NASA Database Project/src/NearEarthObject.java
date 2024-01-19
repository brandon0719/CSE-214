/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#7
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * This class emulates an object of a near earth object. It has attributes of one, such as a reference ID, name, and its statistics.
 */
import java.util.Date;
import java.text.SimpleDateFormat;
public class NearEarthObject {
    private int referenceID;
    private String name;
    private double absoluteMagnitude;
    private double averageDiameter;
    private boolean isDangerous;
    private Date closestApproachDate;
    private double missDistance;
    private String orbitingBody;
    /**
     * Constructor that initializes a near earth object. 
     * @param referenceID
     *  The ID of the NEO.
     * @param name
     *  The name of the NEO.
     * @param absoluteMagnitude
     *  The magnitude of the NEO.
     * @param minDiameter
     *  The minimum diameter of the NEO.
     * @param maxDiameter
     *  The maxiumum diamter of the NEO.
     * @param isDangerous
     *  Boolean: Is the NEO Dangerous.
     * @param closestDateTimestamp
     *  The date timestamp of the NEO.
     * @param missDistance
     *  The miss distance of the NEO.
     * @param orbitingBody
     *  The orbitingBody of the neo
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter, 
    double maxDiameter, boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody) {
        this.referenceID = referenceID;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.averageDiameter = (maxDiameter + minDiameter) / 2;     //average
        this.isDangerous = isDangerous;
        this.closestApproachDate  = new Date(closestDateTimestamp);
        this.missDistance = missDistance;
        this.orbitingBody = orbitingBody;
    }
    /**
     * Gets the reference ID of the NEO.
     * @return
     *  The reference ID.
     */
    public int getReferenceID() {
        return referenceID;
    }
    /**
     * Sets the reference ID of the NEO.
     * @param referenceID
     *  The reference ID.
     */
    public void setReferenceID(int referenceID) {
        this.referenceID = referenceID;
    }
    /**
     * Gets the name of the NEO.
     * @return
     *  The name.
     */
    public String getName() {
        if(name.length() <= 26)
            return name;
        else
            return name.substring(0,26);
    }
    /**
     * Sets the name of the NEO.
     * @param name
     *  The name of the neo.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return
     *  The absolute magnitude of the NEO.
     */
    public double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }
    /**
     * Sets a new absolute magnitude for the NEO.
     * @param absoluteMagnitude
     *  New absolute magnitude.
     */
    public void setAbsoluteMagnitude(double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }
    /**
     * @return
     *  The average diameter of the NEO.
     */
    public double getAverageDiameter() {
        return averageDiameter;
    }
    /**
     * Sets the average diamter of the NEO.
     * @param averageDiameter
     *  The new average diameter.
     */
    public void setAverageDiameter(double averageDiameter) {
        this.averageDiameter = averageDiameter;
    }
    /**
     * @return
     *  If the NEO is dangerous.
     */
    public boolean isDangerous() {
        return isDangerous;
    }
    /**
     * Sets the isDangerous statistic for the NEO.
     * @param isDangerous
     *  The new isDangerous.
     */
    public void setDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }
    /**
     * @return
     *  The closest approach date.
     */
    public Date getClosestApproachDate() {
        return closestApproachDate;
    }
    /**
     * Changes the closest approach date of the NEO.
     * @param closestApproachDate
     *  The new approach date.
     */
    public void setClosestApproachDate(Date closestApproachDate) {
        this.closestApproachDate = closestApproachDate;
    }
    /**
     * @return
     *  The miss distance of the NEO.
     */
    public double getMissDistance() {
        return missDistance;
    }
    /**
     * Changes the miss distance of the NEO.
     * @param missDistance
     *  The new miss distance.
     */
    public void setMissDistance(double missDistance) {
        this.missDistance = missDistance;
    }
    /**
     * @return
     *  The orbiting body of the NEO.
     */
    public String getOrbitingBody() {
        return orbitingBody;
    }
    /**
     * Changes the orbiting body of the NEO.
     * @param orbitingBody
     *  The new orbiting body.
     */
    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }
    /**
     * Formats the date inputted to MM/DD/YYYY.
     * @param date
     *  A date object.
     * @return
     *  The formatted version of date.
     */
    public String formattedDate(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy");
        String output = outputFormat.format(date);
        return output;
    }
    /**
     * Formats the date inputted to YYYY/MM/DD, so that it can be sorted by year.
     * @param date
     *  A date object.
     * @return
     *  Returns the formatted version of date.
     */
    public String compareApproachDates(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("YYYY-MM-DD");
        String output = outputFormat.format(date);
        return output;
    }
}
