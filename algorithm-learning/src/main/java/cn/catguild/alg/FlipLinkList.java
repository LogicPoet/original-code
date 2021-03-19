package cn.catguild.alg;

/**
 *  K个一组反转链表
 *  给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 *
 *  k是一个正整数，它的值小于或等于链表的长度。
 *  如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * @author liu.zhi
 * @date 2021/3/19 14:14
 */
public class FlipLinkList {

    public static void main(String[] args) {
        // 获取初始化链表
        ListNode head = ListNode.init("1,2,3,4,5");
        // 反转整个链表
        ListNode reverse = reverse(head);
        // 输出结果
        ListNode.printll(reverse);
    }

    // 反转以 a 为头结点的链表
    private static ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        while (cur != null) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }
}
