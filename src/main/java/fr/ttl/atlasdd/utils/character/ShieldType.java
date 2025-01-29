package fr.ttl.atlasdd.utils.character;

public enum ShieldType {
    NONE(0),
    NORMAL(2),
    MAGIC_1(3),
    MAGIC_2(4),
    MAGIC_3(5);

    private final int value;

    ShieldType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
