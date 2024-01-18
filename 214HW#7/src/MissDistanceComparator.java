/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#7
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Comparator class for the miss distance statistic.
 */
public class MissDistanceComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * Compares the object based on the miss distance.
     * @param o1
     *  NEO one.
     * @param o2
     *  NEO two.
     * @return
     *  Returns if it less, equal to, or greater than.
     * @Override
     */
    public int compare(NearEarthObject o1, NearEarthObject o2) {
        if (o1.getMissDistance() == o2.getMissDistance())
            return 0;
        else if (o1.getMissDistance() > o2.getMissDistance())
            return 1;
        else
            return -1;
    }
}
