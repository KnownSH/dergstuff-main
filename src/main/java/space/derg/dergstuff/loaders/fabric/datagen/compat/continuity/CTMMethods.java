//? if fabric && continuity {
package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

public enum CTMMethods {
    CTM,
    CTM_COMPACT,
    HORIZONTAL,
    VERTICAL,
    HORIZONTAL_VERTICAL,
    VERTICAL_HORIZONTAL,
    TOP,
    RANDOM,
    REPEAT,
    FIXED,
    OVERLAY_CTM,
    OVERLAY_RANDOM,
    OVERLAY_REPEAT,
    OVERLAY_FIXED;

    public String get() {
        return switch (this) {
            case HORIZONTAL_VERTICAL -> "horizontal+vertical";
            case VERTICAL_HORIZONTAL -> "vertical+horizontal";
            default -> this.toString().toLowerCase();
        };
    }
}
//?}
