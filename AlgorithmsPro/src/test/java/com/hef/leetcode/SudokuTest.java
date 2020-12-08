package com.hef.leetcode;

import java.util.Arrays;

/**
 * @author lifei
 * @since 2020/8/29
 */
public class SudokuTest {

    public static void main(String[] args) {
        char[][] oneChar = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        SudokuTest sudokuTest = new SudokuTest();
        boolean validSudoku = sudokuTest.isValidSudoku(oneChar);
        System.out.println(validSudoku);

    }
    /**
     *
     * 思路：
     *  1、验证每一行；
     *  2、验证每一列；
     *  3、验证 3 * 3 的表格；
     *
     */
    public boolean isValidSudoku(char[][] board) {
        // 验证每一行
        for(int rowIndex=0; rowIndex<board.length;rowIndex++){
            char[] row = board[rowIndex];
            boolean rowSame = rowHaveSame(row);
            if(rowSame){
                return false;
            }
        }
        // 验证每一列
        for(int colIndex=0; colIndex<board[0].length;colIndex++){
            boolean colSame = colHaveSame(colIndex,board);
            if(colSame){
                return false;
            }
        }
        // 验证 9 * 9 的单元格
        for(int y=0;y+2<board[0].length;y+=3){
            for(int x=0;x+2<board.length;x+=3){
                char[] row = new char[9];
                int s=0;
                for(int i=x;i<=x+2;i++){
                    for(int j=y;j<=y+2;j++){
                        row[s++] = board[i][j];
                    }
                }
                boolean rowSame = rowHaveSame(row);
                if(rowSame){
                    System.out.println(String.format("x=%s , y=%s", x, y));
                    System.out.println(Arrays.toString(row));
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证一列数值
     */
    private boolean colHaveSame(int colIndex, char[][] board){
        for(int rowIndex=0; rowIndex<board.length;rowIndex++){
            if (board[rowIndex][colIndex] == '.'){
                continue;
            }
            for(int j=rowIndex+1; j<board.length;j++){
                if(board[j][colIndex] == '.'){
                    continue;
                }
                if(board[j][colIndex] == board[rowIndex][colIndex]){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  验证一行中是否有相同数据
     */
    private boolean rowHaveSame(char[] row){
        for(int colIndex=0; colIndex<row.length;colIndex++){
            if (row[colIndex] == '.'){
                continue;
            }
            for(int j=colIndex+1; j<row.length;j++){
                if(row[j] == '.'){
                    continue;
                }
                if(row[j] == row[colIndex]){
                    return true;
                }
            }
        }
        return false;
    }
}
