package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

public enum Faces {
    BOTTOM,
    TOP,
    NORTH,
    SOUTH,
    EAST,
    WEST,
    SIDES,
    ALL;

    public String get() {
        return this.toString().toLowerCase();
    }
}
