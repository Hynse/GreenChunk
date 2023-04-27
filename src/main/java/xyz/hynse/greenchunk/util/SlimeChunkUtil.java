package xyz.hynse.greenchunk.util;

import java.util.Random;

public class SlimeChunkUtil {
    private static final Random RAND = new Random();
    private static final long SLIME_SEED = 987234911L;

    public static boolean canSlimeSpawnAt(int posX, int posZ, long worldSeed) {
        return canSlimeSpawnInChunk(posX >> 4, posZ >> 4, worldSeed);
    }

    public static boolean canSlimeSpawnInChunk(int chunkX, int chunkZ, long worldSeed) {
        long rngSeed = worldSeed +
                (long) (chunkX * chunkX * 4987142) +
                (long) (chunkX * 5947611) +
                (long) (chunkZ * chunkZ) * 4392871L +
                (long) (chunkZ * 389711) ^ SLIME_SEED;

        RAND.setSeed(rngSeed);

        return RAND.nextInt(10) == 0;
    }
}
