package xyz.hynse.greenchunk.util;

import java.util.Random;

public class SlimeChunkUtil {
    public static boolean isSlimeChunk(int chunkX, int chunkZ, long seed) {
        int x = chunkX * 16 + 8;
        int z = chunkZ * 16 + 8;
        return new Random(seed + (long) (x * x * 0x4c1906) + (long) (x * 0x5ac0dbL) + (long) (z * z) * 0x4307a7L + (long) (z * 0x5f24fL)).nextInt(10) == 0;
    }
}