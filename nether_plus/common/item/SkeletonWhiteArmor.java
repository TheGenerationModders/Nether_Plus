package nether_plus.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import nether_plus.common.NetherPlusCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkeletonWhiteArmor extends ItemArmor
{

	public SkeletonWhiteArmor(int id, EnumArmorMaterial armorMaterial, int type, int layer)
	{
		super(id, armorMaterial, type, layer);
		this.setCreativeTab(NetherPlusCreativeTabs.NetherPlusCreativeTabs);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		if(stack.itemID == NPItemList.SkeletonWhiteLeggings.itemID)
		{
			return "/mods/nether_plus/textures/armors/skeleton_white_armor_2.png"; //armure WhiteSkeleton, jambières
		}
		else
		{
			return "/mods/nether_plus/textures/armors/skeleton_white_armor_1.png"; //armure WhiteSkeleton, casque + plastron + bottes
		}

	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
        itemIcon = iconregister.registerIcon("nether_plus:"+getUnlocalizedName().substring(5));
    }
	
	public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
    {
        return true;
    }
}