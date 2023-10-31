package racingcar.game.model;

import camp.nextstep.edu.missionutils.Randoms;

public class Car implements Comparable<Car> {
    static final String NAME_LENGTH_MIN_LIMIT_ERROR = "[ERROR] 자동차 이름의 길이는 1 이상여야 합니다.";
    static final String NAME_LENGTH_MAX_LIMIT_ERROR = "[ERROR] 자동차 이름의 길이는 5 이하여야 합니다.";
    static final int NAME_LENGTH_MIN_LIMIT = 1;
    static final int NAME_LENGTH_MAX_LIMIT = 5;
    static final int MOVE_FORWARD_MIN_NUMBER = 4;
    private String name;
    private int moveCount;

    public Car(String name) {
        validateName(name);
        this.name = name;
        moveCount = 0;
    }

    private void validateName(String name) {
        if (name.length() < NAME_LENGTH_MIN_LIMIT) {
            throw new IllegalArgumentException(NAME_LENGTH_MIN_LIMIT_ERROR);
        }
        if (name.length() > NAME_LENGTH_MAX_LIMIT) {
            throw new IllegalArgumentException(NAME_LENGTH_MAX_LIMIT_ERROR);
        }
    }

    protected void moveForward() {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        if (randomNumber >= MOVE_FORWARD_MIN_NUMBER) {
            moveCount++;
        }
    }

    protected void saveResult(RaceResult raceResult) {
        raceResult.append(name, moveCount);
    }

    @Override
    public int compareTo(Car otherCar) {
        return Integer.compare(otherCar.moveCount, this.moveCount);
    }

    protected WinnerData createWinnerData() {
        return new WinnerData(moveCount);
    }

    protected boolean isWinner(WinnerData winnerData) {
        return (winnerData.compareMoveCount(moveCount) == 0);
    }

    protected void addWinner(WinnerData winnerData) {
        winnerData.addWinnerName(name);
    }
}
