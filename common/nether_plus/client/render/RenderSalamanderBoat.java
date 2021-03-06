package nether_plus.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import nether_plus.client.model.ModelSalamanderBoat;
import nether_plus.common.entity.SalamanderBoat;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSalamanderBoat extends Render
{
    private static final ResourceLocation boatTexture = new ResourceLocation("nether_plus", "textures/entity/salamanderboat.png");
	
    /** instance of ModelBoat for rendering */
    protected ModelBase ModelSalamanderBoat;

    public RenderSalamanderBoat()
    {
        this.shadowSize = 0.5F;
        this.ModelSalamanderBoat = new ModelSalamanderBoat();
    }

    /**
     * The render method used in RenderBoat that renders the boat model.
     */
    public void renderSalamanderBoat(SalamanderBoat par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        float f2 = (float)par1EntityBoat.getTimeSinceHit() - par9;
        float f3 = (float)par1EntityBoat.getDamageTaken() - par9;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f2 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)par1EntityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }
        
        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.bindEntityTexture(par1EntityBoat);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.ModelSalamanderBoat.render(par1EntityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderSalamanderBoat((SalamanderBoat)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getTexture((SalamanderBoat)entity);
	}

	private ResourceLocation getTexture(SalamanderBoat salamanderBoat)
	{
		return boatTexture;
	}
}