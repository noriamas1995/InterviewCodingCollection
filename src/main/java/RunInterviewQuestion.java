import exercise.InterviewQuestion;
import exercise.InterviewQuestionImpl;

public class RunInterviewQuestion {
  private static final InterviewQuestion qn = new InterviewQuestionImpl();

  public static void main(String[] args) {
    TestThreeSum();
    TestSetZeroes();
    TestGroupAnagrams();
  }

  private static void TestThreeSum() {
    Object o;
    o = qn.threeSum(new int[] {1, 2, 5, -5, 3, -6, -4, 5, 2, 2, 3, 5, -6});
    System.out.println(o);
  }

  private static void TestSetZeroes() {
    int[][] matrix =
        new int[][] {{1, 0, 1, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 1}};
    qn.setZeroes(matrix);
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void TestGroupAnagrams(){
    Object o;
    o = qn.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    System.out.println(o);
  }
}
