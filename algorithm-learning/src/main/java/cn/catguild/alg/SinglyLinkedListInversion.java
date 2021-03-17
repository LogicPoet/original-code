package cn.catguild.alg;

/**
 * 单链表反转
 *
 * @author liu.zhi
 * @date 2021/3/16 14:07
 */
public class SinglyLinkedListInversion {

    public static void main(String[] args) {
        // 获取初始化链表
        ListNode head = ListNode.init("1,2,3,4,5");
        // 反转
        ListNode reverse = reverse(head);
        // 输出结果
        ListNode.printll(reverse);
    }
    //=========================================================

    /**
     * 利用迭代思想 反转整个单链表
     */
    private static ListNode reverse(ListNode head){
        if (null == head || null == head.next){
            return head;
        }
        // 当前节点指针
        ListNode p;
        ListNode p_temp;
        ListNode new_head;
        p = head;
        new_head = null;

        while (null != p){
            p_temp = p.next;
            p.next = new_head;
            new_head = p;
            p = p_temp;
        }
        return new_head;
    }
    //=========================================================

}
