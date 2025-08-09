package org.bsdevelopment.simplepets.commands;

import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bsdevelopment.simplepets.api.pet.PetType;

public interface Arguments {
    static Argument<PetType> setupPetTypeArgument(String nodeName) {
        return new CustomArgument<>(new StringArgument(nodeName), info -> {
            PetType petType = PetType.REGISTRY.getOrDefault(
                    info.input().toLowerCase().replace(" ", "_"),
                    null
            );

            if (petType == null) throw CustomArgument.CustomArgumentException.fromMessageBuilder(
                    new CustomArgument.MessageBuilder("Unknown Pet Type: ").appendArgInput()
            );

            return petType;
        }).replaceSuggestions(ArgumentSuggestions.strings(info ->
                PetType.REGISTRY.keySet().toArray(String[]::new)
        ));
    }
}
