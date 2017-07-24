package com.howard.linkedList;

import java.util.HashMap;

/**
 * 单链表
 * @author Howard
 * 2017年4月9日
 */
public class MyLinkedList {
	Node head = null;
	/**
	 * 插入数据
	 * @param d
	 */
	public void addNode(int d) {
		Node newNode = new Node(d);
		if (head == null) {
			head = newNode;
			return;
		}
		Node tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = newNode;
	}
	public void addByNode(Node newNode) {
//		Node newNode = new Node(d);
		if (head == null) {
			head = newNode;
			return;
		}
		Node tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = newNode;
	}
	/**
	 * 求链表长度
	 * @return
	 */
	public int length() {
		int length = 0;
		Node tmp = head;
		while (tmp != null) {
			length++;
			tmp = tmp.next;
		}
		return length;
	}
	/**
	 * 删除指定索引的结点
	 * @param index
	 * @return
	 */
	public boolean deleteNode(int index) {
		if (index < 1 || index > length()) {
			return false;
		}
		if (index == 1) {
			head = head.next;
			return true;
		}
		int i = 2;
		Node preNode = head;
		Node curNode = preNode.next;
		while(curNode != null) {
			if (i == index) {
				preNode.next = curNode.next;
				return true;
			}
			preNode = curNode;
			curNode = curNode.next;
			i++;
		}
		return true;
	}
	/**
	 * 链表排序
	 * 使用选择排序
	 * 只交换数据并没有交换结点
	 * @return
	 */
	public Node orderList() {
		Node nextNode = null;
		int tmp = 0;
		Node curNode = head;
		while (curNode.next != null) {
			nextNode = curNode.next;
			while (nextNode != null) {
				if (curNode.data > nextNode.data) {
					tmp = curNode.data;
					curNode.data = nextNode.data;
					nextNode.data = tmp;
				}
				nextNode = nextNode.next;
			}
			curNode = curNode.next;
		}
		return head;
	}
	/**
	 * 正序打印链表
	 */
	public void printList() {
		Node tmp = head;
		while (tmp != null) {
			System.out.print(tmp.data+" ");
			tmp = tmp.next;
		}
		System.out.println();
	}
	/**
	 * 倒数打印链表
	 * 方法1：使用栈辅助
	 * 方法2：使用递归
	 * @param curHead
	 */
	public void printListReverse(Node curHead) {
		if (curHead != null) {
			printListReverse(curHead.next);
			System.out.print(curHead.data+" ");
		}
	}
	
	/**
	 * 从链表中删除重复元素
	 * 方法1：
	 * 时间复杂度较低 但是需要额外空间
	 * @param head
	 */
	public void deleteDuplecate1() {
		HashMap<Integer, Integer> map = new HashMap<>();
		Node tmp = head;
		Node pre = null;
		while (tmp != null) {
			if (map.containsKey(tmp.data))
				pre.next = tmp.next;
			else {
				map.put(tmp.data, 1);
				pre = tmp;
			}
			tmp = tmp.next;
		}
	}
	/**
	 * 从链表中删除重复元素
	 * 方法2：双重循环链表 
	 * 优点 空间复杂度低 缺点：时间复杂度高
	 * 外循环正常遍历链表 内循环从当前遍历到的地方开始往后遍历 遇到相等的就删除
	 * 另一个思路： 外循环正常遍历链表 内循环从链表头遍历到外循环当前所在位置 遇到相等的就删除，
	 * 只要遇到一个相等的删除该内循环就结束，外循环继续，如此循环。
	 * 因为这个方式内循环最多遇到一个重复的 某些情况下这个方式相对前面方式会适当提高效率，
	 */
	public void deleteDuplecate2() {
		Node p = head;
		while (p != null) {
			Node q = p;
			while (q.next != null) {
				if (p.data == q.next.data) {
					q.next = q.next.next;
				}else {
					q = q.next;
				}
			}
			p = p.next;
		}
	}
	/**
	 * 找出单链表中倒数第k个数
	 * （这里借用c的指针概念，实际java不叫指针，只有引用，之前学数据结构用的c，说习惯了）
	 * 方法1：
	 * 先遍历一次链表，找出长度n，(这里也可以调用length,当是这种题一般是单独出的，所以不会提供给你这个函数)，
	 * 然后遍历正数n-k个即可
	 * 方法2：
	 * 从头结点开始，遍历，对每个结点进行测试：遍历k个元素，看是否到达尾部，若是，说明该结点为倒数第k个
	 * 效率O(kn)，较低
	 * 方法3：
	 * 只遍历一次
	 * 两个引用(按c来说应该叫指针)，一个先往前移动k-1步，然后两个‘指针’同时开始往后继续遍历，直到后面那个达到尾部停止，
	 * 此时前面那个指向的就是倒数第k个元素，算法如下：
	 * @param k
	 * @return
	 */
	public Node findElem(int k) {
		if (k < 1 || k > length()) {
			return null;
		}
		Node p1 = head;
		Node p2 = head;
		for (int i = 0; i < k; i++) {
			p1 = p1.next;
		}
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
	/**
	 * 链表反转
	 */
	public void reverseList() {
		//反转后的链表头
		Node pReverseHead = head;
		Node pNode = head;
		//用于保存上一个链表的结点 初始为null，因为头结点的下一个结点在反转后就变成尾结点，指向null
		Node pPre = null;
		while (pNode != null) {
			//临时保存正常顺序的下一个结点
			Node pNext = pNode.next;
			if (pNext == null) {
				//反转到最后一个结点了，但是后面还要继续将最后一个结点进行处理，因为反转后其下一指向是正常顺序的倒数第二个结点
				pReverseHead = pNode;
			}
			pNode.next = pPre;
			pPre = pNode;
			pNode = pNext;
		}
		this.head = pReverseHead;
	}
	/**
	 * 寻找单链表的中间结点
	 * 如果是双向链表，可以分别从头和尾同时遍历，直到相遇，相遇处即为中间结点
	 * 单链表也可以用两个引用同时遍历，一个一次走一步，一个一次走两步，直到快的到达尾部，则慢的所处位置为中间结点
	 * 如果链表长度是偶数，则慢的所在结点处和下一个结点都是中间结点
	 * @return
	 */
	public Node SearchMidNode() {
		//快的
		Node p = head;
		//慢的
		Node q = head;
		//用快的作为条件判断
		while (p != null && p.next != null && p.next.next != null) {
			p = p.next.next;
			q = q.next;
		}
		return q;
	}
	/**
	 * 检测链表是否有环
	 * 用两个引用，一个快一个慢，快的每次走两步，慢的每次遍历前进一个结点
	 * 直到快的等于慢的，证明链表有环，否则不带环
	 * @param head
	 * @return
	 */
	public boolean IsLoop(Node head) {
		Node fast = head;
		Node slow = head;
		if (fast == null) {
			return false;
		}
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
		return !(fast == null || fast.next == null);
	}
	/**
	 * 找到有环链表的的入环口的结点
	 * 思路：如果有环，那么按照上面找环的思路，走的快的指针fast和走的慢的指针slow相遇时，slow没有遍历完，fast循环了n圈环，假设slow指针走了
	 * s步，那么fast走了2s步。2s又等于s加上在环内多走的n圈，设环长r，则有2s = s+nr，即s = nr，
	 * 设链表长L，入环口和相遇点距离x，起点到入环口距离a，则：
	 * a+x = nr
	 * a+x = (n -1)r + r = (n - 1)r + L -a
	 * a = (n - 1)r + (L - a -x)
	 * ?????(以下未明白)
	 * (L - a -x)为相遇点到环入口的距离，从链表头到环入口点等于n-1循环内环+相遇点到环入口，于是在链表头与相遇点分别设一个指针，每次各走一步，
	 * 两指针必相遇，且相遇第一点为环入口点
	 * @param head
	 * @return
	 */
	public Node FindLoopPort(Node head) {
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) break;
		}
		if (fast == null || fast.next == null) {
			return null;
		}
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}
	/**
	 * 判断两个链表是否相交
	 * 如果相交，那么一定有相同的尾结点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public boolean isIntersect(Node n1,Node n2) {
		if (n1 == null || n2 == null) {
			return false;
		}
		Node tail1 = n1;
		Node tail2 = n2;
		while (tail1.next != null) {
			tail1 = tail1.next;
		}
		while (tail2.next != null) {
			tail2 = tail2.next;
		}
		return tail1 == tail2;
	}
	/**
	 * 如果两链表相交，找到第一个相交的结点
	 * 思路：分别求出两链表各自长度，然后用长的减去短的，得到差值，然后
	 * 较长的遍历到等于差值的结点处，此时两链表离相交点的距离都相等
	 * 这时只要一起遍历结点，直到两结点相等即可
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static Node getFirstMeetNode (Node n1,Node n2) {
		if (n1 == null || n2 == null) {
			return null;
		}
		Node tail1 = n1;
		Node tail2 = n2;
		int len1 = 0;
		int len2 = 0;
		while (tail1.next != null) {
			tail1 = tail1.next;
			len1++;
		}
		while (tail2.next != null) {
			tail2 = tail2.next;
			len2++;
		}
		if (tail1 != tail2) {
			return null;
		}
		Node t1 = n1;
		Node t2 = n2;
		if (len1 > len2) {
			int d = len1 - len2;
			while (d != 0) {
				t1 = t1.next;
				d--;
			}
		}else {
			int d = len2 - len1;
			while (d != 0) {
				t2 = t2.next;
				d --;
			}
		}
		while (t1 != t2) {
			t1 = t1.next;
			t2 = t2.next;
		}
		return t1;
	}
	
	public static void main(String[] args) {
//		MyLinkedList list = new MyLinkedList();
//		list.addNode(5);
//		list.addNode(3);
//		list.addNode(2);
//		list.addNode(7);
//		list.addNode(3);
//		System.out.println(list.length());
//		list.printList();
//		list.orderList();
//		list.printList();
//		list.deleteNode(2);
//		list.printList();
//		list.deleteDuplecate1();
//		list.deleteDuplecate2();
//		list.printList();
//		Node node = list.findElem(3);
//		if (node == null)
//			System.out.println(node);
//		else System.out.println(node.data);
//		list.reverseList();
//		list.printList();
//		list.printListReverse(list.head);
//		System.out.println();
//		Node node = list.SearchMidNode();
//		if (node == null)
//			System.out.println(node);
//		else System.out.println(node.data);
		//判断是否存在环
		MyLinkedList list = new MyLinkedList();
		list.addNode(5);
		list.addNode(6);
		list.addNode(7);
		Node newNode = new Node(10);
		list.addByNode(newNode);
		list.addNode(1);
		list.addByNode(newNode);
		boolean flag = list.IsLoop(list.head);
		System.out.println(flag);
		Node findLoopPort = list.FindLoopPort(list.head);
		System.out.println(findLoopPort==null?findLoopPort:findLoopPort.data);
		
		//判断两链表是否相交 找到相交开始的结点
//		MyLinkedList list1 = new MyLinkedList();
//		list1.addNode(1);
//		list1.addNode(2);
//		list1.addNode(3);
//		Node newNode1 = new Node(10);
//		Node newNode2 = new Node(11);
//		Node newNode3 = new Node(12);
//		list1.addByNode(newNode1);
//		list1.addByNode(newNode2);
//		list1.addByNode(newNode3);
//		
//		MyLinkedList list2 = new MyLinkedList();
//		list2.addNode(5);
//		list2.addNode(6);
//		list2.addByNode(newNode1);
//		list1.printList();
//		list2.printList();
//		boolean flag = list1.isIntersect(list1.head, list2.head);
//		System.out.println(flag);
//		Node firstMeetNode = getFirstMeetNode(list1.head, list2.head);
//		System.out.println(firstMeetNode==null?firstMeetNode:firstMeetNode.data);
		
		
	}
}
