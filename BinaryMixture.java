public class BinaryMixture {

    private ChemicalSpecies moreVolatileSpecies; // make an array?
    private ChemicalSpecies lessVolatileSpecies;
    private double latentHeat;


    public BinaryMixture(ChemicalSpecies firstChemicalSpecies, ChemicalSpecies secondChemicalSpecies, double latentHeat) {
        boolean firstMostVolatile = (firstChemicalSpecies.getNormalBoilingPoint() < secondChemicalSpecies.getNormalBoilingPoint());
        if (firstMostVolatile) {
            this.moreVolatileSpecies = new ChemicalSpecies(firstChemicalSpecies);
            this.lessVolatileSpecies = new ChemicalSpecies(secondChemicalSpecies);
        }
        else {
            this.moreVolatileSpecies = new ChemicalSpecies(secondChemicalSpecies);
            this.lessVolatileSpecies = new ChemicalSpecies(firstChemicalSpecies);
        }
        this.latentHeat = latentHeat;
    }
    public BinaryMixture(BinaryMixture source) {
        this.moreVolatileSpecies = new ChemicalSpecies(source.moreVolatileSpecies);
        this.lessVolatileSpecies = new ChemicalSpecies(source.lessVolatileSpecies);
        this.latentHeat = source.latentHeat;
    }

    public ChemicalSpecies getMoreVolatileSpecies() {
        return new ChemicalSpecies(moreVolatileSpecies);
    }
    public void setMoreVolatileSpecies(ChemicalSpecies moreVolatileSpecies) {
        this.moreVolatileSpecies = new ChemicalSpecies(moreVolatileSpecies);
    }
    public ChemicalSpecies getLessVolatileSpecies() {
        return new ChemicalSpecies(lessVolatileSpecies);
    }
    public void setLessVolatileSpecies(ChemicalSpecies lessVolatileSpecies) {
        this.lessVolatileSpecies = new ChemicalSpecies(lessVolatileSpecies);
    }
    public double getLatentHeat() {
        return latentHeat;
    }
    public void setLatentHeat(double latentHeat) {
        this.latentHeat = latentHeat;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        BinaryMixture that = (BinaryMixture) otherObject;
        return (this.latentHeat == that.latentHeat) &&
                moreVolatileSpecies.equals(that.moreVolatileSpecies) &&
                lessVolatileSpecies.equals(that.lessVolatileSpecies);
    }


}
