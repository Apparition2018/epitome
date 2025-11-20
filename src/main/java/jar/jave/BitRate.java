package jar.jave;

import lombok.Getter;

/**
 * BitRate
 *
 * @author ljh
 * @since 2025/11/20 09:38
 */
public interface BitRate {

    /**
     * 恒定比特率
     */
    @Getter
    enum CBR implements BitRate {
        LOW(96000, 128000), MEDIUM(128000, 192000), HIGH(192000, 256000), ULTRA(256000, 320000);
        private final int m4a;
        private final int mp3;

        CBR(int m4a, int mp3) {
            this.m4a = m4a;
            this.mp3 = mp3;
        }
    }

    /**
     * 可变比特率
     */
    @Getter
    class VBR implements BitRate {
        private final float quality;

        public VBR(float quality) {
            this.quality = quality;
        }

        public static VBR create(float quality) {
            return new VBR(quality);
        }
    }
}
