package xyz.hynse.greenchunk.util;

import org.bukkit.World;

import java.util.Random;

public class SlimeChunkUtil {
    private static final Random RAND = new Random();
    private static final long SLIME_SEED = 987234911L;

    public static boolean canSlimeSpawnAt(int posX, int posZ, World world) {
        return canSlimeSpawnInChunk(posX >> 4, posZ >> 4, world);
    }

    public static boolean canSlimeSpawnInChunk(int chunkX, int chunkZ, World world) {
        return world.getChunkAt(chunkX, chunkZ).isSlimeChunk();
    }
}
