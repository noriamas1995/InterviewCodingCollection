package exercise;

import java.util.List;
import model.ListNode;
import model.Node;
import model.TreeNode;

public interface InterviewQuestion {
  /* Arrays and Strings **/

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/">Leetcode</a>
   */
  List<List<Integer>> threeSum(int[] nums);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/">Leetcode</a>
   */
  void setZeroes(int[][] matrix);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/778/">Leetcode</a>
   */
  List<List<String>> groupAnagrams(String[] strs);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/779/">Leetcode</a>
   */
  int lengthOfLongestSubstring(String s);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/780/">Leetcode</a>
   */
  String longestPalindrome(String s);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/781/">Leetcode</a>
   */
  boolean increasingTriplet(int[] nums);

  /* Linked List **/

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/783/">Leetcode</a>
   */
  ListNode addTwoNumbers(ListNode l1, ListNode l2);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/784/">Leetcode</a>
   */
  ListNode oddEvenList(ListNode head);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/785/">Leetcode</a>
   */
  ListNode getIntersectionNode(ListNode headA, ListNode headB);

  /* Trees and Graphs **/

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/786/">Leetcode</a>
   */
  List<Integer> inorderTraversal(TreeNode root);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/787/">Leetcode</a>
   */
  List<List<Integer>> zigzagLevelOrder(TreeNode root);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/788/">Leetcode</a>
   */
  TreeNode buildTree(int[] preorder, int[] inorder);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/789/">Leetcode</a>
   */
  Node connect(Node root);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/790/">Leetcode</a>
   */
  int kthSmallest(TreeNode root, int k);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/792/">Leetcode</a>
   */
  int numIslands(char[][] grid);

  /* Backtracking **/

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/793/">Leetcode</a>
   */
  List<String> letterCombinations(String digits);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/794/">Leetcode</a>
   */
  List<String> generateParenthesis(int n);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/795/">Leetcode</a>
   */
  List<List<Integer>> permute(int[] nums);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/796/">Leetcode</a>
   */
  List<List<Integer>> subsets(int[] nums);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/797/">Leetcode</a>
   */
  boolean exist(char[][] board, String word);

  /* Sorting and Searching **/
  /* Dynamic Programming **/
  /* Math **/

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/113/math/815/">Leetcode</a>
   */
  boolean isHappy(int n);

  /**
   * @see <a href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/113/math/816/">Leetcode</a>
   */
  int trailingZeroes(int n);

  /* Others **/
}
