public class ChemicalSpecies {

    private double heatCapacity;
    private double normalBoilingPoint;

    public ChemicalSpecies(double heatCapacity, double normalBoilingPoint) {
        this.heatCapacity = heatCapacity;
        this.normalBoilingPoint = normalBoilingPoint;
    }
    public ChemicalSpecies(ChemicalSpecies source) {
        this.heatCapacity = source.heatCapacity;
        this.normalBoilingPoint = source.normalBoilingPoint;
    }

    public double getHeatCapacity() {
        return heatCapacity;
    }
    public void setHeatCapacity(double heatCapacity) {
        this.heatCapacity = heatCapacity;
    }
    public double getNormalBoilingPoint() {
        return normalBoilingPoint;
    }
    public void setNormalBoilingPoint(double normalBoilingPoint) {
        this.normalBoilingPoint = normalBoilingPoint;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == this) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        ChemicalSpecies that = (ChemicalSpecies) otherObject;
        return (this.normalBoilingPoint == that.normalBoilingPoint)
                && (this.heatCapacity == that.heatCapacity);
    }

}
