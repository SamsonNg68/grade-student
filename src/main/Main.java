package main;

import java.util.Scanner;

public class Main {
	private static int midTermWeight;
	private static int finalTermWeight;
	private static int homeworkWeight;
	// ham main luồng điều khiển chính của chương trình
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		begin();
		double weightMidTerm = midTerm();

		double weightFinalTerm = finalTerm();

		double weightHomework = homework();

		// tính tổng điểm hai kì và bài tập về nhà
		double totalGrade = weightMidTerm + weightFinalTerm + weightHomework;

		report(totalGrade);
	}

	// hàm hiển thị thông điệp chào mừng
	public static void begin() {
		System.out.println("This program reads exam/homework scores\nand reports your overall course grade");
	}

	// hàm nhâp và tính toán điểm thi giữa kì
	public static double midTerm() {

		// nhập thông tin điểm
		System.out.println("\nMidterm: ");
		Scanner sc = new Scanner(System.in);
		int  scoreEarned, wereScoresShifted;
		System.out.println("Weight (0-100)? ");
		midTermWeight = sc.nextInt();
		System.out.println("Score earned? ");
		scoreEarned = sc.nextInt();
		System.out.println("Were scores shifted (1 = yes, 2 = no)? ");
		wereScoresShifted = sc.nextInt();

		// kiểm tra điều kiện được tăng điểm
		if (wereScoresShifted == 1) {
			System.out.println("Shift amount? ");
			int shiftAmount = sc.nextInt();
			if ((shiftAmount + scoreEarned) > 100) {
				scoreEarned = 100;
			} else {
				scoreEarned += shiftAmount;
			}
		}

		// hiển thị thông tin điểm giữa kì
		int totalPoints = scoreEarned;
		System.out.println("Total points? " + totalPoints + " / 100");
		double weightedScoreMid = (((double) totalPoints / 100) * midTermWeight);
		System.out.println("Weighted score? " + (Math.round(weightedScoreMid * 10.0) / 10.0) + " / " + midTermWeight);

		// trả về giá trị điểm đã tính dưa trên trọng só
		return weightedScoreMid;

	}

	// hàm nhập và tính toán điêm thi cuối kì

	public static double finalTerm() {
		// nhập thông tin điểm
		System.out.println("\nFinal: ");
		Scanner sc = new Scanner(System.in);
		int  scoreEarned, wereScoresShifted;
		System.out.println("Weight (0-100)? ");
		finalTermWeight = sc.nextInt();
		if(finalTermWeight + midTermWeight >= 100) {
			finalTermWeight = sc.nextInt();
		}
		System.out.println("Score earned? ");
		scoreEarned = sc.nextInt();
		System.out.println("Were scores shifted (1 = yes, 2 = no)? ");
		wereScoresShifted = sc.nextInt();

		// kiểm tra điều kiện được tăng điểm
		if (wereScoresShifted == 1) {
			System.out.println("Shift amount? ");
			int shiftAmount = sc.nextInt();
			if ((shiftAmount + scoreEarned) > 100) {
				scoreEarned = 100;
			} else {
				scoreEarned += shiftAmount;
			}
		}

		// hiển thị thông tin điểm cuối kì

		int totalPoints = scoreEarned;

		System.out.println("Total points? " + totalPoints + " / 100");

		double weightedScoreFinal = (((double) totalPoints / 100) * finalTermWeight);

		System.out.println("Weighted score? " + (Math.round(weightedScoreFinal * 10.0) / 10.0) + " / " + finalTermWeight);

		// trả về giá trị điểm đã tính dưa trên trọng só
		return weightedScoreFinal;
	}

	// hàm nhập và tính toán điểm bài tập về nhà
	public static double homework() {
		// nhập thông tin điểm
		Scanner sc = new Scanner(System.in);
		System.out.println("\nHomework: ");
		System.out.println("Weight (0-100)? ");
		int weight = sc.nextInt();

		System.out.println("Number of assignments? ");
		int numberOfAssigns = sc.nextInt();
		sc.nextLine();

		int totalEarned = 0;
		int totalAssScore = 0;

		// sử dụng vòng lặp điểm lấy thông tin điểm bài assignment
		for (int numberOfAssign = 0; numberOfAssign < numberOfAssigns; numberOfAssign++) {
			System.out.println("Assignment " + (numberOfAssign + 1) + " score and max? ");
			String scoreAndMax = sc.nextLine();

			// Chuyển đổi String sang int, và sử dụng subtring để lấy ký tự ở vị trí index 0
			// đến 1 đầu tiên
			// tạo biến và vào giá trị
			int earned = Integer.parseInt(scoreAndMax.substring(0, 2));
			// Chuyển đổi String sang int, và sử dụng subtring để lấy ký tự ở vị trí index
			// từ 2 hết chuỗi
			// tạo biến và vào giá trị
			int total = Integer.parseInt(scoreAndMax.substring(scoreAndMax.length() - 2, scoreAndMax.length()));

			// tổng điểm các bài assignments
			totalEarned += earned;

			// tổng điểm max của các bài assignments
			totalAssScore += total;

		}

		System.out.println("How many sections did you attend?");
		int attend = sc.nextInt();
		int maxAttend = 30;

		int sectionsPoints = attend * 5;
		// Điểm tối đa của phần Attend là 30, nếu vượt quá 30 thì vẫn chỉ tính là 30.
		if (sectionsPoints > 30) {
			sectionsPoints = 30;
		}

		System.out.println("Section points = " + sectionsPoints + " / " + maxAttend);

		int maxTotalPoints = totalAssScore + maxAttend;
		// Điểm tối đa của phần Assignment là 150, nếu vượt quá thì cũng chỉ tính là 150
		// điểm.
		if (maxTotalPoints > 150) {
			maxTotalPoints = 150;
		}
		int totalPoints = totalEarned + sectionsPoints;
		// Điểm tối đa của phần Assignment là 150, nếu vượt quá thì cũng chỉ tính là 150
		// điểm.
		if (totalPoints > 150) {
			totalPoints = 150;
		}
		System.out.println("Total points = " + totalPoints + " / " + maxTotalPoints);

		double weightScoredHomework = (((double) totalPoints / maxTotalPoints) * weight);
		System.out.println("Weighted score = " + (Math.round(weightScoredHomework * 10.0) / 10.0) + " / " + weight);

		// trả về giá trị tính tổng điểm dựa trên trọng số
		return weightScoredHomework;

	}

	// hàm để tính toán về hiển thị kết quả GPA cũng như thông báo nhận xét tương
	// ứng.
	public static void report(double grade) {
		System.out.println("Overall Percentage = " + (Math.round(grade * 10.0) / 10.0));

		// dùng điều kiện để kiểm tra mức GPA
		if (grade >= 85.0) {
			System.out.println("Your grade will be at least: 3.0");
		} else if (grade >= 75.0 && grade < 85.0) {
			System.out.println("Your grade will be at least: 2.0");
		} else if (grade >= 60.0 && grade < 75.0) {
			System.out.println("Your grade will be at least: 1.0");
		} else {
			System.out.println("Your grade will be at least: 0.0");
		}
	}

}
