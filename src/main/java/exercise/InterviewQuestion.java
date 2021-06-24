package exercise;

import java.util.List;
import model.ListNode;

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
  /* Backtracking **/
  /* Sorting and Searching **/
  /* Dynamic Programming **/
  /* Math **/
  /* Others **/
}
