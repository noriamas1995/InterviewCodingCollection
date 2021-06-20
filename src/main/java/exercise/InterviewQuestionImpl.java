package exercise;

import java.util.*;

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
      if (i != 0 && nums[i] == nums[i - 1]) continue;
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
          while (j < k && j != i + 1 && nums[j] == nums[j - 1]) j++;
          while (j < k && k != nums.length - 1 && nums[k] == nums[k + 1]) k--;
        }
      }
    }
    return res;
  }
}
