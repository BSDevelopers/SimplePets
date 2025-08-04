package org.bsdevelopment.simplepets.nms.entity.type;

import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.simplepets.api.pet.entity.PetDataHandler;
import org.bsdevelopment.simplepets.api.pet.entity.type.SquidType;
import org.bsdevelopment.simplepets.nms.entity.EntityPet;

public class EntityGlowSquidPet extends EntityPet implements SquidType.GlowSquidPet {
    protected EntityGlowSquidPet(PetDataHandler petDataHandler) {
        super(EntityType.GLOW_SQUID, petDataHandler);
    }
}
