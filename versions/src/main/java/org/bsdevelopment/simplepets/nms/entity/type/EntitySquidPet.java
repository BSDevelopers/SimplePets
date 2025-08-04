package org.bsdevelopment.simplepets.nms.entity.type;

import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.simplepets.api.pet.entity.PetDataHandler;
import org.bsdevelopment.simplepets.api.pet.entity.type.SquidType;
import org.bsdevelopment.simplepets.nms.entity.EntityPet;

public class EntitySquidPet extends EntityPet implements SquidType.SquidPet {
    protected EntitySquidPet(PetDataHandler petDataHandler) {
        super(EntityType.SQUID, petDataHandler);
    }
}
