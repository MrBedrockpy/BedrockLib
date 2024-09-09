package ru.mrbedrockpy.bedrockutils.builder;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import ru.mrbedrockpy.bedrockutils.chat.ChatUtil;

import java.util.Collection;

public class EntityBuilder {

    private final LivingEntity entity;

    private EntityBuilder(LivingEntity entity) {
        this.entity = entity;
    }

    public static EntityBuilder open(LivingEntity entity) {
        return new EntityBuilder(entity);
    }

    public static EntityBuilder clone(LivingEntity entity, Location location) {
        LivingEntity nEntity = (LivingEntity) location.getWorld().spawnEntity(location, entity.getType());
        nEntity.setAI(entity.hasAI());
        nEntity.setCollidable(entity.isCollidable());
        for (Attribute a : Attribute.values()) {
            nEntity.getAttribute(a).setBaseValue(entity.getAttribute(a).getBaseValue());
            for (AttributeModifier m : entity.getAttribute(a).getModifiers()) nEntity.getAttribute(a).addModifier(m);
        }
        nEntity.setHealth(entity.getHealth());
        nEntity.setGliding(entity.isGliding());
        nEntity.setCanPickupItems(entity.getCanPickupItems());
        nEntity.setSwimming(entity.isSwimming());
        nEntity.setCustomName(entity.getCustomName());
        nEntity.setCustomNameVisible(entity.isCustomNameVisible());
        nEntity.setGlowing(entity.isGlowing());
        nEntity.setGravity(entity.hasGravity());
        nEntity.setInvulnerable(entity.isInvulnerable());
        nEntity.setOp(entity.isOp());
        nEntity.setSilent(entity.isSilent());
        nEntity.setVelocity(entity.getVelocity().clone());
        for (PotionEffect effect : entity.getActivePotionEffects()) nEntity.addPotionEffect(effect, true);
        EntityEquipment entityEquipment = entity.getEquipment();
        EntityEquipment nEntityEquipment = nEntity.getEquipment();
        nEntityEquipment.setBoots(entityEquipment.getBoots().clone());
        nEntityEquipment.setLeggings(entityEquipment.getLeggings().clone());
        nEntityEquipment.setChestplate(entityEquipment.getChestplate().clone());
        nEntityEquipment.setHelmet(entityEquipment.getHelmet().clone());
        nEntityEquipment.setItemInMainHand(entityEquipment.getItemInMainHand().clone());
        nEntityEquipment.setItemInOffHand(entityEquipment.getItemInOffHand().clone());
        nEntityEquipment.setBootsDropChance(entityEquipment.getBootsDropChance());
        nEntityEquipment.setLeggingsDropChance(entityEquipment.getLeggingsDropChance());
        nEntityEquipment.setChestplateDropChance(entityEquipment.getChestplateDropChance());
        nEntityEquipment.setHelmetDropChance(entityEquipment.getHelmetDropChance());
        nEntityEquipment.setItemInMainHandDropChance(entityEquipment.getItemInMainHandDropChance());
        nEntityEquipment.setItemInOffHandDropChance(entityEquipment.getItemInOffHandDropChance());
        return new EntityBuilder(nEntity);
    }

    public static EntityBuilder clone(LivingEntity entity) {
        return clone(entity, entity.getLocation());
    }

    public EntityBuilder(EntityType entityType, Location location) {
        entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    }

    public EntityBuilder setCustomName(String name) {
        entity.setCustomName(ChatUtil.format(name));
        return this;
    }

    public EntityBuilder setCustomNameVisible(boolean b) {
        entity.setCustomNameVisible(b);
        return this;
    }

    public EntityBuilder setAI(boolean b) {
        entity.setAI(b);
        return this;
    }

    public EntityBuilder setCollidable(boolean b) {
        entity.setCollidable(b);
        return this;
    }

    public EntityBuilder setGliding(boolean b) {
        entity.setGliding(b);
        return this;
    }

    public EntityBuilder setCanPickupItems(boolean b) {
        entity.setCanPickupItems(b);
        return this;
    }

    public EntityBuilder setSwimming(boolean b) {
        entity.setSwimming(b);
        return this;
    }

    public EntityBuilder setGlowing(boolean b) {
        entity.setGlowing(b);
        return this;
    }

    public EntityBuilder setGravity(boolean b) {
        entity.setGravity(b);
        return this;
    }

    public EntityBuilder setInvulnerable(boolean b) {
        entity.setInvulnerable(b);
        return this;
    }

    public EntityBuilder setOp(boolean b) {
        entity.setOp(b);
        return this;
    }

    public EntityBuilder setSilent(boolean b) {
        entity.setSilent(b);
        return this;
    }

    public EntityBuilder setVelocity(Vector vector) {
        entity.setVelocity(vector);
        return this;
    }

    public EntityBuilder setHelmet(ItemStack helmet) {
        entity.getEquipment().setHelmet(helmet.clone());
        return this;
    }

    public EntityBuilder setChestplate(ItemStack chestplate) {
        entity.getEquipment().setChestplate(chestplate.clone());
        return this;
    }

    public EntityBuilder setLeggings(ItemStack leggings) {
        entity.getEquipment().setLeggings(leggings.clone());
        return this;
    }

    public EntityBuilder setBoots(ItemStack boots) {
        entity.getEquipment().setBoots(boots.clone());
        return this;
    }

    public EntityBuilder setMainHand(ItemStack item) {
        entity.getEquipment().setItemInMainHand(item.clone());
        return this;
    }

    public EntityBuilder setOffHand(ItemStack item) {
        entity.getEquipment().setItemInOffHand(item.clone());
        return this;
    }

    public EntityBuilder addEffect(PotionEffect effect) {
        entity.addPotionEffect(effect);
        return this;
    }

    public EntityBuilder addEffect(PotionEffect effect, boolean force) {
        entity.addPotionEffect(effect, force);
        return this;
    }

    public EntityBuilder addEffects(Collection<PotionEffect> effects) {
        entity.addPotionEffects(effects);
        return this;
    }

    public EntityBuilder addEffect(PotionEffectType effectType, int time, int lvl, boolean ambient, boolean particles, boolean icon) {
        if (lvl > 255) {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, 255, ambient, particles, icon));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, 255, ambient, particles, icon));
            }
        } else {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, lvl, ambient, particles, icon));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, lvl, ambient, particles, icon));
            }
        }
        return this;
    }

    public EntityBuilder addEffect(PotionEffectType effectType, int time, int lvl, boolean ambient, boolean particles) {
        if (lvl > 255) {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, 255, ambient, particles));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, 255, ambient, particles));
            }
        } else {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, lvl, ambient, particles));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, lvl, ambient, particles));
            }
        }
        return this;
    }

    public EntityBuilder addEffect(PotionEffectType effectType, int time, int lvl, boolean ambient) {
        if (lvl > 255) {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, 255, ambient));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, 255, ambient));
            }
        } else {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, lvl, ambient));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, lvl, ambient));
            }
        }
        return this;
    }

    public EntityBuilder addEffect(PotionEffectType effectType, int time, int lvl) {
        if (lvl > 255) {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, 255));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, 255));
            }
        } else {
            if (time > 0) {
                entity.addPotionEffect(new PotionEffect(effectType, time, lvl));
            } else {
                entity.addPotionEffect(new PotionEffect(effectType, 9999, lvl));
            }
        }
        return this;
    }

    public EntityBuilder removeEffect(PotionEffectType effectType) {
        entity.removePotionEffect(effectType);
        return this;
    }

    public EntityBuilder addPassenger(Entity passenger) {
        entity.addPassenger(passenger);
        return this;
    }

    public EntityBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        entity.getAttribute(attribute).addModifier(modifier);
        return this;
    }

    public EntityBuilder removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        entity.getAttribute(attribute).removeModifier(modifier);
        return this;
    }

    public EntityBuilder setAttributeBaseValue(Attribute attribute, double value) {
        entity.getAttribute(attribute).setBaseValue(value);
        return this;
    }

    public EntityBuilder removeAllAttributeModifiers(Attribute attribute) {
        for (AttributeModifier m : entity.getAttribute(attribute).getModifiers())
            entity.getAttribute(attribute).removeModifier(m);
        return this;
    }

    public EntityBuilder resetAttribute(Attribute attribute) {
        for (AttributeModifier m : entity.getAttribute(attribute).getModifiers())
            entity.getAttribute(attribute).removeModifier(m);
        entity.getAttribute(attribute).setBaseValue(entity.getAttribute(attribute).getDefaultValue());
        return this;
    }

    @Deprecated
    public EntityBuilder setHeath(double heath, boolean force) {
        if (entity.getMaxHealth() < heath) {
            if (force) entity.setMaxHealth(heath);
            else heath = entity.getMaxHealth();
        }
        entity.setHealth(heath);
        return this;
    }

    @Deprecated
    public EntityBuilder setMaxHeath(double heath) {
        entity.setMaxHealth(heath);
        return this;
    }

    public LivingEntity getEntity() {
        return entity;
    }
}