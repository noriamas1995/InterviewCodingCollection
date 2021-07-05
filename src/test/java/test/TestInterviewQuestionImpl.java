package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.InterviewQuestionTestUtil.compareLinkedListValues;
import static util.InterviewQuestionTestUtil.constructBinaryTree;
import static util.InterviewQuestionTestUtil.constructLinkedList;
import static util.InterviewQuestionTestUtil.constructList;
import static util.InterviewQuestionTestUtil.display2dArray;

import exercise.InterviewQuestion;
import exercise.InterviewQuestionImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ListNode;
import model.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInterviewQuestionImpl implements TestInterviewQuestion {

  private InterviewQuestion qn;

  @BeforeEach
  public void init() {
    qn = new InterviewQuestionImpl();
  }

  @Override
  @Test
  public void testThreeSum() {
    int[] testCase = new int[]{1, 2, 5, -5, 3, -6, -4, 5, 2, 2, 3, 5, -6};
    final List<List<Integer>> testResult = qn.threeSum(testCase);
    assertEquals(5, testResult.size());
    assertEquals(List.of(List.of(-6, 1, 5), List.of(-6, 3, 3), List.of(-5, 2, 3), List.of(-4, 1, 3),
        List.of(-4, 2, 2)), testResult);
  }

  @Override
  @Test
  public void testSetZeroes() {
    int[][] matrix = new int[][]{{1, 0, 1, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 1, 1, 1},
        {1, 0, 0, 1, 1}};
    qn.setZeroes(matrix);
    display2dArray(matrix);
    assertEquals(1, matrix[2][3]);
  }

  @Override
  @Test
  public void testGroupAnagrams() {
    final String note = """
        Input: strs = ["eat","tea","tan","ate","nat","bat"]
        Output: [["bat"],["nat","tan"],["ate","eat","tea"]] (may not be in sequence)
        """;
    List<List<String>> result = qn
        .groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    assertEquals(3, result.size());
  }

  @Override
  @Test
  public void testLengthOfLongestSubstring() {
    assertEquals(3, qn.lengthOfLongestSubstring("abcabcbb"));
    assertEquals(1, qn.lengthOfLongestSubstring("bbbbb"));
    assertEquals(3, qn.lengthOfLongestSubstring("pwwkew"));
  }

  @Override
  @Test
  public void testLongestPalindrome() {
    assertEquals("aba", qn.longestPalindrome("babad")); // or "bab"
    assertEquals("bb", qn.longestPalindrome("cbbd"));
    assertEquals("a", qn.longestPalindrome("a"));
    assertEquals("c", qn.longestPalindrome("ac")); // or "a"
    assertEquals("bb", qn.longestPalindrome("bb"));
  }

  @Override
  @Test
  public void testIncreasingTriplet() {
    assertEquals(true, qn.increasingTriplet(new int[]{1, 2, 3, 4, 5}));
    assertEquals(false, qn.increasingTriplet(new int[]{5, 4, 3, 2, 1}));
    assertEquals(true, qn.increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
  }

  @Override
  @Test
  public void testAddTwoNumbers() {
    /**
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807
     */
    ListNode l1 = constructLinkedList(2, 4, 3);
    ListNode l2 = constructLinkedList(5, 6, 4);
    ListNode result = qn.addTwoNumbers(l1, l2);
    assertTrue(compareLinkedListValues(result, 7, 0, 8));

    /**
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     */
    l1 = constructLinkedList(0);
    l2 = constructLinkedList(0);
    result = qn.addTwoNumbers(l1, l2);
    assertEquals(0, result.val);
    assertNull(result.next);

    /**
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     */
    l1 = constructLinkedList(9, 9, 9, 9, 9, 9, 9);
    l2 = constructLinkedList(9, 9, 9, 9);
    result = qn.addTwoNumbers(l1, l2);
    assertTrue(compareLinkedListValues(result, 8, 9, 9, 9, 0, 0, 0, 1));
  }

  @Override
  @Test
  public void testOddEvenList() {
    ListNode test = constructLinkedList(1, 2, 3, 4, 5);
    qn.oddEvenList(test);
    assertTrue(compareLinkedListValues(test, 1, 3, 5, 2, 4));

    test = constructLinkedList(2, 1, 3, 5, 6, 4, 7);
    qn.oddEvenList(test);
    assertTrue(compareLinkedListValues(test, 2, 3, 6, 7, 1, 5, 4));
  }

  @Override
  @Test
  public void testGetIntersectionNode() {
    final String note = """
        Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
        Output: Intersected at '8'
        Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
        From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
        There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
        """;
    ListNode a = new ListNode(4);
    ListNode headA = a;
    a.next = new ListNode(1);
    a = a.next;

    ListNode b = new ListNode(5);
    ListNode headB = b;
    b.next = new ListNode(6);
    b = b.next;
    b.next = new ListNode(1);
    b = b.next;

    ListNode intersection = new ListNode(8);
    a.next = intersection;
    b.next = intersection;

    intersection.next = new ListNode(4);
    intersection.next.next = new ListNode(5);

    ListNode result = qn.getIntersectionNode(headA, headB);
    assertEquals(8, result.val);
    assertTrue(compareLinkedListValues(result, 8, 4, 5));
  }

  @Override
  @Test
  public void testInOrderTraversal() {
    TreeNode root = constructBinaryTree(constructList(1, null, 2, null, null, 3, null));
    final List<Integer> testResult = qn.inorderTraversal(root);
    assertEquals(3, testResult.size());
    assertEquals(List.of(1, 3, 2), testResult);
  }

  @Override
  @Test
  public void testZigzagLevelOrder() {
    /**
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[20,9],[15,7]]
     */
    TreeNode root = constructBinaryTree(constructList(3, 9, 20, null, null, 15, 7));
    List<List<Integer>> testResult = qn.zigzagLevelOrder(root);
    assertEquals(3, testResult.size());
    assertEquals(List.of(List.of(3), List.of(20, 9), List.of(15, 7)), testResult);

    /**
     * Input: root = [1,2,3,4,5,6,7]
     * Output: [[1],[3,2],[4,5,6,7]]
     */
    root = constructBinaryTree(constructList(1, 2, 3, 4, 5, 6, 7));
    testResult = qn.zigzagLevelOrder(root);
    assertEquals(3, testResult.size());
    assertEquals(List.of(List.of(1), List.of(3, 2), List.of(4, 5, 6, 7)), testResult);
  }

  @Override
  @Test
  public void testBuildTree() {
    /**
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     */
    TreeNode root = qn.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    assertEquals(3, root.val);
    assertEquals(9, root.left.val);
    assertEquals(20, root.right.val);
    assertEquals(15, root.right.left.val);
    assertEquals(7, root.right.right.val);

    root = qn.buildTree(new int[]{-1}, new int[]{-1});
    assertEquals(-1, root.val);
    assertNull(root.left);
    assertNull(root.right);
  }

  @Override
  @Test
  public void testConnect() {
    // TODO: 6/29/2021 Add test case
  }

  @Override
  @Test
  public void testKthSmallest() {
    int testResult = qn.kthSmallest(constructBinaryTree(constructList(3, 1, 4, null, 2)), 1);
    assertEquals(1, testResult);

    testResult = qn
        .kthSmallest(constructBinaryTree(constructList(5, 3, 6, 2, 4, null, null, 1)), 3);
    assertEquals(3, testResult);
  }

  @Override
  @Test
  public void testNumIslands() {
    char[][] island = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
    final int noOfIsland = qn.numIslands(island);
    assertEquals(1, noOfIsland);
  }

  @Override
  @Test
  public void testLetterCombinations() {
    List<String> testResult = qn.letterCombinations("23");
    assertEquals(List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), testResult);

    testResult = qn.letterCombinations("2");
    assertEquals(List.of("a", "b", "c"), testResult);

    testResult = qn.letterCombinations("");
    assertEquals(0, testResult.size());
  }

  @Override
  @Test
  public void testGenerateParenthesis() {
    List<String> testResult = qn.generateParenthesis(3);
    assertEquals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), testResult);

    testResult = qn.generateParenthesis(1);
    assertEquals("()", testResult.get(0));

    testResult = qn.generateParenthesis(2);
    assertEquals(List.of("(())", "()()"), testResult);
  }

  @Override
  @Test
  public void testPermute() {
    /**
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    List<List<Integer>> testResult = qn.permute(new int[]{1, 2, 3});
    assertEquals(6, testResult.size());
    assertEquals(List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1),
        List.of(3, 1, 2), List.of(3, 2, 1)), testResult);

    testResult = qn.permute(new int[]{0, 1});
    assertEquals(2, testResult.size());
    assertEquals(List.of(List.of(0, 1), List.of(1, 0)), testResult);

    testResult = qn.permute(new int[]{1});
    assertEquals(List.of(List.of(1)), testResult);
  }

  @Override
  @Test
  public void testSubsets() {
    /**
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    var testResult = qn.subsets(new int[]{1, 2, 3});
    assertEquals(8, testResult.size());
    assertTrue(testResult.contains(List.of()));
    assertEquals(
        List.of(List.of(), List.of(1), List.of(1, 2), List.of(1, 2, 3), List.of(1, 3), List.of(2),
            List.of(2, 3), List.of(3)), testResult);
  }

  @Override
  @Test
  public void testExist() {
    char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    assertTrue(qn.exist(board, "ABCCED"));
    assertFalse(qn.exist(board, "ABCB"));
  }

  @Override
  @Test
  public void testSortColors() {
    int[] test = new int[]{2, 0, 2, 1, 1, 0};
    qn.sortColors(test);
    assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, test);
    assertEquals(6, test.length);
  }

  @Override
  @Test
  public void testTopKFrequent() {
    int[] result = qn.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    assertArrayEquals(new int[]{1, 2}, result);
  }

  @Override
  @Test
  public void testFindKthLargest() {
    int result = qn.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
    assertEquals(4, result);
  }

  @Override
  @Test
  public void testFindPeakElement() {
    int result = qn.findPeakElement(new int[]{1, 2, 3, 1});
    assertEquals(2, result);

    result = qn.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4});
    assertEquals(5, result);
  }

  @Override
  @Test
  public void testSearchRange() {
    int[] result = qn.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
    assertArrayEquals(new int[]{3, 4}, result);

    result = qn.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
    assertArrayEquals(new int[]{-1, -1}, result);

    result = qn.searchRange(new int[]{},0);
    assertArrayEquals(new int[]{-1,-1},result);
  }

  @Override
  @Test
  public void testIsHappy() {
    assertTrue(qn.isHappy(19));
    assertFalse(qn.isHappy(2));
  }

  @Override
  @Test
  public void testTrailingZeroes() {
    assertEquals(2, qn.trailingZeroes(10));
    assertEquals(1, qn.trailingZeroes(5));
    assertEquals(0, qn.trailingZeroes(2));
    assertEquals(6, qn.trailingZeroes(25));
  }
}
