package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 자동차 이름 입력
        List<String> carNames = readCarNames();
        // 시도할 횟수 입력
        int tryCount = readTryCount();

        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());

        // 게임 진행
        System.out.println("\n실행결과");
        for (int i = 0; i < tryCount; i++){
            moveAll(cars);
            printRoundResult(cars);
        }

    }

    // 자동차 이름 입력
    private static List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine().trim();
        List<String> names = Arrays.asList(input.split(","));
        validation(names);
        return names;
    }

    // 자동차 이름 유효성 검사
    private static void validation(List<String> names){
        for (String name : names) {
            // 자동차 이름 이 빈 문자열인지, 5자가 넘어가는지 검사 후 예외 처리
            if (name.isBlank() || name.length() > 5) {
                throw new IllegalArgumentException();
            }
        }
    }

    // 시도 횟수 입력
    private static int readTryCount(){
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine().trim();
        try {
            int count = Integer.parseInt(input);
            // 시도할 횟수가 1보다 작을경우
            if (count <= 0){
                throw new IllegalArgumentException();
            }
            return count;
        // 횟수가 숫자가 아닐경우
        } catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }
    }

    // 게임 로직
    private static void moveAll(List<Car> cars){
        for (Car car : cars){
            car.move();
        }
    }

    // 자동차 클래스 생성
    static class Car {
        private final String name;
        private int position = 0;

        public Car (String name){
            this.name = name;
        }

        public void move() {
            int random = Randoms.pickNumberInRange(0, 9);
            if (random >= 4){
                position++;
            }
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }

    }
}

