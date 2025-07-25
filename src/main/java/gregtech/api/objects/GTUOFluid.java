package gregtech.api.objects;

import static gregtech.common.UndergroundOil.DIVIDER;

import java.util.Random;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import gregtech.api.util.GTUtility;

public class GTUOFluid {

    public String Registry = "null";
    public int MaxAmount = 0;
    public int MinAmount = 0;
    public int Chance = 0;
    public int DecreasePerOperationAmount = 5;

    public GTUOFluid(ConfigCategory aConfigCategory) { // TODO CONFIGURE
        if (aConfigCategory.containsKey("Registry")) {
            aConfigCategory.get("Registry").comment = "Fluid registry name";
            Registry = aConfigCategory.get("Registry")
                .getString();
        }
        if (aConfigCategory.containsKey("MaxAmount")) {
            aConfigCategory
                .get("MaxAmount").comment = "Max amount generation (per operation, sets the VeinData) 80000 MAX";
            MaxAmount = aConfigCategory.get("MaxAmount")
                .getInt(0);
        }
        if (aConfigCategory.containsKey("MinAmount")) {
            aConfigCategory.get("MinAmount").comment = "Min amount generation (per operation, sets the VeinData) 0 MIN";
            MinAmount = aConfigCategory.get("MinAmount")
                .getInt(0);
        }
        if (aConfigCategory.containsKey("Chance")) {
            aConfigCategory
                .get("Chance").comment = "Chance generating (weighted chance!, there will be a fluid in chunk always!)";
            Chance = aConfigCategory.get("Chance")
                .getInt(0);
        }
        if (aConfigCategory.containsKey("DecreasePerOperationAmount")) {
            aConfigCategory.get(
                "DecreasePerOperationAmount").comment = "Decrease per operation (actual fluid gained works like (Litre)VeinData/5000)";
            DecreasePerOperationAmount = aConfigCategory.get("DecreasePerOperationAmount")
                .getInt(5);
        }
        // GT_FML_LOGGER.info("GT UO "+aConfigCategory.getName()+" Fluid:"+Registry+" Max:"+MaxAmount+"
        // Min:"+MinAmount+" Chance:"+Chance);
    }

    public Fluid getFluid() {
        try {
            return FluidRegistry.getFluid(this.Registry);
        } catch (Exception e) {
            return null;
        }
    }

    public int getRandomAmount(Random aRandom) { // generates some random ass number that correlates to extraction
                                                 // speeds
        int smax = (int) Math.floor(Math.pow(MaxAmount * 100.d * DIVIDER, 0.2d)); // use scaled max and min values for
                                                                                  // the randomness to make high values
                                                                                  // more rare.
        double smin = Math.pow(MinAmount * 100.d * DIVIDER, 0.2d);
        double samount = Math.max(smin, aRandom.nextInt(smax) + aRandom.nextDouble());
        return (int) (GTUtility.powInt(samount, 5) / 100); // reverses the computation above
    }
}
