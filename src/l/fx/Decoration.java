package l.fx;

import javafx.scene.Node;

public interface Decoration {

    /** Add a decoration to the given target node
     * @param target The node to decorate
     */
    void add(Node target);

    /** Remove a decoration from the given target node
     * @param target The node to remove decoration from
     */
    void remove(Node target);
}
