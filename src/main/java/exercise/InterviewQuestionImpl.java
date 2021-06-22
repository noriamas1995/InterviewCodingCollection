package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.ListNode;

public class InterviewQuestionImpl implements InterviewQuestion {

  @Override
  public List<List<Integer>> threeSum(int[] nums) {
    final String note = """
        We can change the equation A + B + C = 0 to A = -(B + C).
        So we just have to loop for A and use a while loop with 2 pointers to find B and C,
        we can compare the target to the actual sum to see which direction we are moving.
        One important note is that we cannot use List.contains to eliminate the duplicate solutions since it's slow.
        """;
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length < 3) {
      return res;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      // skip duplicate target number
      // cannot compare for "if nums[i] == nums[i+1] continue" because possible solutions can be missed
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      final int target = -nums[i];
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        final int sum = nums[j] + nums[k];
        if (sum < target) {
          j++;
        } else if (sum > target) {
          k--;
        } else {
          List<Integer> temp = new LinkedList<>();
          temp.addAll(List.of(nums[i], nums[j], nums[k]));
          res.add(temp);
          j++;
          k--;
          // avoid duplicate solutions, move index to next unique number
          while (j < k && j != i + 1 && nums[j] == nums[j - 1]) {
            j++;
          }
          while (j < k && k != nums.length - 1 && nums[k] == nums[k + 1]) {
            k--;
          }
        }
      }
    }
    return res;
  }

  @Override
  public void setZeroes(int[][] matrix) {
    String note = """
        We can keep track of an O(MN) space boolean matrix to see which cells are 0, but it is unnecessary work.
        We only need to track the first row and column to know which row/col should we revert to 0, for e.g. 2 boolean arrays.
        If it has to be done in-place with O(1) space, then we can directly modify the original matrix by using the first row and column as a flag.
        """;
    final int row = matrix.length;
    final int col = matrix[0].length;
    boolean firstRowHasZero = false;
    boolean firstColHasZero = false;

    for (int j = 0; j < col; j++) {
      if (matrix[0][j] == 0) {
        firstRowHasZero = true;
        break;
      }
    }

    for (int i = 0; i < row; i++) {
      if (matrix[i][0] == 0) {
        firstColHasZero = true;
        break;
      }
    }

    // loop the rest of numbers so the index starts from 1 not 0
    // if 0 is found then change the flag to 0
    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // check the first row to set 0 for columns
    // noted that the index should start from 1 since we will deal with [0,0] in the end
    for (int j = 1; j < col; j++) {
      if (matrix[0][j] == 0) {
        makeColAllZero(matrix, j);
      }
    }

    // check the first column to set 0 for the rows
    for (int i = 1; i < row; i++) {
      if (matrix[i][0] == 0) {
        makeRowAllZero(matrix, i);
      }
    }

    // set 0 for first row and column
    if (firstColHasZero) {
      makeColAllZero(matrix, 0);
    }
    if (firstRowHasZero) {
      makeRowAllZero(matrix, 0);
    }
  }

  private void makeRowAllZero(int[][] matrix, int rowIndex) {
    for (int j = 0; j < matrix[0].length; j++) {
      matrix[rowIndex][j] = 0;
    }
  }

  private void makeColAllZero(int[][] matrix, int colIndex) {
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][colIndex] = 0;
    }
  }

  @Override
  public List<List<String>> groupAnagrams(String[] strs) {
    String note = """
        A very intuitive approach is to use a hashmap whereby the key is the sorted string and value is the list of strings that belong to this category.
        By using this approach, the space complexity will be O(NK) where N is the length of array and K is the length of the longest string.
        The time complexity will be O(N K logK) because sorting requires O(K log K) and we have to do it for N elements.
        """;
    if (strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new LinkedHashMap<>();

    for (int i = 0; i < strs.length; i++) {
      String str = strs[i];
      char[] curr = str.toCharArray();
      Arrays.sort(curr);
      String key = new String(curr);
      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(str);
    }
    return new ArrayList<>(map.values());
  }

  @Override
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return null;

  }
}
