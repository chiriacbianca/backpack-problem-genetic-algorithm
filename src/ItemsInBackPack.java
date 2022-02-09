public class ItemsInBackPack {

    public int weight;
    public int value;

    public ItemsInBackPack(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ItemsInBagPack{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
