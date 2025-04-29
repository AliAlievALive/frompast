package org.frompast.utils;

import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.util.UUID;

@UtilityClass
public class GuidHelper {

    public static String convertByteArrayToGuidString(byte[] bytes) {
        if (bytes.length != 16 && bytes.length != 36) {
            throw new IllegalArgumentException("Invalid GUID byte array length: " + bytes.length);
        }

        if (bytes.length == 36) {
            return new String(bytes);
        }

        return String.format("%02x%02x%02x%02x-%02x%02x-%02x%02x-%02x%02x-%02x%02x%02x%02x%02x%02x",
                bytes[3] & 255,
                bytes[2] & 255,
                bytes[1] & 255,
                bytes[0] & 255,
                bytes[5] & 255,
                bytes[4] & 255,
                bytes[7] & 255,
                bytes[6] & 255,
                bytes[8] & 255,
                bytes[9] & 255,
                bytes[10] & 255,
                bytes[11] & 255,
                bytes[12] & 255,
                bytes[13] & 255,
                bytes[14] & 255,
                bytes[15] & 255);
    }

    public static byte[] convertUUIDToBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

}
