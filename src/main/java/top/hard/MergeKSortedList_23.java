package top.hard;

import utils.ListNode;

public class MergeKSortedList_23 {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists.length == 0)
                return null;
            return mergeHelper(lists, 0, lists.length-1);
        }

        private ListNode merge2List(ListNode l1, ListNode l2) {
            ListNode tempNode = new ListNode(0);
            ListNode pointNode = tempNode;

            while(l1 != null && l2 != null) {
                int temp;
                if(l1.val > l2.val) {
                    temp = l2.val;
                    l2 = l2.next;
                } else {
                    temp = l1.val;
                    l1 = l1.next;
                }
                ListNode nextNode = new ListNode(temp);
                pointNode.next = nextNode;
                pointNode = nextNode;
            }

            pointNode.next = l1 == null ? l2 : l1;
            return tempNode.next;
        }

        private ListNode mergeHelper(ListNode[] lists, int left, int right) {
            ////【错误点】 下面是>= 注意边界！
            if(left >= right)
                return lists[left];
            int mid = (left + right) / 2;
            ListNode leftMerged = mergeHelper(lists, left, mid);
            ListNode rightMerged = mergeHelper(lists, mid + 1, right);
            return merge2List(leftMerged, rightMerged);
        }
//     【一句话】 堆，所有节点入堆， 出堆，三个指针连起来。

//     public ListNode mergeKLists(ListNode[] lists) {
//         //创建优先队（堆），并且指明优先队的规则是比较Node的val，升序
//         PriorityQueue<ListNode> pqueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

//         // 过一遍列表，传入 首节点 ，从首节点开始遍历，把每个列表里的每个节点入队
//         for (int i = 0; i < lists.length ; i++) {
//             this.addListToPQueue(lists[i] , pqueue);
//         }

//         // 三个指针，上一个指针，当前指针，头指针
//         ListNode lastNode = null;
//         ListNode currentNode = null;
//         ListNode head = null;

//         // loop队列，如果队列不为空
//         while(!pqueue.isEmpty()) {

//             // 取出第一个节点，作为当前节点
//             currentNode = pqueue.poll();

//             // 让上一个指针指向当前指针
//             if(lastNode == null){
//                 // 如果上个节点指针为空，意思是这个是第一个节点
//                 //we have the first node , head
//                 head = currentNode;
//             }else{
//                 // 如果不是第一个节点，上一个指针的下一个指向当前节点
//                 lastNode.next = currentNode;
//             }

//             //上一个指针等于当前指针
//             lastNode = currentNode;

//         }
//         // 如果队列是空的，表示已经过完了， 最后一个节点如果不为空，把它的next设为null
//         if(currentNode != null){
//             currentNode.next = null;
//         }

//         // 返回头指针
//         return head;
//     }

//     private void addListToPQueue(ListNode head , PriorityQueue<ListNode> pqueue){
//         ListNode currentNode = head;

//         while(currentNode != null){
//             pqueue.add(currentNode);
//             currentNode = currentNode.next;
//         }

//     }
}
