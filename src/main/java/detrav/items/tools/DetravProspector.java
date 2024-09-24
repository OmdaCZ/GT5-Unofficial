package detrav.items.tools;

import net.minecraft.item.ItemStack;

import detrav.enums.Textures01;
import detrav.items.behaviours.BehaviourDetravToolProspector;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.MetaGeneratedTool;

public class DetravProspector extends DetravToolElectricProspectorBase {

    private final int tier;

    public DetravProspector(int tier) {
        this.tier = tier;
    }

    public int getBaseQuality() {
        return tier;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        double x = tier + 1;
        return (float) (0.00625D + (1.25D * x / 6D) * Math.tanh(Math.pow(x, (x / 8D)) / 25D));
    }

    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return Textures01.mTextures[0];
    }

    public void onStatsAddedToTool(MetaGeneratedTool aItem, int aID) {
        aItem.addItemBehavior(aID, new BehaviourDetravToolProspector(15));
    }
}
