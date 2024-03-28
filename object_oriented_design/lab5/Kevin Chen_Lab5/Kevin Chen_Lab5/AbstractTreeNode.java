/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: AbstractTreeNode Class implementing the TreeNode interface, adds a final value and constructor
 */


/**
 * Abstract Tree Node base class
 *
 * @param <T> Generic
 */
public abstract class AbstractTreeNode<T> implements TreeNode<T> {
    public final T value;

    protected AbstractTreeNode(T value) {
        this.value = value;
    }
}