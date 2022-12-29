package me.alpha.kitpvp.Objects.Mobs;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffectType;

public class CustomZombie extends EntityZombie {

    @SuppressWarnings("deprecation")
    public CustomZombie(World world) {
        super(world);

        Zombie craftZombie = (Zombie) this.getBukkitEntity();

        craftZombie.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(32000, 1), false);
        craftZombie.addPotionEffect(PotionEffectType.SPEED.createEffect(32000, 1), false);

        this.setSprinting(true);

        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 1.0D, false));
        this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.0D, false));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));

        this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class,false));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class,false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class,false));

        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityZombie>(this, EntityZombie.class,true));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityMonster>(this, EntityMonster.class,true));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityPigZombie>(this, EntityPigZombie.class,true));

        this.getWorld().addEntity(this);
    }
}
