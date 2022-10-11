package algorithm;

import java.util.ArrayList;

/**
 * 递归其实只是一种迭代的写法。 而且由于需要额外保存zhan
 */

public class Recursion {
    public static void main(String[] args) {
        RecursionSolution rs = new RecursionSolution();
        System.out.println("test Q62");
        System.out.println(rs.lastRemainNumber(5, 2));

        System.out.println("test Q2");
        int[] nums1 = { 8, 2, 5 };
        Link link1 = new Link(nums1);
        int[] nums2 = { 8, 2, 7 };
        Link link2 = new Link(nums2);
        Node node3 = rs.linkNumberAdd(link1.head, link2.head, 0);
        Link link3 = new Link(node3);
        link3.priceLink();

        System.out.println("test Q60");
        int[] res60 = rs.findKarrang(4, 9);
        for (int i = 0; i < res60.length; i++) {
            System.out.print(res60[i]);
        }
        System.out.println();

    }

}

class RecursionSolution {
    /**
     * 62 圆圈中剩余的最后数字
     * 0到n-1 n个数字组成圆环；每次删除第m个数字，求最后剩余的数字
     * 
     * f(n,m) 和 f(n-1,m) 关系
     * 
     * 长度为n的序列先删除m%n个元素; 然后就变成n-1的序列;
     * f(n-1,m)=x 是留下了第x个元素； 那么长度为n的序列 最后删除的元素是 从m%n 开始数的第x个元素
     * f(n,m)= (m%n + x) % n
     */
    public int lastRemainNumber(int n, int m) {
        return f(n, m);
    }

    private int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    /**
     * 2 两数相加
     * 两个非空的链表，表示两个非负整数，数字按逆序存储，head存的是个位数；进行两数相加
     * 
     */
    public Node linkNumberAdd(Node a, Node b, int c) {
        if (a == null || b == null) {
            if (c == 1) {
                Node n = new Node(1);
                return n;
            } else {
                return (a == null ? b : a);
            }
        }
        int temp = a.val + b.val + c;
        c = 0;
        if (temp >= 10) {
            temp = temp % 10;
            c = 1;
        }

        Node node = new Node(temp);
        node.next = linkNumberAdd(a.next, b.next, c);
        return node;
    }

    private int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 60 第k个序列
     * 集合[1,2,...,n]共有 n！种排列； 按照小大顺序找出第k个排列
     */
    public int[] findKarrang(int n, int k) {
        // 1开头有 (n-1)! 个排列; k / (n-1)! + 1 为第1 位的值
        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            int j = n - i;
            int r = k / factorial(j) + 1;

            if (k == 1) {
                r = 1;
            } else {
                k = k - factorial(j) * (r - 1);
            }
            res[i - 1] = r;
            String s = String.format("k:%d j:%d, j!:%d r:%d ", k, j, factorial(j), r);
            System.out.println(s);
        }
        res[n - 1] = k;

        ArrayList nums = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // 这种解法也太难看了
        int[] res1 = new int[n];
        for (int i = 1; i <= n; i++) {
            Object r = nums.remove(res[i - 1] - 1);
            int ri = (int) r;
            res1[i - 1] = ri;
        }
        return res1;
    }
}

class Node {
    public int val;
    public Node next;

    Node(int i) {
        this.val = i;
    }

    // 反转链表的递归写法
    public Node reverse(Node head) {
        if (head.next == null) {
            return head;
        }

        Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // 反转链表的迭代写法
    public static Node reverseIter(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}

class Link {
    public Node head;

    // 构造函数 (不需要修饰符)
    Link(Node head) {
        this.head = head;
    }

    Link(int[] nums) {
        Node head = null;
        Node node = null;
        for (int i : nums) {
            Node newNode = new Node(i);
            if (head == null) {
                head = newNode;
                node = newNode;
            } else {
                node.next = newNode;
                node = node.next;
            }
        }
        this.head = head;
    }

    // 判断链表是否一致
    public static boolean isSameLink(Link a, Link b) {
        Node headA = a.head;
        Node headB = b.head;

        while (headA != null && headB != null) {
            if (headA.val != headB.val) {
                return false;
            }
            headA = headA.next;
            headB = headB.next;
        }

        if (headA == null && headB == null) {
            return true;
        }

        return false;
    }

    public Node getHead() {
        return this.head;
    }

    public void appendNode(Node newNode) {
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
    }

    // 知道尾节点时候的append
    public void appendNodeWithTail(Node newNode, Node tail) {
        tail.next = newNode;
    }

    public void priceLink() {
        Node node = this.head;
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
        System.out.println();
    }
}
