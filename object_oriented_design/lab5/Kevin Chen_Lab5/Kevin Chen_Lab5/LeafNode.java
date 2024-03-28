/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: Generic BST Leaf node class and child of AbstractTreeNode
 */


import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * LeafNode of the BST
 *
 * @param <T> Generic
 */
public class LeafNode<T> extends AbstractTreeNode<T> {

    public LeafNode(T value) {
        super(value);
    }

    @Override
    public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
        return new GroupNode<T>(value, identifier, child);
    }

    @Override
    public List<T> toList() {
        return Arrays.asList(value);
    }

    @Override
    public <R> TreeNode<R> map(Function<T, R> transform) {
        return new LeafNode<R>(transform.apply(value));
    }

    @Override
    public <R> R reduce(R initialValue, BiFunction<R, T, R> combiner) {
        return combiner.apply(initialValue, value);
    }

    @Override
    public void print() {
        System.out.println(value);
    }
}
