package nether_plus.common.event;

import net.minecraftforge.event.entity.player.BonemealEvent;
import nether_plus.common.block.ChiliC;
import nether_plus.common.block.Crops;
import nether_plus.common.block.GrimwoodSapling;
import nether_plus.common.block.NPBlockList;
import nether_plus.common.block.RiceC;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
 
public class GrimBoneMeal
{
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.block == NPBlockList.GrimwoodSapling)
		{
			if (!event.world.isRemote)
			{
				((GrimwoodSapling)NPBlockList.GrimwoodSapling).growTree(event.world, event.x, event.y, event.z, event.world.rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
		else if (event.block == NPBlockList.Crops)
		{
			if (!event.world.isRemote)
			{
				((Crops)NPBlockList.Crops).fertilizeCrops(event.world, event.x, event.y, event.z);
				event.setResult(Event.Result.ALLOW);
			}
		}
		else if (event.block == NPBlockList.ChiliC)
		{
			if (!event.world.isRemote)
			{
				((ChiliC)NPBlockList.ChiliC).fertilizeStem(event.world, event.x, event.y, event.z);
				event.setResult(Event.Result.ALLOW);
			}
		}
		else if (event.block == NPBlockList.RiceC)
		{
			if (!event.world.isRemote)
			{
				((RiceC)NPBlockList.RiceC).fertilizeRiceC(event.world, event.x, event.y, event.z);
				event.setResult(Event.Result.ALLOW);
			}
		}
	}
}