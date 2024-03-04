package model.domain;
public enum Tipo {
    SCHERMO(1),
    CONNETTOREUSB(2),
    BATTERIA(3),
    SCHEDAMADRE(4),
    TASTIERA(5),
    TRACKPAD(6),
    VENTOLA(7),
    MODULORAM(8)
    ;
    private final int id;

    Tipo(int id){

        this.id = id;
    }

    public static Tipo fromInt(int id) {
        for (Tipo type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return id;
    }
}
