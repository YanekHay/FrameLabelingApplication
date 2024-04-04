package core;
import java.util.UUID;
public class FLAAnnotation2D{
    private int label;
    private String labelName;
    private final UUID id;

    public FLAAnnotation2D() {
        this.id = UUID.randomUUID();
    }

    public FLAAnnotation2D(int label, String labelName) {
        this.label = label;
        this.id = UUID.randomUUID();
        this.labelName = labelName;
    }

    public FLAAnnotation2D(int label) {
        this.id = UUID.randomUUID();
        this.label = label;
        this.labelName = "";
    }

    public int getLabel() {
        return label;
    }

    public String getLabelName() {
        return labelName;
    }

    public String getId() {
        return id.toString();
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

}