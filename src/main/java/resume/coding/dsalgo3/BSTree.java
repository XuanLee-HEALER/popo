package resume.coding.dsalgo3;

import java.util.LinkedList;

public class BSTree<T extends Comparable<T>> {

    static class BSTNode<T> {
        BSTNode<T> left;
        BSTNode<T> right;
        T element;

        public BSTNode(T element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private BSTNode<T> root;

    public BSTNode<T> getRoot() {
        return root;
    }

    private void add(BSTNode<T> node, BSTNode<T> oNode) {
        if (oNode.element.compareTo(node.element) <= 0) {
            if (node.left == null) {
                node.left = oNode;
                return;
            }
            add(node.left, oNode);
        } else {
            if (node.right == null) {
                node.right = oNode;
                return;
            }
            add(node.right, oNode);
        }
    }

    public void breadFirstSearch(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        LinkedList<BSTNode<T>> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            var item = queue.pop();
            System.out.println(item.element);

            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }
    }

    public void add(T element) {
        // root要在外面判断，如果传进私有方法得到的是root的拷贝
        if (root == null) {
            root = new BSTNode<>(element);
            return;
        }
        add(root, new BSTNode<>(element));
    }
    
    public void preOrder(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.element);
    }

    public void inOrder(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.element);
        inOrder(node.right);
    }

    public void reverse(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        var t = node.left;
        node.left = node.right;
        node.right = t;

        reverse(node.left);
        reverse(node.right);
    }
}   
