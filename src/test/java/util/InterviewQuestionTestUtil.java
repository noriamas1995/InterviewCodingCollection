package util;

import model.ListNode;

public final class InterviewQuestionTestUtil {

  private InterviewQuestionTestUtil() {
    throw new AssertionError();
  }

  public static void display2dArray(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static ListNode constructLinkedList(int... nums) {
    if (nums.length == 0) {
      return new ListNode(0);
    }

    ListNode head = new ListNode(nums[0]);
    ListNode curr = head;

    for (int i = 1; i < nums.length; i++) {
      curr.next = new ListNode(nums[i]);
      curr = curr.next;
    }
    return head;
  }

  public static boolean compareLinkedListValues(ListNode node, int... nums) {
    for (int num : nums) {
      if (node.val != num) {
        return false;
      }
      node = node.next;
    }

    // after finishing the array if there are still extra nodes then not equal
    if (node != null) {
      return false;
    }

    return true;
  }
}
