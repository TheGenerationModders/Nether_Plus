package nether_plus.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import nether_plus.common.creativetabs.NetherPlusCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkeletonBlackArmor extends ItemArmor
{

	public SkeletonBlackArmor(int id, EnumArmorMaterial armorMaterial, int type, int layer)
	{
		super(id, armorMaterial, type, layer);
		this.setCreativeTab(NetherPlusCreativeTabs.NPCreativeTabsArmor);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		if(stack.itemID == NPItemList.SkeletonBlackLeggings.itemID)
		{
			return "/mods/nether_plus/textures/armors/BlackSkeleton_2.png"; //armure BlackSkeleton, jambières
		}
		else
		{
			return "/mods/nether_plus/textures/armors/BlackSkeleton_1.png"; //armure BlackSkeleton, casque + plastron + bottes
		}	
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack stack)
	{
		if(!stack.isItemEnchanted())
		{
			stack.addEnchantment(Enchantment.fireProtection, 10);
		}
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 100));
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