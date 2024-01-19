public class Cargo {
    private String name;
    private int weight;
    private CargoStrength strength;
    public Cargo(String initName, int initWeight, CargoStrength initStrength) throws IllegalArgumentException {
        if(initName == null) throw new IllegalArgumentException("Name cannot be null");
        if(initWeight <= 0) throw new IllegalArgumentException("Weight cannot be <= 0");
        this.name = initName;
        this.weight = initWeight;
        this.strength = initStrength;
    }
    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public CargoStrength getStrength() {
        return strength;
    }
    public String printStrength() {
        if(strength == CargoStrength.F)
            return "FRAGILE";
        else if(strength == CargoStrength.M)
            return "MODERATE";
        else
            return "STURDY";
    }
}
