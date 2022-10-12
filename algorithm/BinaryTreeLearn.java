package algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树
 * 
 * 1.二叉树有两种存储结构，一种是顺序存储，一种是链式存储；
 * 2.满二叉树比完全二叉树更完全；
 * 3.二叉树的深度优先遍历就是<先序遍历> 广度优先遍历就是<中序遍历>
 */

public class BinaryTreeLearn {
    public static void main(String[] args) {
        BinaryTreeSolution bs = new BinaryTreeSolution();

        System.out.println("test Q102");
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8 };
        BinaryTree tree102 = new BinaryTree(nums);
        List<List<Integer>> levelList = bs.layerTravel(tree102.root);
        for (List<Integer> level : levelList) {
            System.out.println(level);
        }
    }

}

class BinaryTree {
    public BinaryTreeNode root;
    // public int depth;

    // 构造一棵二叉树 顺序存储 -> 链式存储
    public BinaryTree(int[] arrs) {
        if (arrs.length == 0) {
            return;
        }
        this.root = new BinaryTreeNode(arrs[0]);
        this.constructTree(arrs, this.root, 0);
    }

    private void constructTree(int[] arrs, BinaryTreeNode father, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < arrs.length) {
            BinaryTreeNode node = new BinaryTreeNode(arrs[left]);
            father.left = node;
            this.constructTree(arrs, node, left);
        }
        if (right < arrs.length) {
            BinaryTreeNode node = new BinaryTreeNode(arrs[right]);
            father.right = node;
            this.constructTree(arrs, node, right);
        }
    }

    // 递归函数当作 实例函数时有个问题; 实例本身还是需要通过参数显式的穿进去； 不如直接定义成类函数

    // 先序遍历
    public static void preOrder(BinaryTreeNode node) {
        // 递归的函数 一定要注意些终止条件
        if (node == null) {
            return;
        }
        System.out.print(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }
}

class BinaryTreeNode {
    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int v) {
        this.val = v;
    }
}

class BinaryTreeSolution {
    /**
     * Q 102 二叉树的层序遍历
     */
    public List<List<Integer>> layerTravel(BinaryTreeNode root) {
        // 和preOrder相比 还需要每行分隔

        // 使用迭代的写法 （栈）
        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            // 现在queue里存了一层
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            res.add(level);
        }
        return res;
    }
}