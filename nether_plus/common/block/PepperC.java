package nether_plus.common.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import nether_plus.common.item.NPItemList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PepperC extends BlockStem
{
    /** Defines if it is a Melon or a Pumpkin that the stem is producing. */
    private final Block fruitType;
    @SideOnly(Side.CLIENT)
    private Icon theIcon;

    protected PepperC(int par1, Block par2Block)
    {
        super(par1, par2Block);
        this.fruitType = par2Block;
        this.setTickRandomly(true);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == NPBlockList.Nether_Farm.blockID;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            float f = this.getGrowthModifier(par1World, par2, par3, par4);

            if (par5Random.nextInt((int)(25.0F / f) + 1) == 0)
            {
                int l = par1World.getBlockMetadata(par2, par3, par4);

                if (l < 7)
                {
                    ++l;
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                }
                else
                {
                    if (par1World.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID)
                    {
                        return;
                    }

                    if (par1World.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID)
                    {
                        return;
                    }

                    if (par1World.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID)
                    {
                        return;
                    }

                    if (par1World.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID)
                    {
                        return;
                    }

                    int i1 = par5Random.nextInt(4);
                    int j1 = par2;
                    int k1 = par4;

                    if (i1 == 0)
                    {
                        j1 = par2 - 1;
                    }

                    if (i1 == 1)
                    {
                        ++j1;
                    }

                    if (i1 == 2)
                    {
                        k1 = par4 - 1;
                    }

                    if (i1 == 3)
                    {
                        ++k1;
                    }

                    int l1 = par1World.getBlockId(j1, par3 - 1, k1);

                    boolean isSoil = (blocksList[l1] != null && blocksList[l1].canSustainPlant(par1World, j1, par3 - 1, k1, ForgeDirection.UP, this));
                    if (par1World.getBlockId(j1, par3, k1) == 0 && (isSoil || l1 == Block.slowSand.blockID || l1 == Block.netherrack.blockID))
                    {
                        par1World.setBlock(j1, par3, k1, this.fruitType.blockID);
                    }
                }
            }
        }
    }

    public void fertilizeStem(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

        if (l > 7)
        {
            l = 7;
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    private float getGrowthModifier(World par1World, int par2, int par3, int par4)
    {
        float f = 1.0F;
        int l = par1World.getBlockId(par2, par3, par4 - 1);
        int i1 = par1World.getBlockId(par2, par3, par4 + 1);
        int j1 = par1World.getBlockId(par2 - 1, par3, par4);
        int k1 = par1World.getBlockId(par2 + 1, par3, par4);
        int l1 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
        int i2 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
        int j2 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
        int k2 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
        boolean flag = j1 == this.blockID || k1 == this.blockID;
        boolean flag1 = l == this.blockID || i1 == this.blockID;
        boolean flag2 = l1 == this.blockID || i2 == this.blockID || j2 == this.blockID || k2 == this.blockID;

        for (int l2 = par2 - 1; l2 <= par2 + 1; ++l2)
        {
            for (int i3 = par4 - 1; i3 <= par4 + 1; ++i3)
            {
                int j3 = par1World.getBlockId(l2, par3 - 1, i3);
                float f1 = 0.0F;

                if (blocksList[j3] != null && blocksList[j3].canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if (blocksList[j3].isFertile(par1World, l2, par3 - 1, i3))
                    {
                        f1 = 3.0F;
                    }
                }

                if (l2 != par2 || i3 != par4)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.maxY = (double)((float)(par1IBlockAccess.getBlockMetadata(par2, par3, par4) * 2 + 2) / 16.0F);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.maxY, 0.5F + f);
    }
    
    public int getRenderType()
    {
        return 19;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the current state of the stem. Returns -1 if the stem is not fully grown, or a value between 0 and 3
     * based on the direction the stem is facing.
     */
    public int getState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        return l < 7 ? -1 : (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID ? 0 : (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID ? 1 : (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID ? 2 : (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID ? 3 : -1))));
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        for (int i = 0; i < 3; i++)
        {
            if (world.rand.nextInt(15) <= metadata)
            {
                ret.add(new ItemStack(fruitType == NPBlockList.PepperB ? NPItemList.PepperSeed : null));
            }
        }

        return ret;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return NPItemList.PepperSeed.itemID;
    }

    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    protected int getSeedItem()
    {
        return NPItemList.PepperSeed.itemID;
    }
    
    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.getSeedItem();
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("nether_plus:stem_straight");
        this.theIcon = par1IconRegister.registerIcon("nether_plus:stem_bent");
    }

    @SideOnly(Side.CLIENT)
    public Icon func_94368_p()
    {
        return this.theIcon;
    }
}