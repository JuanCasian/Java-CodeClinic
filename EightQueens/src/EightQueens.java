import java.util.Scanner;

public class EightQueens {
    public static void main(String[] args) {
        Board board = new Board();
        int i;
        int flag;

        i = 0;
        flag = 1;
        while (flag == 1 || flag == 2) {
            if (flag == 2)
                i = 7;
            while (!(board.solved()) || flag == 2) {
                flag = 0;
                if (board.findNextPos(i) == -1) {
                    if (i == 0) {
                        System.out.println("No more solutions");
                        System.exit(-1);
                    }
                    board.board[i] = -1;
                    i--;
                } else
                    i++;
            }
            board.printBoard();
            System.out.println();
            if (askUser())
                flag = 2;
        }
    }

    public static boolean askUser() {
        String res;
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to find next solution? (yes/no)");
        res = sc.nextLine();
        if (res.equalsIgnoreCase("yes"))
            return (true);
        return (false);
    }
}
