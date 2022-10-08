package algorithm;

public class LinkedList {
    public static void main(String[] args) {
        LinkTest linkTest = new LinkTest();

        // test Q86
        System.out.println("test Question 86:");
        int[] nums = { 1, 4, 3, 2, 5, 2 };
        Link link = new Link(nums);
        link.priceLink();
        linkTest.divideLink(link, 3);
        link.priceLink();

        // test Q19
        System.out.println("test Question 19:");
        int[] nums19 = { 1, 4, 3, 2, 5, 2 };
        Link link19 = new Link(nums19);
        link19.priceLink();
        linkTest.removeNthNodeFromEnd(link19, 5);
        link19.priceLink();

        // test Q92
        System.out.println("test Question 92:");
        int[] nums92 = { 1, 2, 3, 4, 5, 6 };
        Link link92 = new Link(nums92);
        link92.priceLink();
        linkTest.reverseLinkByIndex(link92, 2, 4);
        link92.priceLink();

        // test Q234
        System.out.println("test Question 234:");
        int[] nums234 = { 1, 2, 3, 4, 5, 3, 2, 1 };
        Link link234 = new Link(nums234);
        link234.priceLink();
        System.out.println(linkTest.isPalindromeLink(link234));
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

class LinkTest {
    /**
     * Q86
     * 一个链表的头节点和一个X，对链表进行分隔，使得小于x的节点都出现在大于等于x的节点之前；
     */
    public void divideLink(Link link, int x) {
        Node headGteX = null;
        Link linkGtex = new Link(headGteX);
        Node headLtX = null;
        Link linkLtX = new Link(headLtX);
        Node node = link.head;
        while (node != null) {
            if (node.val >= x) {
                if (linkGtex.head == null) {
                    linkGtex.head = new Node(node.val);
                } else {
                    linkGtex.appendNode(new Node(node.val));
                }
            } else {
                if (linkLtX.head == null) {
                    linkLtX.head = new Node(node.val);
                } else {
                    linkLtX.appendNode(new Node(node.val));
                }
            }
            node = node.next;
        }
        if (linkLtX.head == null || linkGtex.head == null) {
            return;
        }
        linkLtX.appendNode(linkGtex.head);
        link.head = linkLtX.head;
    }

    /**
     * Q19
     * 删除链表的倒数第N个节点
     * 1.双向链表 2.快慢指针
     */

    public void removeNthNodeFromEnd(Link link, int n) {
        Node left = link.head;
        Node right = link.head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
    }

    /**
     * Q92 反转链表
     * 给你单链表的头指针head和两个整数left right 反转left到right
     * 1.使用递归 2.三段式反转
     */
    public void reverseLinkByIndex(Link link, int left, int right) {
        // 第一段不反转
        Node pre = link.head;
        for (int i = 1; i < left - 1; i++) {
            pre = pre.next;
        }

        // pre就是临时头
        Node tail = pre.next; // 反转前列表的头 就是反转后列表的尾
        Node newHead = pre.next; // 临时头

        // 第二段反转
        for (int i = left; i <= right; i++) {
            Node temp = newHead.next;
            newHead.next = pre.next; // 头部插入
            pre.next = newHead;
            newHead = temp;
        }
        tail.next = newHead;

        // 第三段不需要反转
    }

    /**
     * Q234 回文链表
     * 判断一个链表是否为回文链表
     * A1: 找到中间节点 反转链表 比较两个子链表是否相等
     */
    public boolean isPalindromeLink(Link link) {
        // 使用快慢指针查找中间节点
        Node end = null;
        Node slow = link.head, fast = link.head;
        while (fast != null && fast.next != null) {
            end = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        // 第一段链表截断
        end.next = null;
        Node left = link.head;
        Node right = Node.reverseIter(slow);

        Link linkLeft = new Link(left);
        Link linkRight = new Link(right);

        linkLeft.priceLink();
        linkRight.priceLink();

        return Link.isSameLink(linkLeft, linkRight);
    }
}