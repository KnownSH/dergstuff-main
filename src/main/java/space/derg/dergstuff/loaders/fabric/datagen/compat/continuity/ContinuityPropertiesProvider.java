//? if fabric && continuity {
package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class ContinuityPropertiesProvider implements DataProvider {
    private final List<ContinuityProperty> continuityProperties = new ArrayList<>();
    private final FabricDataOutput output;

    public ContinuityPropertiesProvider(FabricDataOutput output) {
        this.output = output;
    }


    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.runAsync(() -> {

        });
    }

    public ContinuityPropertiesProvider ctm(ContinuityProperty continuityProperty) {
        this.continuityProperties.add(continuityProperty);
        return this;
    }

    public abstract void generate();

    @Override
    public String getName() {
        return "Continuity Properties Provider";
    }
}
//?}