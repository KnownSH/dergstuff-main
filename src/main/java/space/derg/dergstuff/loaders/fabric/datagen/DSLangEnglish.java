//? if fabric {
package space.derg.dergstuff.loaders.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import space.derg.dergstuff.DergStuff;
import space.derg.dergstuff.loaders.fabric.DergStuffDatagen;

public class DSLangEnglish extends FabricLanguageProvider {
    public DSLangEnglish(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        DergStuffDatagen.ENTRIES.blockEntries.forEach(entry -> entry.genLang(translationBuilder));

        translationBuilder.add("item_group." + DergStuff.MOD_ID + ".main_tab", "Derg's Stuff");
    }
}
//?}