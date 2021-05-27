package cn.catguild.alg;

/**
 * 单链表 定义
 *
 * @author liu.zhi
 * @date 2021/3/16 14:05
 */
public class ListNode {
    /**
     * 当前元素值
     **/
    int val;
    /**
     * 下一个节点
     **/
    ListNode next;

    /**
     * 构造函数
     **/
    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 初始一个单链表
     *
     * @param link 序列 链表 值字符串 逗号分隔
     * @return 返回头节点
     */
    public static ListNode init(String link) {
        if (null == link || "".equals(link)) {
            return new ListNode();
        }
        String[] split = link.split(",");
        ListNode pre = new ListNode(Integer.parseInt(split[0]));
        ListNode head = pre;
        for (int i = 1; i < split.length; i++) {
            ListNode next = new ListNode(Integer.parseInt(split[i]));
            pre.next = next;
            pre = next;
        }
        return head;
    }

    /**
     * 遍历输出 结果
     *
     * @param head
     */
    public static void printll(ListNode head) {
        if (head == null) {
            System.out.print("NULL");
            return;
        }
        System.out.print(head.val + " -> ");
        printll(head.next);
    }
}
