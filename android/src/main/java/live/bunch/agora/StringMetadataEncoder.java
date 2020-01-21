package live.bunch.agora;

import java.nio.charset.StandardCharsets;

/**
 * A basic String metadata encoder/decoder.
 *
 * Usage:
 *
 *  <pre>
 *    // sending metadata
 *    AgoraManager.getInstance().sendMetadata(new StringMetadataEncoder().encode("hello world"));
 *
 *    // receiving metadata
 *    AgoraManager.getInstance().addMetadataListener(new AgoraManager.MetadataListener() {
 *        @Override
 *        public void onMetadataReceived(byte[] buffer, int uid, long timeStampMs) {
 *            String metadata = new StringMetadataEncoder().decode(buffer);
 *            // metadata = "hello world"
 *            // ...
 *        }
 *    });
 *  </pre>
 */
public class StringMetadataEncoder implements MetadataEncoder<String> {
    @Override
    public byte[] encode(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String decode(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }
}
