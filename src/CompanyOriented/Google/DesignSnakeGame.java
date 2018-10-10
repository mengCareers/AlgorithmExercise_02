package CompanyOriented.Google;

import java.util.*;

/*
attribute
data structure, board is a [][]

 */
public class DesignSnakeGame {

    int foodi;
    int score;
    int width;
    int height;
    int[][] food;
    Snake snake;

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public DesignSnakeGame(int width, int height, int[][] food) {
        foodi = 0;
        score = 0;
        this.width = width;
        this.height = height;
        snake = new Snake();
        this.food = food;
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        int nx = snake.bodyDeque.peekFirst() / width;
        int ny = snake.bodyDeque.peekFirst() % width;
        switch (direction) {
            case "U":
                nx--;
                break;
            case "D":
                nx++;
                break;
            case "L":
                ny--;
                break;
            default:
                ny++;
        }
        int nh = nx * width + ny;
        snake.bodySet.remove(snake.bodyDeque.peekLast()); // remove tail from set temporarily
        if (nx < 0 || ny < 0 || nx >= height || ny >= width || snake.bodySet.contains(nh)) {
            return -1;
        }
        snake.bodySet.add(nh);
        snake.bodyDeque.addFirst(nh);
        if (foodi < food.length && food[foodi][0] == nx && food[foodi][1] == ny) {
            foodi++;
            snake.bodySet.add(snake.bodyDeque.peekLast());
            score++;
            return score;
        }
        snake.bodyDeque.pollLast();
        return score;
    }

    class Snake {
        //        int length;
//        int[] start;
//        int[] end;
        Set<Integer> bodySet;
        Deque<Integer> bodyDeque;

        public Snake() {
            bodySet = new HashSet<>();
            bodySet.add(0);
            bodyDeque = new ArrayDeque<>();
            bodyDeque.addLast(0);
        }
    }
}
