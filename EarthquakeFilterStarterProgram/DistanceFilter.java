/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {

    private Location location;
    private double maxDistance;
    
    public DistanceFilter(Location loc, double maxDist) {
        location = new Location(loc);
        // this.maxDistance = mD;
        maxDistance = maxDist;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        Location qeLoc = qe.getLocation();
        return qeLoc.distanceTo(location) < maxDistance;
    }
     public String getName(){
        return "DistanceFilter";
    }
}