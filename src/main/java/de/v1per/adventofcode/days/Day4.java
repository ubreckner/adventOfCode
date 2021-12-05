package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends Day<String> {
    public static void main(String[] args) {
        List<String> list = Utils.readFile("day4.txt", String.class);
        new Day4().play(list);
    }

    @Override
    void one() {
        List<Integer> drawnNumbers = Arrays.stream(list.get(0).split(",")).map(Integer::parseInt).collect(
                Collectors.toList());
        List<int[][]> boardList = readBoards();

        int[][] winnerBoard = null;
        int lastDrawnNumber = 0;

        win:
        for (Integer number : drawnNumbers) {
            for (int[][] board : boardList) {
                markBoard(board, number);
                if (checkBoard(board)) {
                    winnerBoard = board;
                    lastDrawnNumber = number;
                    //GOTO programming INC
                    break win;
                }
            }
        }
        int sum = Arrays.stream(winnerBoard).flatMapToInt(Arrays::stream).filter(i -> i >= 0).sum();

        System.out.println("4.1: " + sum * lastDrawnNumber);
    }

    @Override
    void two() {
        List<Integer> drawnNumbers = Arrays.stream(list.get(0).split(",")).map(Integer::parseInt).collect(
                Collectors.toList());
        List<int[][]> boardList = readBoards();

        int[][] loserBoard = null;
        int lastDrawnNumber = 0;

        win:
        for (Integer number : drawnNumbers) {
            Iterator<int[][]> iter = boardList.iterator();
            while (iter.hasNext()) {
                int[][] board = iter.next();
                markBoard(board, number);
                if (checkBoard(board)) {
                    iter.remove();
                    lastDrawnNumber = number;
                    if (boardList.size() == 0) {
                        loserBoard = board;
                        //GOTO programming INC
                        break win;
                    }
                }
            }
        }
        int sum = Arrays.stream(loserBoard).flatMapToInt(Arrays::stream).filter(i -> i >= 0).sum();

        System.out.println("4.1: " + sum * lastDrawnNumber);

    }

    private boolean checkBoard(int[][] board) {
        boolean completeColumn = true;
        boolean completeRow = true;
        for (int[] row : board) {
            completeRow = true;
            for (int cell : row) {
                if (cell > -1) {
                    completeRow = false;
                    break;
                }
            }
            if (completeRow) {
                break;
            }
        }
        //transpose that stuff
        for (int i = 0; i < board[0].length; i++) {
            completeColumn = true;
            for (int[] row : board) {
                if (row[i] > -1) {
                    completeColumn = false;
                    break;
                }
            }
            if (completeColumn) {
                break;
            }
        }

        return completeRow || completeColumn;
    }

    private void markBoard(int[][] board, int number) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == number) {
                    board[i][j] = -1;
                    return;
                }
            }
        }
    }

    private List<int[][]> readBoards() {
        List<int[][]> result = new ArrayList<>();
        //Skip to the boards
        int inputRow = 2;
        while (inputRow < list.size()) {
            int[][] board = new int[5][];

            for (int i = 0; i < 5; i++) {
                board[i] = Arrays.stream(list.get(inputRow + i).split(" ")).filter(s -> !s.equals("")).map(
                        String::strip).mapToInt(Integer::parseInt).toArray();
            }
            //on to the next board
            result.add(board);
            inputRow += 6;
        }
        return result;
    }
}
