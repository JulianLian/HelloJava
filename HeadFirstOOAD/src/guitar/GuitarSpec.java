package guitar;

public class GuitarSpec {
    private Builder builder;
    private String  model;
    private Type type;

    public int getNumStrings() {
        return numStrings;
    }

    public void setNumStrings(int numStrings) {
        this.numStrings = numStrings;
    }

    private int numStrings;
    private Wood backWood, topWood;

    public GuitarSpec(Builder builder, String model, Type type, int numStrings, Wood backWood, Wood topWood) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.numStrings = numStrings;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Wood getBackWood() {
        return backWood;
    }

    public void setBackWood(Wood backWood) {
        this.backWood = backWood;
    }

    public Wood getTopWood() {
        return topWood;
    }

    public void setTopWood(Wood topWood) {
        this.topWood = topWood;
    }

    boolean matches(GuitarSpec otherSpec) {
        if (otherSpec.getBuilder() != getBuilder()) {
            return false;
        }

        if (otherSpec.getModel() != null && !otherSpec.getModel().equals("")
                && !otherSpec.getModel().equals(getModel())) {
            return false;
        }

        if (otherSpec.getType() != getType()) {
            return false;
        }

        if (otherSpec.getBackWood() != getBackWood()) {
            return false;
        }

        if (otherSpec.getTopWood() != getTopWood()) {
            return false;
        }
        return true;
    }
}
