package org.bsdevelopment.simplepets.commands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandUtils {
    public static LiteralArgumentBuilder<?> literal(String literal) {
        return LiteralArgumentBuilder.literal(literal);
    }

    public static <T> RequiredArgumentBuilder<?, T> argument(String name, ArgumentType<T> argumentType) {
        return RequiredArgumentBuilder.argument(name, argumentType);
    }

    public static <T> T getArgument(CommandContext<?> commandContext, String name, Class<T> type, T defaultValue) {
        try {
            return commandContext.getArgument(name, type);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static <T> CompletableFuture<Suggestions> suggest(SuggestionsBuilder builder, List<T> list) {
        list.forEach(t -> {
            if (t instanceof Enum<?> enumValue) {
                builder.suggest(enumValue.name());
            } else {
                builder.suggest(String.valueOf(t));
            }
        });
        return builder.buildFuture();
    }

    public static <T> CompletableFuture<Suggestions> suggest(SuggestionsBuilder builder, T... list) {
        for (T t : list) {
            if (t instanceof Enum<?> enumValue) {
                builder.suggest(enumValue.name());
            } else {
                builder.suggest(String.valueOf(t));
            }
        }
        return builder.buildFuture();
    }
}