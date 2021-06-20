import exercise.InterviewQuestion;
import exercise.InterviewQuestionImpl;

public class RunInterviewQuestion {
  private static final InterviewQuestion qn = new InterviewQuestionImpl();

  public static void main(String[] args) {
    TestThreeSum();
  }

  private static void TestThreeSum() {
    Object o = qn.threeSum(new int[] {1,2,5,-5,3,-6,-4,5,2,2,3,5,-6});
    System.out.println(o);
  }
}
