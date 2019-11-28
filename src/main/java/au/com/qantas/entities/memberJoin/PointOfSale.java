package au.com.future-airlines.entities.memberJoin;

/**
 * Created by boses on 29/08/2017.
 */
public class PointOfSale {

    public String staffId;
    public String pointOfInjection;

    public PointOfSale() {
    }

    public PointOfSale(String staffId, String pointOfInjection) {
        this.staffId = staffId;
        this.pointOfInjection = pointOfInjection;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPointOfInjection() {
        return pointOfInjection;
    }

    public void setPointOfInjection(String pointOfInjection) {
        this.pointOfInjection = pointOfInjection;
    }
}
