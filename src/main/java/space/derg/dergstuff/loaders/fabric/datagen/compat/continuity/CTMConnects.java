package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

public enum CTMConnects {
    BLOCK,
    TILE,
    STATE;

    public String get() {
        return this.toString().toLowerCase();
    }
}
