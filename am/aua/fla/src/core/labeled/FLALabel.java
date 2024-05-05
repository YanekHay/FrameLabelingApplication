package core.labeled;
import java.util.UUID;

public class FLALabel implements ILabeled, Cloneable{
    private int classNumber;
    private String className;
    private UUID id;

    public FLALabel(int classNumber, String className) {
        this.classNumber = classNumber;
        this.id = UUID.randomUUID();
        this.className = className;
    }

    public FLALabel(int classNumber) {
        this(classNumber, "unknown");
    }

    @Override
    public int getClassNumber() {
        return this.classNumber;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getId() {
        return id.toString();
    }

    @Override
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public FLALabel clone(){
        try {
            FLALabel clone = (FLALabel) super.clone();
            clone.id = UUID.randomUUID();
            clone.classNumber = this.classNumber;
            clone.className = this.className;

            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
