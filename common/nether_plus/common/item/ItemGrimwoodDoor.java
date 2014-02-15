package nether_plus.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import nether_plus.common.block.NPBlockList;
import nether_plus.common.creativetabs.NetherPlusCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGrimwoodDoor extends Item
{
	private Material doorMaterial;

	public ItemGrimwoodDoor(Material material)
	{
		super();
        this.doorMaterial = material;
        this.maxStackSize = 1;
		this.setCreativeTab(NetherPlusCreativeTabs.NPCreativeTabsItem);
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else
        {
            ++y;
            Block block;

            if (this.doorMaterial == Material.wood)
            {
                block = NPBlockList.BlockGrimwoodDoor;
            }
            else
            {
                block = Blocks.iron_door;
            }

            if (player.canPlayerEdit(x, y, z, par7, itemStack) && player.canPlayerEdit(x, y + 1, z, par7, itemStack))
            {
                if (!block.canPlaceBlockAt(world, x, y, z))
                {
                    return false;
                }
                else
                {
                    int i1 = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(world, x, y, z, i1, block);
                    --itemStack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int par4, Block block)
    {
        byte b0 = 0;
        byte b1 = 0;

        if (par4 == 0)
        {
            b1 = 1;
        }

        if (par4 == 1)
        {
            b0 = -1;
        }

        if (par4 == 2)
        {
            b1 = -1;
        }

        if (par4 == 3)
        {
            b0 = 1;
        }

        int i1 = (world.getBlock(x - b0, y, z - b1).isNormalCube() ? 1 : 0) + (world.getBlock(x - b0, y + 1, z - b1).isNormalCube() ? 1 : 0);
        int j1 = (world.getBlock(x + b0, y, z + b1).isNormalCube() ? 1 : 0) + (world.getBlock(x + b0, y + 1, z + b1).isNormalCube() ? 1 : 0);
        boolean flag = world.getBlock(x - b0, y, z - b1) == block || world.getBlock(x - b0, y + 1, z - b1) == block;
        boolean flag1 = world.getBlock(x + b0, y, z + b1) == block || world.getBlock(x + b0, y + 1, z + b1) == block;
        boolean flag2 = false;

        if (flag && !flag1)
        {
            flag2 = true;
        }
        else if (j1 > i1)
        {
            flag2 = true;
        }

        world.setBlock(x, y, y, block, par4, 2);
        world.setBlock(x, y + 1, y, block, 8 | (flag2 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, y, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, y, block);
    }
    
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconregister)
	{
        this.itemIcon = iconregister.registerIcon("nether_plus:ItemGrimwoodDoor");
 	}
}