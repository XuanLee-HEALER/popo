package resume.coding.dsalgo3;

import org.junit.Test;

public class TreeTest {

    static class TreePrinter {

        <T> int heightOfTree(BSTree.BSTNode<T> node) {
            if (node == null) {
                return 0;
            }
            return Math.max(
                    heightOfTree(node.left),
                    heightOfTree(node.right)
            ) + 1;
        }

        public <T> void printTree(BSTree.BSTNode<T> root) {
            int h = heightOfTree(root);
            int W = (int) Math.pow(2, h + 1);
            var lines = new StringBuilder[h*2];
            for (int i = 0; i < lines.length; i++) {
                lines[i] = new StringBuilder(String.format("%" + W + "s", ""));
            }
            printNode(lines, W, root, 0, 0);
            for (var line: lines) {
                System.out.println(line.toString());
            }
        }

        private <T> void printNode(StringBuilder[] lines, int W, BSTree.BSTNode<T> node, int h, int base) {
            if (node == null) {
                return;
            }
            var nums = Math.pow(2, h);
            var pos = base + (int)(W/(nums * 2));

            var str = node.element.toString();
            for (int i = 0; i < lines.length; i++) {
                lines[h*2].setCharAt(pos+i, str.charAt(i));
            }
            if(node.left != null) {
                lines[h*2+1].setCharAt(pos-1, '/');
                printNode(lines, W, node.left, h+1, base);
            }

            if(node.right != null) {
                lines[h*2 + 1].setCharAt(pos + str.length(), '\\');
                printNode(lines, W, node.right, h+1, pos);
            }
        }
    }

    static class TreePrinter2 {

        <T> int heightOf(BSTree.BSTNode<T> node) {
            if(node == null) {
                return 0;
            }
            return Math.max(
                    heightOf(node.left),
                    heightOf(node.right)
            ) + 1;
        }


        public <T> void print(BSTree.BSTNode<T> root) {
            int h = heightOf(root);
            int W = 2*(int)Math.pow(2, h);
            var lines = new StringBuilder[h*2];
            for(int i = 0; i < h*2; i++) {
                lines[i] = new StringBuilder(String.format("%" + W + "s", ""));
            }

            printNode(lines, W, root, 0, 0);
            for(var line : lines) {
                System.out.println(line.toString());
            }

        }

        private <T> void printNode(StringBuilder[] lines, int W,  BSTree.BSTNode<T> node, int h, int base) {
            var nums = Math.pow(2, h);
            var pos = base + (int)(W / (nums *  2));

            var str = node.element.toString();
            for(int i = 0; i < str.length(); i++) {
                lines[h*2].setCharAt(pos + i, str.charAt(i));
            }

            if(node.left != null) {
                lines[h*2+1].setCharAt(pos-1, '/');
                printNode(lines, W, node.left, h+1, base);
            }

            if(node.right != null) {
                lines[h*2 + 1].setCharAt(pos + str.length() + 1, '\\');
                printNode(lines, W, node.right, h+1, pos);
            }


        }

    }
    @Test
    public void bsTreeTest() {
        TreePrinter2 tp = new TreePrinter2();

        BSTree<Integer> tree = new BSTree<>();
        tree.add(15);
        tree.add(7);
        tree.add(4);
        tree.add(18);
        tree.add(20);
        tree.add(1);
        tree.add(3);

//        tree.preOrder(tree.getRoot());
//        tp.print(tree.getRoot());
//        tree.reverse(tree.getRoot());
//        tree.inOrder(tree.getRoot());
        tree.breadFirstSearch(tree.getRoot());
    }
}
