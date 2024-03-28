/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: Generic BST Branch node class and child of AbstractTreeNode
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * GroupNode is a generic class and child of AbstractTreeNode.
 * This node will have two sub-nodes
 *
 * @param <T> Generic
 */
public class GroupNode<T> extends AbstractTreeNode<T> {

    private TreeNode<T> left, right;

    public GroupNode(T value) {
        super(value);
    }

    public GroupNode(T value, Predicate<T> identifier, TreeNode<T> child) {
        this(value);
        addChild(identifier, child);
    }

    @Override
    public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
        if (identifier.test(value))
            if (left != null) left = left.addChild(identifier, child);
            else left = child;
        else if (right != null) right = right.addChild(identifier, child);
        else right = child;
        return this;
    }

    @Override
    public List<T> toList() {
        ArrayList<T> list = new ArrayList<>();
        list.add(value);
        if (left != null)
            list.addAll(left.toList());
        if (right != null)
            list.addAll(right.toList());
        return list;
    }

    @Override
    public <R> TreeNode<R> map(Function<T, R> transform) {
        GroupNode<R> other = new GroupNode<>(transform.apply(value));
        if (left != null)
            other.left = left.map(transform);
        if (right != null)
            other.right = right.map(transform);
        return other;
    }

    @Override
    public <R> R reduce(R initialValue, BiFunction<R, T, R> combiner) {
        R val = combiner.apply(initialValue, value);
        if (left != null)
            val = left.reduce(val, combiner);
        if (right != null)
            val = right.reduce(val, combiner);
        return val;
    }

    @Override
    public void print() {
        System.out.println(value);
        if (left != null)
            left.print();
        if (right != null)
            right.print();
    }
}