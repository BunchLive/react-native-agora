package live.bunch.agora;

interface MetadataEncoder<T> {
    byte[] encode(T str);

    T decode(byte[] data);
}
