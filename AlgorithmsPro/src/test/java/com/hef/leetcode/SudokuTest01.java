package com.hef.leetcode;

import java.util.Arrays;

/**
 * @author lifei
 * @since 2020/8/29
 */
public class SudokuTest01 {

    public static void main(String[] args) {
        char[][] oneChar = {
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        };
        SudokuTest01 sudokuTest01 = new SudokuTest01();
        boolean validSudoku = sudokuTest01.isValidSudoku(oneChar);
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
        char[] row = new char[board.length];
        // 验证每一行
        for(int rowIndex=0; rowIndex<board.length;rowIndex++){
            createOneSortedRow(board[rowIndex],row);
            boolean rowSame = rowHaveSameBySortedRow(row);
            if(rowSame){
                return false;
            }
        }
        // 验证每一列
        for(int colIndex=0; colIndex<board[0].length;colIndex++){
            createOneSortedRow(colIndex,board,row);
            boolean rowSame = rowHaveSameBySortedRow(row);
            if(rowSame){
                return false;
            }
        }
        // 验证 9 * 9 的单元格
        for(int y=0;y+2<board[0].length;y+=3){
            for(int x=0;x+2<board.length;x+=3){
                createOneSortedRow(x, y, board,row);
                boolean rowSame = rowHaveSameBySortedRow(row);
                if(rowSame){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  将一行数据，按照顺序创建数组
     */
    private char[] createOneSortedRow(char[] row,char[] oneRow){
        int oneRowLen = 0;
        for(int i=0;i<row.length;i++){
            if(row[i] != '.'){
                oneRow[oneRowLen++] = row[i];
            }
        }
        for(int j=oneRowLen;j<oneRow.length;j++){
            oneRow[j] = '.';
        }
        return oneRow;
    }

    /**
     * 将一列数组，按照顺序创建成数组
     */
    private char[] createOneSortedRow(int colIndex, char[][] board,char[] oneRow){
        int oneRowLen = 0;
        for(int i=0;i<board.length;i++){
            if(board[i][colIndex] != '.'){
                oneRow[oneRowLen++] = board[i][colIndex];
            }
        }
        for(int j=oneRowLen;j<oneRow.length;j++){
            oneRow[j] = '.';
        }
        return oneRow;
    }

    /**
     * 将一个 3*3 转变成数组
     */
    private char[] createOneSortedRow(int x,int y, char[][] board,char[] oneRow){
        int oneRowLen = 0;
        for(int i=x;i<=x+2;i++){
            for(int j=y;j<=y+2;j++){
                if (board[i][j]!='.') {
                    oneRow[oneRowLen++] = board[i][j];
                }
            }
        }
        for(int j=oneRowLen;j<oneRow.length;j++){
            oneRow[j] = '.';
        }
        System.out.println(Arrays.toString(oneRow));
        return oneRow;
    }

    /**
     *  验证一行中是否有相同数据
     */
    private boolean rowHaveSameBySortedRow(char[] row){
        for(int colIndex=0; colIndex<row.length;colIndex++){
            if (row[colIndex] == '.'){
                break;
            }
            for(int j=colIndex+1; j<row.length;j++){
                if(row[j] == '.'){
                    break;
                }
                if(row[j] == row[colIndex]){
                    return true;
                }
            }
        }
        return false;
    }

}
