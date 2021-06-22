package exercise;

import java.util.List;
import model.ListNode;

public interface InterviewQuestion {
  /* Arrays and Strings **/
  /**
   * @see <a
   *     href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/">Leetcode</a>
   */
  List<List<Integer>> threeSum(int[] nums);

  /**
   * @see <a
   *     href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/">Leetcode</a>
   */
  void setZeroes(int[][] matrix);

  /**
   * @see <a
   *     href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/778/">Leetcode</a>
   */
  List<List<String>> groupAnagrams(String[] strs);

  /* Linked List **/
  /**
   * @see <a
   *     href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/783/">Leetcode</a>
   */
  ListNode addTwoNumbers(ListNode l1, ListNode l2);

  /* Trees and Graphs **/
  /* Backtracking **/
  /* Sorting and Searching **/
  /* Dynamic Programming **/
  /* Math **/
  /* Others **/
}
