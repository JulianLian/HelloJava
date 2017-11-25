package guitar;

public enum Builder {
    FENDER, MARTIN, GIBSON, COLLINS, OLSON, RYAN, PRS, ANY;

    @Override
    public String toString() {
        switch (this) {
            case ANY:
                return "any";
            case COLLINS:
                return "collins";
            case FENDER:
                return "fender";
            case GIBSON:
                return "gibson";
            case MARTIN:
                return "martin";
            case OLSON:
                return "olson";
            case PRS:
                return "prs";
            case RYAN:
                return "ryan";
            default:
                return "";

        }
    }
}
