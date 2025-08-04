package org.bsdevelopment.simplepets.api.utils;

import org.bsdevelopment.pluginutils.version.ServerVersion;
import org.bsdevelopment.simplepets.api.PetPlugin;

import java.lang.reflect.InvocationTargetException;

public class HelperUtilities {
    // The Byte Array for the NMS path to prevent relocation
    public static final String NMS_PATH = new String(new byte[]{111, 114, 103, 46, 98, 115, 100, 101, 118, 101, 108, 111, 112, 109, 101, 110, 116, 46, 115, 105, 109, 112, 108, 101, 112, 101, 116, 115, 46, 110, 109, 115});

    public static <T> T getVersionedClass(String className, Class<?>[] types, Object... args) {
        String mcVersion = ServerVersion.getVersion().getVersionName();
        String path = NMS_PATH +"."+ mcVersion+"."+className;
        try {
            Class<?> classInstance = Class.forName(path);
            T object = (T) classInstance.getConstructor(types).newInstance(args);
            PetPlugin.LOG.info("Found support for version: "+mcVersion);
            return object;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getVersionedClass(String className) {
        String mcVersion = ServerVersion.getVersion().getVersionName();
        String path = NMS_PATH +"."+ mcVersion+"."+className;
        try {
            Class<?> classInstance = Class.forName(path);
            T object = (T) classInstance.newInstance();
            PetPlugin.LOG.info("Found support for version: "+mcVersion);
            return object;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getVersionedClass(String className, Class<T> fallback) {
        String mcVersion = ServerVersion.getVersion().getVersionName();
        String path = NMS_PATH +"."+ mcVersion+"."+className;
        try {
            Class<?> classInstance = Class.forName(path);
            T object = (T) classInstance.newInstance();
            PetPlugin.LOG.info("Found support for version: "+mcVersion);
            return object;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            try {
                T object = (T) fallback.newInstance();
                PetPlugin.LOG.info("Found support for version: "+mcVersion);
                return object;
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
