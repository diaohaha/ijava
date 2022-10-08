package algorithm;

public class TreeTest {
    public static void main(String[] args) {
        // test build tree by list
        int[] treeList = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        BinaryTree tree1 = new BinaryTree();
        tree1.makeTreeByArr(treeList);
        tree1.printTree();
        System.out.println();
        // test build tree by list

        int depth = tree1.getDepth();
        System.out.println(String.format("tree depth is %d", depth));

    }
}

class BinaryTree {
    private TreeNode root;
    // TreeNode left; // 对象 ：引用下一个节点对象。在Java中没有指针的概念，Java中的引用和C语言的指针类似

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // 通过列表生成tree (或者可以直接写成构造函数)
    public void makeTreeByArr(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        this.root = new TreeNode(nums[0]);
        this.constructTrueByList(nums, this.root, 0);
    }

    private void constructTrueByList(int[] nums, TreeNode node, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < nums.length) {
            TreeNode leftNode = new TreeNode(nums[left]);
            node.setLeft(leftNode);
            this.constructTrueByList(nums, leftNode, left);
        }
        if (right < nums.length) {
            TreeNode rightNode = new TreeNode(nums[right]);
            node.setRight(rightNode);
            this.constructTrueByList(nums, rightNode, right);
        }
    }

    public int getDepth() {
        return depth(this.root);
    }

    // 计算树的深度
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.getLeft());
        int rightDepth = depth(node.getLeft());
        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        } else {
            return rightDepth + 1;
        }
    }

    public void printTree() {
        preOrder(this.root);
    }

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getVal());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrder(node.getLeft());
        preOrder(node.getRight());
        System.out.print(node.getVal());
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrder(node.getLeft());
        System.out.print(node.getVal());
        preOrder(node.getRight());
    }
}

class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getRight() {
        return this.right;
    }

}
