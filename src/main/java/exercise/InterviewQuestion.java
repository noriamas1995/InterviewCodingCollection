package exercise;

import java.util.List;

public interface InterviewQuestion {
  /* Arrays and Strings **/
  /**
   * @apiNote Medium
   * @param nums An integer array that has 0 <= nums.length <= 3000 and -105 <= nums[i] <= 105
   * @return All the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and
   *     nums[i] + nums[j] + nums[k] == 0
   * @see <a
   *     href="https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/">Leetcode</a>
   */
  List<List<Integer>> threeSum(int[] nums);
  /* Linked List **/
  /* Trees and Graphs **/
  /* Backtracking **/
  /* Sorting and Searching **/
  /* Dynamic Programming **/
  /* Math **/
  /* Others **/
}
