package nether_plus.common.entity.ia;

import net.minecraft.entity.EntityLiving;

public class Survival_VI_AIOpenDoor extends Survival_VI_AIDoorInteract
{
    boolean field_75361_i;
    int field_75360_j;

    public Survival_VI_AIOpenDoor(EntityLiving par1EntityLiving, boolean par2)
    {
        super(par1EntityLiving);
        this.theEntity = par1EntityLiving;
        this.field_75361_i = par2;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.field_75361_i && this.field_75360_j > 0 && super.continueExecuting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.field_75360_j = 20;
        this.field_151504_e.func_150014_a(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        if (this.field_75361_i)
        {
            this.field_151504_e.func_150014_a(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, false);
        }
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        --this.field_75360_j;
        super.updateTask();
    }
}