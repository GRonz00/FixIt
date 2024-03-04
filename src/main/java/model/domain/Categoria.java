package model.domain;

public enum Categoria {
    TELEFONO(1),
    PORTATILE(2)
    ;
    private final int id;

    private Categoria(int id){
        this.id = id;
    }

    public static Categoria fromInt(int id) {
        for (Categoria type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
