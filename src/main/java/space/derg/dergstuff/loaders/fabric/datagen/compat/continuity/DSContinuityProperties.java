package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import space.derg.dergstuff.registry.DSBlocks;

public class DSContinuityProperties extends ContinuityPropertiesProvider {
    public DSContinuityProperties(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        ContinuityProperty shelfProperty = new ContinuityProperty(DSBlocks.COMMERCIAL_SHELF.get(), OptifineMethods.HORIZONTAL)
                .tiles("0-3").faces(Faces.NORTH).connect(CTMConnects.BLOCK);
        this.facingCtms(shelfProperty, Faces.NORTH, Faces.EAST, Faces.SOUTH, Faces.WEST);
    }
}