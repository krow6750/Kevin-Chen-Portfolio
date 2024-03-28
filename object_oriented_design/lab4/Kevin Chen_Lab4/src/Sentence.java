/**
 * Name: Kevin Chen
 * Assignment: Words in a Sentence
 * Date: 2/27/23
 * Notes: Implementation of the Sentence as detailed in the lab PDF
 */
public class Sentence {
    private Node head = EmptyNode.EMPTY_NODE;

    /**
     * Empty Sentence constructor
     */
    public Sentence() { }

    /**
     * Recursive helper method to build a Sentence linked list from a String
     * @param node The current new head
     * @param remainder The remaining string to cut up into a Sentence
     */
    private static void buildFromStr(Node node, String remainder) {
        if (remainder.isBlank()) {
            node.next = EmptyNode.EMPTY_NODE;
            return;
        }

        if (!Character.isAlphabetic(remainder.charAt(0))) { // Must be punctuation
            node.next = new PunctuationNode(remainder.charAt(0));
            buildFromStr(node.next, remainder.substring(1).trim());
            return;
        }

        String word = remainder.split("[^A-z]+")[0];
        node.next = new WordNode(word);
        buildFromStr(node.next, remainder.substring(word.length()).trim());
    }

    /**
     * Constructor from Strings
     * @param sentence String to be turned into a Sentence
     */
    public Sentence(String sentence) {
        EmptyNode storage = new EmptyNode();
        buildFromStr(storage, sentence.trim());
        head = storage.next;
    }

    /**
     * Counts the number of words
     * @return Numbers of word in the Sentence
     */
    public int getNumberOfWords() { return getNumberOfWordsPrimer(head, 0); }

    /**
     * Recursive helper method to count words in a Sentence
     * @param node Current new head
     * @param words Words seen so far
     * @return Number of words up until the given node in the chain
     */
    private int getNumberOfWordsPrimer(Node node, int words) {
        if (node instanceof EmptyNode) return words;
        return getNumberOfWordsPrimer(node.next, words + ((node instanceof WordNode) ? 1 : 0));
    }

    /**
     * Returns the longest word
     * @return Longest String
     */
    public String longestWord() { return longestWordPrimer(head, ""); }

    /**
     * Recursive helper method to find the longest string
     * @param node Current traversed node
     * @param actual Longest String up until the selected node
     * @return Longest String up until the current node
     */
    private String longestWordPrimer(Node node, String actual) {
        if (node instanceof EmptyNode) return actual;
        if (node instanceof WordNode) {
            String word = ((WordNode) node).word;
            if (actual.length() < word.length()) actual = word;
        }
        return longestWordPrimer(node.next, actual);
    }

    /**
     * toString method for Sentence
     * @return String representation of the Sentence
     */
    @Override public String toString() {
        if (head instanceof EmptyNode) return "";
        return appendNodeStr(new StringBuilder(), head);
    }
    /**
     * Recursive helper method to build a String out a Sentence
     * @param str String being built
     * @param node Current selected Node
     * @return String representation of the Sentence
     */
    private static String appendNodeStr(StringBuilder str, Node node) {
        str.append(node);
        if (node.next instanceof EmptyNode) {
            if (node instanceof WordNode)
                str.append('.');
            return str.toString();
        }
        if (node.next instanceof WordNode)
            str.append(' ');
        return appendNodeStr(str, node.next);
    }

    /**
     * Method to clone a Sentence object
     * @return new and isolated Sentence instance
     */
    public Sentence clone() {
        Sentence clone = new Sentence();
        clone.head = head.clone();
        pushNodes(head.next, clone.head);
        return clone;
    }

    /**
     * Recursive helper method to copy a Sentence into another
     * @param source Current node
     * @param dest Destination node
     */
    private void pushNodes(Node source, Node dest) {
        if (source instanceof EmptyNode) return;
        dest.next = source.clone();
        pushNodes(source.next, dest.next);
    }

    /**
     * Creates a Sentence from two other sentences by concatenating them together
     * @param other Other Sentence to concatenate after
     * @return New Sentence combining both this and other
     */
    public Sentence merge(Sentence other) {
        if (head instanceof EmptyNode) return other.clone();
        Sentence merge = clone();
        pushNodes(other.head, lastNode(merge.head));
        return merge;
    }

    /**
     * Recursive helper method to get the first node before EmptyNode (or itself)
     * @param node Currently selected node
     * @return Last node before EmptyNode (or EmptyNode)
     */
    private static Node lastNode(Node node) {
        if (node.next instanceof EmptyNode) return node;
        return lastNode(node.next);
    }

    /**
     * Equals method
     * @param other other object
     * @return Whether both Sentences are logically equal
     */
    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Sentence)) return false;
        return head.equals(((Sentence) other).head);
    }

    /**
     * Inner Node class
     */
    private static abstract class Node {
        /**
         * Pointer to the next node
         */
        public Node next;
        public Node() { this(EmptyNode.EMPTY_NODE); }
        public Node(Node next) { this.next = next; }
        public abstract String toString();
        public abstract Node clone();
    }

    /**
     * Inner EmptyNode class
     */
    public static class EmptyNode extends Node {
        public static final EmptyNode EMPTY_NODE = new EmptyNode();
        private EmptyNode() { super(null); }
        @Override public String toString() { return ""; }
        @Override public Node clone() { return EMPTY_NODE; }
        @Override public boolean equals(Object other) {
            return other == EMPTY_NODE;
        }
    }

    /**
     * Inner PunctuationNode class
     */
    public static class PunctuationNode extends Node {
        /**
         * Punctuation character stored within the node
         */
        public final char punctuation;
        public PunctuationNode(char punctuation) {
            super();
            this.punctuation = punctuation;
        }
        public PunctuationNode(Node node, char punctuation) {
            super(node);
            this.punctuation = punctuation;
        }
        @Override public String toString() { return String.valueOf(punctuation); }
        @Override public Node clone() { return new PunctuationNode(next, punctuation); }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof PunctuationNode)) return false;
            return next.equals(((Node) other).next);
        }
    }

    /**
     * Inner WordNode
     */
    public static class WordNode extends Node {
        /**
         * Word stored within the node
         */
        public final String word;
        public WordNode(String word) {
            super();
            this.word = word;
        }
        public WordNode(Node node, String word) {
            super(node);
            this.word = word;
        }
        @Override public String toString() { return word; }
        @Override public Node clone() { return new WordNode(next, word); }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof WordNode)) return false;
            return next.equals(((Node) other).next);
        }
    }
}
