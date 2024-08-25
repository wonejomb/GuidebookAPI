package de.wonejo.wuidebook.wrapper;

public class PositionedWrapper<T> {

    private final T object;
    private final int x;
    private final int y;

    public PositionedWrapper(T pObject, int pX, int pY ) {
        this.object = pObject;
        this.x = pX;
        this.y = pY;
    }

    public T getObject() {
        return object;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
