public class Board {
    int[] board;

    public Board(){
        board = new int[8];
        for (int i = 0; i < board.length; i++) {
            board[i] = -1;
        }
    }

    public boolean solved() {
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i] == -1)
                return (false);
        }
        return (true);
    }

    public int check_horizontal(int arrPos) {
        int i;
        i = 0;
        while (i < arrPos) {
            if (this.board[i] == this.board[arrPos])
                return (0);
            i++;
        }
        return (1);
    }

    public int check_diagonals(int arrPos){
        int i;
        int diagonalVal;

        i = arrPos - 1;
        diagonalVal = this.board[arrPos];
        while (i >= 0) {
            diagonalVal--;
            if (this.board[i] == diagonalVal)
                return (0);
            i--;

        }
        i = arrPos - 1;
        diagonalVal = this.board[arrPos];
        while (i >= 0) {
            diagonalVal++;
            if (this.board[i] == diagonalVal)
                return (0);
            i--;
        }
        return (1);
    }

    public int findNextPos(int arrPos) {
        int pos;
        int solution;

        pos = this.board[arrPos] + 1;
        while (pos < 8) {
            solution = 0;
            this.board[arrPos] = pos;
            if (this.check_horizontal(arrPos) == 1)
                solution++;
            if (this.check_diagonals(arrPos) == 1)
                solution++;
            if (solution == 2)
                return (1);
            pos++;
        }
        this.board[arrPos] = -1;
        return (-1);
    }

    public void printBoard() {
        int i;
        int j;

        i = 0;
        while (i < 8) {
            j = 0;
            while (j < 8){
                if (this.board[j] == i) {
                    System.out.print("Q ");
                } else {
                    System.out.print("☐ ");
                }
                j++;
            }
            System.out.println();
            i++;
        }
    }
}
