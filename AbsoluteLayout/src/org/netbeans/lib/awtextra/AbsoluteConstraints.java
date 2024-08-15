package org.netbeans.lib.awtextra;

import java.io.Serial; // Línea añadida para importar la anotación @Serial
import java.io.Serializable;

/** A constraints object that specifies horizontal and vertical
 * position and width and height for the component.
 */
public class AbsoluteConstraints implements Serializable {
    @Serial
    private static final long serialVersionUID = 5261460716622152494L; // Añadida anotación @Serial

    int x;
    int y;
    int width;
    int height;

        public AbsoluteConstraints(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() + "[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
    }
}