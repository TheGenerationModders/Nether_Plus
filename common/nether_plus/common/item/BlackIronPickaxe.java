package nether_plus.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import nether_plus.common.creativetabs.NetherPlusCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlackIronPickaxe extends ItemPickaxe
{

	public BlackIronPickaxe(ToolMaterial toolmaterial)
	{
		super(toolmaterial);
		this.setCreativeTab(NetherPlusCreativeTabs.NPCreativeTabsTool);
	}
	
	public boolean getIsRepairable(ItemStack stack, ItemStack outputstack)
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconregister)
	{
        this.itemIcon = iconregister.registerIcon("nether_plus:BlackIronPickaxe");
 	}
}