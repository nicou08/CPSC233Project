package etphoneshome.objects;

public enum PhonePieceType {

    ANTENNA(0),
    CHASSIS(1),
    KEYPAD(2);

    private final int index;

    PhonePieceType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
