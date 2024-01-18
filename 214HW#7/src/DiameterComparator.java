/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#7
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Comparator class for the diameter statistic.
 */
public class DiameterComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * Compares the object based on diameter.
     * @param o1
     *  NEO one.
     * @param o2
     *  NEO two.
     * @return
     *  Returns if it less, equal to, or greater than.
     * @Override
     */
    public int compare(NearEarthObject o1, NearEarthObject o2) {
        if (o1.getAverageDiameter() == o2.getAverageDiameter())
            return 0;
        else if (o1.getAverageDiameter() > o2.getAverageDiameter())
            return 1;
        else
            return -1;
    }
}
