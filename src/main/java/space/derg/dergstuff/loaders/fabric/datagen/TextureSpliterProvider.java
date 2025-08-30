//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import com.google.common.hash.HashCode;
import com.mojang.blaze3d.platform.NativeImage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class TextureSpliterProvider implements DataProvider {
    private final List<TextureSplit> textures;
    private final FabricDataOutput output;

    public TextureSpliterProvider(FabricDataOutput output) {
        this.textures = new ArrayList<>();
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        this.generate();
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (TextureSplit split : this.textures) {
            ResourceLocation texture = split.texture();

            Path textureInput = this.output
                    .getModContainer()
                    .findPath("assets")
                    .get()
                    .resolve(Path.of(this.output.getModId(), "textures", texture.getPath() + ".png"));

            Path textureOutput = this.output
                    .getOutputFolder()
                    .resolve(Path.of("assets", texture.getNamespace(), "textures", texture.getPath(), split.suffix() + ".png"));

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try (InputStream inputStream = Files.newInputStream(textureInput)) {
                    NativeImage image = NativeImage.read(inputStream);
                    NativeImage cropped = new NativeImage(NativeImage.Format.RGBA, 16, 16, false);
                    image.copyRect(cropped, split.x() * 16, split.y() * 16, 0, 0, 16, 16, false, false);
                    byte[] buffer = cropped.asByteArray();
                    output.writeIfNeeded(textureOutput, buffer, HashCode.fromBytes(buffer));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            futures.add(future);
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    public final void splitTexture(ResourceLocation texture, String suffix, int x, int y) {
        this.textures.add(new TextureSplit(texture, suffix, x, y));
    }

    public abstract void generate();

    @Override
    public String getName() {
        return "Texture Splitter Provider";
    }
}
//?}