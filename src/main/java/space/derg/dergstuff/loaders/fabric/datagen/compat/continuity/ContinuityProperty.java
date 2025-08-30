//? if fabric && continuity {
package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

import net.minecraft.world.level.block.Block;

public class ContinuityProperty implements Cloneable {
    public final Block block;
    public final OptifineMethods method;
    public String nameSuffix = "";
    public String connect;
    public String tiles;
    public String suffixes;
    public Faces[] faces;

    public ContinuityProperty(Block block, OptifineMethods method) {
        this.block = block;
        this.method = method;
    }

    public ContinuityProperty tiles(String... tiles) {
        this.tiles = String.join(" ", tiles);
        return this;
    }

    public ContinuityProperty customMatch(String... suffixes) {
        this.suffixes = String.join(" ", suffixes);
        return this;
    }

    public ContinuityProperty nameSuffix(String suffix) {
        this.nameSuffix = suffix;
        return this;
    }

    public ContinuityProperty faces(Faces... faces) {
        this.faces = faces;
        return this;
    }

    public ContinuityProperty connect(CTMConnects connect) {
        this.connect = connect.get();
        return this;
    }

    @Override
    public ContinuityProperty clone() {
        try {
            return (ContinuityProperty) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
//?}