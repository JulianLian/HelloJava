package guitar;

public enum Wood {
    INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE,
    COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA;

    @Override
    public String toString() {
        switch (this) {
            case ALDER:
                return "alder";
            default:
                return "...";
        }
    }
}
