package util;

import java.util.ArrayList;
import java.util.List;
import model.ListNode;
import model.TreeNode;

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

  /**
   * Construct a linked list from numbers
   *
   * @param nums The elements in the linked list in sequence
   * @return The head node of the formed linked list
   */
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

  /**
   * Compare the linked list with an array of integers to check if they have same values in
   * sequence
   *
   * @param node The head of the linked list to compare
   * @param nums An array of integers to compare to
   * @return If the linked list matches with the provided numbers exactly (i.e. same length too)
   */
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

  /**
   * Construct a binary tree by a list of numbers
   *
   * @param nums A list of integers as node values.
   *             <p> Notice that null values have to be given for the nodes that are null just like
   *             in LeetCode</p>
   * @return The root of the constructed binary tree
   */
  public static TreeNode constructBinaryTree(List<Integer> nums) {
    if (nums == null || nums.size() == 0) {
      return new TreeNode(); // dummy root
    }
    return constructBinaryTree(nums, null, 0);
  }

  private static TreeNode constructBinaryTree(List<Integer> nums, TreeNode root, int i) {
    // base case for recursion
    if (i < nums.size()) {
      Integer val = nums.get(i);
      TreeNode temp = (val == null ? null : new TreeNode(val));
      root = temp;

      // insert left child and right child
      if (root != null) {
        root.left = constructBinaryTree(nums, root.left, 2 * i + 1);
        root.right = constructBinaryTree(nums, root.right, 2 * i + 2);
      }
    }
    return root;
  }

  /**
   * Construct a simple list (with potential null values)
   *
   * @param nums A list of numbers
   * @return The formed integer list
   */
  @SafeVarargs
  public static <E> List<E> constructList(E... nums) {
    List<E> res = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return res;
    }
    for (E num : nums) {
      res.add(num);
    }
    return res;
  }
}
