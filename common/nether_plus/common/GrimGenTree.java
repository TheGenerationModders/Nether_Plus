/*package nether_plus.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import nether_plus.common.block.NPBlockList;

public class GrimGenTree extends WorldGenerator
{
	private final int field_48202_a;
	private final boolean field_48200_b;
	private final int field_48201_c;
	private final int field_48199_d;

	public GrimGenTree(boolean flag)
	{
		this(flag, 4, 0, 0, false);
	}

	public GrimGenTree(boolean flag, int i, int j, int k, boolean flag1)
	{
		super(flag);
		field_48202_a = i;
		field_48201_c = j;
		field_48199_d = k;
		field_48200_b = flag1;
	}

	public boolean generate(World world, Random random, int i, int j, int k)
	{
		int l = random.nextInt(3) + field_48202_a;
		boolean flag = true;

		if (j < 1 || j + l + 1 > 256)
		{
			return false;
		}

		for (int i1 = j; i1 <= j + 1 + l; i1++)
		{
			byte byte0 = 1;

			if (i1 == j)
			{
				byte0 = 0;
			}

			if (i1 >= (j + 1 + l) - 2)
			{
				byte0 = 2;
			}

			for (int k1 = i - byte0; k1 <= i + byte0 && flag; k1++)
			{
				for (int i2 = k - byte0; i2 <= k + byte0 && flag; i2++)
				{
					if (i1 >= 0 && i1 < 256)
					{
						Block i3 = world.getBlock(k1, i1, i2);
						if (i3 != NPBlockList.GrimwoodLeaves && i3 != Blocks.grass && i3 != NPBlockList.GrimwoodLog && i3 != Blocks.dirt && i3 != NPBlockList.CorruptionStone)
						{
							flag = false;
						}
					}
					else
					{
						flag = false;
					}
				}
			}
		}

		if (!flag)
		{
			return false;
		}

		Block j1 = world.getBlock(i, j - 1, k);

		if (j1 != Blocks.dirt && j1 != Blocks.grass && j1 != NPBlockList.CorruptionStone || j >= 256 - l - 1)
		{
			return false;
		}

		func_50073_a(world, i, j - 1, k, NPBlockList.GrimwoodLog);
		byte byte1 = 3;
		int l1 = 0;

		for (int j2 = (j - byte1) + l; j2 <= j + l; j2++)
		{
			int j3 = j2 - (j + l);
			int i4 = (l1 + 1) - j3 / 2;

			for (int k4 = i - i4; k4 <= i + i4; k4++)
			{
				int i5 = k4 - i;

				for (int k5 = k - i4; k5 <= k + i4; k5++)
				{
					int l5 = k5 - k;

					if ((Math.abs(i5) != i4 || Math.abs(l5) != i4 || random.nextInt(2) != 0 && j3 != 0) && !Block.opaqueCubeLookup[world.getBlock(k4, j2, k5)])
					{
						setBlockAndMetadata(world, k4, j2, k5, NPBlockList.GrimwoodLeaves, field_48199_d);
					}
				}
			}
		}

		for (int k2 = 0; k2 < l; k2++)
		{
			Block k3 = world.getBlock(i, j + k2, k);

			if (k3 != NPBlockList.GrimwoodLeaves)
			{
				continue;
			}

			setBlockAndMetadata(world, i, j + k2, k, NPBlockList.GrimwoodLog, field_48201_c);

			if (!field_48200_b || k2 <= 0)
			{
				continue;
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i - 1, j + k2, k))
			{
				setBlockAndMetadata(world, i - 1, j + k2, k, Blocks.dirt, 8);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i + 1, j + k2, k))
			{
				setBlockAndMetadata(world, i + 1, j + k2, k, Blocks.dirt, 2);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i, j + k2, k - 1))
			{
				setBlockAndMetadata(world, i, j + k2, k - 1, Blocks.dirt, 1);
			}

			if (random.nextInt(3) > 0 && world.isAirBlock(i, j + k2, k + 1))
			{
				setBlockAndMetadata(world, i, j + k2, k + 1, Blocks.dirt, 4);
			}
		}

		if (field_48200_b)
		{
			for (int l2 = (j - 3) + l; l2 <= j + l; l2++)
			{
				int l3 = l2 - (j + l);
				int j4 = 2 - l3 / 2;

				for (int l4 = i - j4; l4 <= i + j4; l4++)
				{
					for (int j5 = k - j4; j5 <= k + j4; j5++)
					{
						if (world.getBlock(l4, l2, j5) != NPBlockList.GrimwoodLog)
						{
							continue;
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4 - 1, l2, j5) == 0)
						{
							func_48198_a(world, l4 - 1, l2, j5, 8);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4 + 1, l2, j5) == 0)
						{
							func_48198_a(world, l4 + 1, l2, j5, 2);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4, l2, j5 - 1) == 0)
						{
							func_48198_a(world, l4, l2, j5 - 1, 1);
						}

						if (random.nextInt(4) == 0 && world.getBlock(l4, l2, j5 + 1) == 0)
						{
							func_48198_a(world, l4, l2, j5 + 1, 4);
						}
					}
				}
			}
		}

		return true;
	}

	private void func_50073_a(World world, int i, int j, int k, Block blockID)
	{
	}

	private void func_48198_a(World world, int i, int j, int k, int l)
	{
		setBlockAndMetadata(world, i, j, k, Blocks.dirt, l);

		for (int i1 = 4; world.getBlock(i, --j, k) == 0 && i1 > 0; i1--)
		{
			setBlockAndMetadata(world, i, j, k, Blocks.dirt, l);
		}
	}

	public void fertilize(World world, int x, int y, int z)
	{
		
	}
}*/