//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen.entries;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public abstract class DatagenEntry<T> {
    protected final T object;
    protected final String id;

    protected String lang;

    public DatagenEntry(T object, String id) {
        this.object = object;
        this.id = id;
    }

    public DatagenEntry<T> lang(String lang) {
        this.lang = lang;
        return this;
    }

    public abstract void genLang(FabricLanguageProvider.TranslationBuilder builder);
}
//?}