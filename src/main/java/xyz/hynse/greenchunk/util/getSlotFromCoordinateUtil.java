package xyz.hynse.greenchunk.util;

public class getSlotFromCoordinateUtil {
    public static int getSlotFromCoordinates(float yaw, int dx, int dz) {
        try {
            int dir = (Math.round(yaw / 90) + 2) % 4;
            int[] xTable = {1, 0, -1, 0};
            int[] zTable = {0, 1, 0, -1};
            int newX = dx * xTable[dir] + dz * zTable[dir];
            int newZ = -dx * zTable[dir] + dz * xTable[dir];
            return (newZ + 3) * 9 + (newX + 4);
        } catch (Exception ignored) {
            return 0;
        }
    }
}
