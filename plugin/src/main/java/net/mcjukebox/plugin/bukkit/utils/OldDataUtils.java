package net.mcjukebox.plugin.bukkit.utils;

import java.io.*;

public class OldDataUtils {

    /**
     * Attempts to write an object to the provided path, creating the file if it does not already
     * exist. Uses the default serialization methods, and as result, it is recommended that in
     * Bukkit applications, it be used in conjunction with the Serialization package
     *
     * @param objectToSave The object which should be saved
     * @param pathToSaveTo The path in which to save the object
     */
    public static <T extends Object> void saveObjectToPath(T objectToSave, String pathToSaveTo) {
        try {
            File file = new File(pathToSaveTo);
            if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if(!file.exists()) file.createNewFile();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(objectToSave);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads an object saved using the saveObjectToPath method. Returns an object which can be
     * casted, after checking to the expected Type.
     *
     * @param pathToLoadFrom The path in which the object file is located
     * @return An object which has been loaded from the file, or null if the file does not exist
     */
    public static <T extends Object> T loadObjectFromPath(String pathToLoadFrom) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToLoadFrom));
            T result = (T) ois.readObject();
            ois.close();
            return result;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
}
