package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String[] names = Console.readLine().split(","); // 자동차 이름 배열
        for (String name : names) {
            if (name.length() > 5) throw new IllegalArgumentException();
        }
        int[] moves = new int[names.length]; // 전진 횟수 배열

        System.out.println("시도할 회수는 몇회인가요?");
        int attempts;
        try {
            attempts = Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.println("실행결과");
        for (int i = 0; i < attempts; i++) {
            race(moves);
            result(names, moves);
            System.out.println();
        }

        System.out.print("최종 우승자 : ");
        List<String> winner = getWinner(names, moves);
        for (int i = 0; i < winner.size(); i++) {
            if (i == winner.size() - 1) {
                System.out.print(winner.get(i));
            } else {
                System.out.print(winner.get(i) + ", ");
            }
        }

    }

    // 전진 여부 결정
    private static void race(int[] moves) {
        for (int i = 0; i < moves.length; i++) {
            int randomNumber = Randoms.pickNumberInRange(0, 9);
            if (randomNumber >= 4) {
                moves[i]++;
            }
        }
    }

    // 결과 출력
    private static void result(String[] names, int[] moves) {
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " : " + "-".repeat(moves[i]));
        }
    }

    private static List<String> getWinner(String[] names, int[] moves) {
        // 최댓값 찾기
        int max = 0;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] > max) {
                max = moves[i];
            }
        }
        // 최댓값 이름 저장
        List<String> winner = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] == max) {
                winner.add(names[i]);
            }
        }

        return winner;
    }
}
