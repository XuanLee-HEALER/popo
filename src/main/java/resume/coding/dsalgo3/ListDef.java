package resume.coding.dsalgo3;

import java.util.Objects;
import java.util.function.Predicate;

public class ListDef<T> {

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next);
        }
    }

    Node<T> head = null;

    // O(1)
    public void insert(T data) {
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
    }

    // O(N)
    public Node<T> find(Predicate<T> predicate) {
        var p = head;
        while (p != null) {
            if (predicate.test(p.data)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public void remove(Node<T> node) {
        if (head == null) {
            return;
        }

        if (head == node) {
            head = head.next;
        }

        var slow = head;
        var fast = head.next;

        while (fast != node && fast != null) {
            slow = fast;
            fast = fast.next;
        }

        if (fast != null) {
            // fast还在指向data
            slow.next = fast.next;
            // 要考虑实际情况，看数据是否需要显式地回收内存
            // fast.data = null;
        }
    }

    // 可以把计算链表大小的数放在插入、删除操作中
    public int size() {
        return 0;
    }

    // 三指针翻转链表
    public void reverse1() {
        Node<T> prev = null;
        var cur = head;
        Node<T> next;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        head.next = null;
        head = prev;
    }

    public void reverse2() {
        head = reverseRecursive(head);
    }

    public Node<T> reverseRecursive(Node<T> node) {
        if (node == null || node.next == null) {
            return node;
        }

        // 递归成功，只需要操作头元素
        // 从状态空间角度思考，上一层递归保存了某个节点的后继节点内容，即使下一层递归丢失了这个信息也不再重要
        Node<T> rest = reverseRecursive(node.next);
        node.next.next = node;
        node.next = null;

        return rest;
    }

    // 只要进到环中，快指针每次比慢指针多走一步，总会追上慢指针
    public boolean hasLoop() {
        if (head == null || head.next == null) {
            return false;
        }

        var slow = head;
        var fast = head.next.next;

        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}
