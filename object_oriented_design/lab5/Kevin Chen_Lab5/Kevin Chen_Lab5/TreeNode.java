/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: Base interface dictating all the capabilities a tree's node should have
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Base Tree Node interface
 *
 * @param <T> Generic
 */
public interface TreeNode<T> {

    /**
     * Adds a child to the node, the side it will reside is determined by the identifier predictate
     *
     * @param identifier Predicate that selects which side the child node ends up
     * @param child      Child node to add
     * @return The modified node, self
     */
    TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child);

    /**
     * Adds all the values, of the node and its children to a list
     *
     * @return List of values of nodes
     */
    default List<T> toList() {
        return this.reduce(new ArrayList<>(), (l, e) -> {
            l.add(e);
            return l;
        });
    }

    /**
     * Transforms the inner type of the tree to another one
     *
     * @param transform Function that performs the mapping
     * @param <R>       Generic
     * @return The modified node, self
     */
    <R> TreeNode<R> map(Function<T, R> transform);

    /**
     * Reduces a tree to a single value
     *
     * @param initialValue Starting value
     * @param combiner     Combining function that will apply the starting value and the node value togheter
     * @param <R>          Starting and Returned value type
     * @return The transformed value
     */
    <R> R reduce(R initialValue, BiFunction<R, T, R> combiner);

    /**
     * Prints the value in the node and its children
     */
    void print();
}
 
 