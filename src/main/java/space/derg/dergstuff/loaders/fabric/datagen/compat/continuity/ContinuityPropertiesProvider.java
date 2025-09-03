//? if fabric && continuity {
package space.derg.dergstuff.loaders.fabric.datagen.compat.continuity;

import com.google.common.hash.HashCode;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import space.derg.dergstuff.loaders.fabric.datagen.TextureSpliterProvider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public abstract class ContinuityPropertiesProvider implements DataProvider {
    private final List<ContinuityProperty> continuityProperties = new ArrayList<>();
    private final FabricDataOutput output;

    public ContinuityPropertiesProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        this.generate();
        return CompletableFuture.runAsync(() -> {
            this.continuityProperties.forEach(property -> {
                ResourceLocation blockLocation = BuiltInRegistries.BLOCK.getKey(property.block);

                Path propertiesOutput = TextureSpliterProvider.getOptifineCTMDir(
                        this.output, BuiltInRegistries.BLOCK.getKey(property.block).withSuffix(property.textureSuffix))
                        .resolve(blockLocation.getPath() + property.nameSuffix + ".properties");
                File propertiesFile = propertiesOutput.toFile();

                try (FileWriter fileWriter = new FileWriter(propertiesFile)){
                    PrintWriter bufferedWriter = new PrintWriter(fileWriter);
                    bufferedWriter.println("method=" + property.method.get());
                    bufferedWriter.println("matchBlocks=" + Objects.requireNonNullElse(property.suffixes, blockLocation));

                    if (property.tiles != null) {
                        bufferedWriter.println("tiles=" + property.tiles);
                    }

                    if (property.faces != null) {
                        String facesString = Arrays.stream(property.faces)
                                .map(Faces::get)
                                .collect(Collectors.joining(" "));
                        bufferedWriter.println("faces=" + facesString);
                    }

                    if (property.connect != null) {
                        bufferedWriter.println("connect=" + property.connect);
                    }

                    bufferedWriter.close();

                    byte[] buffer = Files.readAllBytes(propertiesFile.toPath());
                    output.writeIfNeeded(propertiesOutput, buffer, HashCode.fromBytes(buffer));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    public ContinuityPropertiesProvider ctm(ContinuityProperty continuityProperty) {
        this.continuityProperties.add(continuityProperty);
        return this;
    }

    public ContinuityPropertiesProvider facingCtms(ContinuityProperty continuityProperty, Faces... faces) {
        for (Faces face : faces) {
            ContinuityProperty clonedProperty = continuityProperty.clone();
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(continuityProperty.block);
            String direction = face.get();
            clonedProperty.customMatch(key + ":facing=" + direction).nameSuffix(".facing-" + direction).faces(face);
            this.continuityProperties.add(clonedProperty);
        }
        return this;
    }

    public abstract void generate();

    @Override
    public String getName() {
        return "Continuity Properties Provider";
    }

    public FabricDataOutput getOutput() {
        return output;
    }
}
//?}