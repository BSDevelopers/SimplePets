package org.bsdevelopment.simplepets.api.pet;

import org.bsdevelopment.simplepets.api.pet.entity.type.SquidType;

public interface PetTypeHandler {
    PetType SQUID = PetType.register("squid", SquidType.SquidPet.class, "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac");
    PetType GLOW_SQUID = PetType.register("squid", SquidType.GlowSquidPet.class, "3e94a1bb1cb00aaa153a74daf4b0eea20b8974522fe9901eb55aef478ebeff0d");
}
