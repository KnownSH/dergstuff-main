package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import space.derg.dergstuff.registry.DSBlocks;

public class DSContinuityProperties extends ContinuityPropertiesProvider {
    public DSContinuityProperties(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        this.ctm(new ContinuityProperty(DSBlocks.COMMERCIAL_SHELF.get(), OptifineMethods.HORIZONTAL));
    }
}