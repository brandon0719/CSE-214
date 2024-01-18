/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#7
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Comparator class for the approach date statistic.
 */
public class ApproachDateComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * Compares the object based on the approach date.
     * @param o1
     *  NEO one.
     * @param o2
     *  NEO two.
     * @return
     *  Returns if it less, equal to, or greater than.
     * @Override
     */
    public int compare(NearEarthObject o1, NearEarthObject o2) {
        return (o1.compareApproachDates(o1.getClosestApproachDate()).compareTo(o2.compareApproachDates(o2.getClosestApproachDate())));
    }
}
