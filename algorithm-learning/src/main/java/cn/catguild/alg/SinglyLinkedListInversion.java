package cn.catguild.alg;

/**
 * 单链表反转
 *
 *
 * @author liu.zhi
 * @date 2021/3/16 14:07
 */
public class SinglyLinkedListInversion {
    static ListNode successor = null; // 后驱节点

    public static void main(String[] args) {
        // 获取初始化链表
        ListNode head = ListNode.init("1,2,3,4,5");
        // 反转整个链表
        //ListNode reverse = reverse(head);
        // 反转链表前N个节点
        //ListNode reverse = reverseN(head, 3);
        ListNode reverse = reverseBetween(head, 2, 4);
        // 输出结果
        ListNode.printll(reverse);
    }

    /**
     * 利用迭代思想 反转整个单链表
     */
    private static ListNode reverseIteration(ListNode head){
        if (null == head || null == head.next){
            return head;
        }
        // 当前节点指针
        ListNode new_head_next;
        ListNode new_head_next_temp;
        ListNode new_head;

        new_head_next = head;
        new_head = null;

        while (null != new_head_next){
            // 保存new_head_next的下一个节点，防止交换的时候丢失指针指向
            new_head_next_temp = new_head_next.next;

            // 反转指针指向
            new_head_next.next = new_head;

            // 将新的头节点往后移
            new_head = new_head_next;
            // 将p节点节点往后移
            new_head_next = new_head_next_temp;
        }
        return new_head;
    }

    /**
     * 利用递归思想 反转整个单链表
     * <p>
     * 对于递归算法，最重要的就是明确递归函数的定义；
     * 不要跳进递归（你的脑袋能压几个栈呀？），而是要根据函数定义，来弄清楚这段代码会产生什么结果：
     * <p>
     * 函数定义：
     * 输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点。
     *
     * @param head 一个节点 head
     * @return 反转之后的头结点
     */
    private static ListNode reverse(ListNode head) {
        // base case: 链表只有一个节点的时候反转也是它自己，直接返回即可
        if (head.next == null) {
            return head;
        }

        // 不停的递归，head.next 会逐步靠近 null
        ListNode last = reverse(head.next);
        // 完成 第一次递归 后的后续操作
        head.next.next = head;
        head.next = null;

        return last;
    }
    //=========================================================

    /**
     * 反转以 head 为起点的 n 个节点，返回新的头结点
     * <p>
     * 递归方法定义：
     * 将链表的前 n 个节点反转（n <= 链表长度）
     * <p>
     * 写递归方法是，使用一常规参数，代入递归函数，不要跳入递归，单次执行完，符合逻辑即可
     * 列如
     * 1 -> 2 -> 3 -> 4 -> 5 -> NULL
     * 代入reverseN(head, 3)，得到如下结果，则大概率是对的
     * 3 -> 2 -> 1 -> 4 -> 5 -> NULL
     *
     * @param head 头节点，不为null
     * @param n    反转到n节点，0 < n <=链表长度
     * @return 反转之后的头节点
     */
    private static ListNode reverseN(ListNode head, int n) {
        // base case
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;

        return last;
    }

    /**
     * 反转链表的一部分
     * <p>
     * 另一个重点就是 base case ，能够确定正确的 base case 事半功倍
     * 并且 下次递归 必然要 靠近 base case 被触发的条件
     *
     * @param head 单链表头节点
     * @param m    反转开始节点
     * @param n    反转结束节点
     * @return 单链表头节点
     */
    private static ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head,n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

}
