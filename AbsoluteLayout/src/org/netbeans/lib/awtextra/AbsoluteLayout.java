package org.netbeans.lib.awtextra;

import java.awt.*;
import java.util.Hashtable;

/** A flow-like layout arranged in absolute coordinates.
 * @see AbsoluteConstraints
 * @version 1.01, Aug 19, 1998
 */
public class AbsoluteLayout implements LayoutManager2, java.io.Serializable {
    private static final long serialVersionUID = -1919857869177070440L; // Cambiado a 'private static final long'
    protected Hashtable<Component, AbsoluteConstraints> constraints = new Hashtable<>();

    /** Adds the specified component with the specified name to the layout.
     * @param name the component name
     * @param comp the component to be added
     */
    public void addLayoutComponent(String name, Component comp) {
        throw new IllegalArgumentException();
    }

    /** Removes the specified component from the layout.
     * @param comp the component to be removed
     */
    public void removeLayoutComponent(Component comp) {
        constraints.remove(comp);
    }

    /** Calculates the preferred size dimensions for the specified
     * panel given the components in the specified parent container.
     * @param parent the component to be laid out
     * @see #minimumLayoutSize
     */
    public Dimension preferredLayoutSize(Container parent) {
        int maxWidth = 0;
        int maxHeight = 0;
        synchronized (parent.getTreeLock()) {
            for (Component comp : parent.getComponents()) {
                AbsoluteConstraints ac = constraints.get(comp);
                if (ac != null) {
                    Dimension size = comp.getPreferredSize();
                    int width = ac.getWidth();
                    int height = ac.getHeight();
                    if (width == -1) {
                        width = size.width;
                    }
                    if (height == -1) {
                        height = size.height;
                    }
                    maxWidth = Math.max(ac.getX() + width, maxWidth);
                    maxHeight = Math.max(ac.getY() + height, maxHeight);
                }
            }
        }
        return new Dimension(maxWidth, maxHeight);
    }

    /** Calculates the minimum size dimensions for the specified
     * panel given the components in the specified parent container.
     * @param parent the component to be laid out
     * @see #preferredLayoutSize
     */
    public Dimension minimumLayoutSize(Container parent) {
        int maxWidth = 0;
        int maxHeight = 0;
        synchronized (parent.getTreeLock()) {
            for (Component comp : parent.getComponents()) {
                AbsoluteConstraints ac = constraints.get(comp);
                if (ac != null) {
                    Dimension size = comp.getMinimumSize();
                    int width = ac.getWidth();
                    int height = ac.getHeight();
                    if (width == -1) {
                        width = size.width;
                    }
                    if (height == -1) {
                        height = size.height;
                    }
                    maxWidth = Math.max(ac.getX() + width, maxWidth);
                    maxHeight = Math.max(ac.getY() + height, maxHeight);
                }
            }
        }
        return new Dimension(maxWidth, maxHeight);
    }

    /** Lays out the container in the specified panel.
     * @param parent the component which needs to be laid out
     */
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            for (Component comp : parent.getComponents()) {
                AbsoluteConstraints ac = constraints.get(comp);
                if (ac != null) {
                    Dimension size = comp.getPreferredSize();
                    int width = ac.getWidth();
                    int height = ac.getHeight();
                    if (width == -1) {
                        width = size.width;
                    }
                    if (height == -1) {
                        height = size.height;
                    }
                    comp.setBounds(ac.getX(), ac.getY(), width, height);
                }
            }
        }
    }

    /** Adds the specified component to the layout, using the specified
     * constraint object.
     * @param comp the component to be added
     * @param constr where/how the component is added to the layout.
     */
    public void addLayoutComponent(Component comp, Object constr) {
        if (!(constr instanceof AbsoluteConstraints)) {
            throw new IllegalArgumentException();
        }
        constraints.put(comp, (AbsoluteConstraints) constr);
    }

    /** Returns the maximum size of this component.
     * @see java.awt.Component#getMinimumSize()
     * @see java.awt.Component#getPreferredSize()
     * @see LayoutManager
     */
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /** Returns the alignment along the x axis. This specifies how
     * the component would like to be aligned relative to other
     * components. The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     */
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    /** Returns the alignment along the y axis. This specifies how
     * the component would like to be aligned relative to other
     * components. The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     */
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    /** Invalidates the layout, indicating that if the layout manager
     * has cached information it should be discarded.
     */
    public void invalidateLayout(Container target) {
    }
}