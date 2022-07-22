package com.example.realchess;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// used to store the current state of the game.
public class Chess {

    boolean black_turn = true; // True = Black Can Move
    // False = White Can Move

    public boolean isSelect = false; // True if player select chess piece
    // False otherwise

    public boolean isCheck = false;

    public ImageView[][] board = new ImageView[8][8];

    public String previousPieceType = "";
    public Integer previousIsSelectI = -1, previousIsSelectJ = -1;

    public List<Pair<Integer, Integer>> validMoves = new ArrayList<>();
    public List<Pair<Integer, Integer>> attackMoves = new ArrayList<>();

    List<Pair<Integer, Integer>> black_pawn = new ArrayList<>();
    List<Pair<Integer, Integer>> black_rook = new ArrayList<>();
    List<Pair<Integer, Integer>> black_knight = new ArrayList<>();
    List<Pair<Integer, Integer>> black_bishop = new ArrayList<>();

    List<Pair<Integer, Integer>> white_pawn = new ArrayList<>();
    List<Pair<Integer, Integer>>white_rook = new ArrayList<>();
    List<Pair<Integer, Integer>>white_knight = new ArrayList<>();
    List<Pair<Integer, Integer>>white_bishop = new ArrayList<>();


    List<Pair<Integer, Integer>> valid_pawn_moves = new ArrayList<>();
    List<Pair<Integer, Integer>> valid_pawn_attack_moves  = new ArrayList<>();

    List<Pair<Integer, Integer>> valid_knight_moves = new ArrayList<>();
    List<Pair<Integer, Integer>> valid_knight_attack_moves  = new ArrayList<>();

    List<Pair<Integer, Integer>> valid_rook_moves = new ArrayList<>();
    List<Pair<Integer, Integer>> valid_rook_attack_moves  = new ArrayList<>();

    List<Pair<Integer, Integer>> valid_bishop_moves = new ArrayList<>();
    List<Pair<Integer, Integer>> valid_bishop_attack_moves  = new ArrayList<>();

    List<Pair<Integer, Integer>> valid_queen_moves = new ArrayList<>();
    List<Pair<Integer, Integer>> valid_queen_attack_moves  = new ArrayList<>();

    List<Pair<Integer, Integer>> valid_king_moves = new ArrayList<>();
    List<Pair<Integer, Integer>> valid_king_attack_moves  = new ArrayList<>();

    Pair<Integer, Integer> black_king, black_queen;
    Pair<Integer, Integer> white_king, white_queen;


    // used to check move if game on check state
    List<Pair<String, Pair<Integer, Integer>>> moves = new ArrayList<>();

    // called initially to reset all variables
    public void play() {
        isCheck = false;
        black_turn = true;
        black_pawn = new ArrayList<>();
        black_rook = new ArrayList<>();
        black_knight = new ArrayList<>();
        black_bishop = new ArrayList<>();

        white_pawn = new ArrayList<>();
        white_rook = new ArrayList<>();
        white_knight = new ArrayList<>();
        white_bishop = new ArrayList<>();

        // set pawn
        for (int i = 0; i < 8; i++) {
            black_pawn.add(new Pair<>(1, i));
            white_pawn.add(new Pair<>(6, i));
        }

        // set rook
        black_rook.add(new Pair<>(0, 0));
        black_rook.add(new Pair<>(0, 7));

        white_rook.add(new Pair<>(7, 0));
        white_rook.add(new Pair<>(7, 7));

        // set knight
        black_knight.add(new Pair<>(0, 1));
        black_knight.add(new Pair<>(0, 6));

        white_knight.add(new Pair<>(7, 1));
        white_knight.add(new Pair<>(7, 6));

        // set bishop
        black_bishop.add(new Pair<>(0, 2));
        black_bishop.add(new Pair<>(0, 5));

        white_bishop.add(new Pair<>(7, 2));
        white_bishop.add(new Pair<>(7, 5));

        // set queen
        black_queen = new Pair<>(0, 3);

        white_queen = new Pair<>(7, 3);

        // set king
        black_king = new Pair<>(0, 4);

        white_king = new Pair<>(7, 4);

        display();
    }

    // called to display the updated piece of chess
    public void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setImageResource(android.R.color.transparent);
            }
        }

        // display black pawn on the chess board
        for (Pair<Integer, Integer> p : black_pawn) {
            board[p.first][p.second].setImageResource(R.drawable.bp);
            board[p.first][p.second].setTag(R.drawable.bp);
        }

        // display white pawn on the chess board
        for (Pair<Integer, Integer> p : white_pawn) {
            board[p.first][p.second].setImageResource(R.drawable.wp);
            board[p.first][p.second].setTag(R.drawable.wp);
        }

        // display black rook on the chess board
        for (Pair<Integer, Integer> p : black_rook) {
            board[p.first][p.second].setImageResource(R.drawable.br);
            board[p.first][p.second].setTag(R.drawable.br);
        }

        // display white white on the chess board
        for (Pair<Integer, Integer> p : white_rook) {
            board[p.first][p.second].setImageResource(R.drawable.wr);
            board[p.first][p.second].setTag(R.drawable.wr);
        }

        // display black knight on the chess board
        for (Pair<Integer, Integer> p : black_knight) {
            board[p.first][p.second].setImageResource(R.drawable.bk);
            board[p.first][p.second].setTag(R.drawable.bk);
        }

        // display white knight on the chess board
        for (Pair<Integer, Integer> p : white_knight) {
            board[p.first][p.second].setImageResource(R.drawable.wk);
            board[p.first][p.second].setTag(R.drawable.wk);
        }

        // display black bishop on the chess board
        for (Pair<Integer, Integer> p : black_bishop) {
            board[p.first][p.second].setImageResource(R.drawable.bb);
            board[p.first][p.second].setTag(R.drawable.bb);
        }

        // display white white on the chess board
        for (Pair<Integer, Integer> p : white_bishop) {
            board[p.first][p.second].setImageResource(R.drawable.wb);
            board[p.first][p.second].setTag(R.drawable.wb);
        }

        // display black queen on the chess board
        if (black_queen.first != -1 || black_queen.second != -1) {
            board[black_queen.first][black_queen.second].setImageResource(R.drawable.bq);
            board[black_queen.first][black_queen.second].setTag(R.drawable.bq);
        }

        // display white queen on the chess board
        if (white_queen.first != -1 || white_queen.second != -1) {
            board[white_queen.first][white_queen.second].setImageResource(R.drawable.wq);
            board[white_queen.first][white_queen.second].setTag(R.drawable.wq);
        }

        // display black king on the chess board
        if (black_king.first != -1 || black_king.second != -1) {
            board[black_king.first][black_king.second].setImageResource(R.drawable.bk);
            board[black_king.first][black_king.second].setTag(R.drawable.bk);
        }

        // display white king on the chess board
        if (white_king.first != -1 || white_king.second != -1) {
            board[white_king.first][white_king.second].setImageResource(R.drawable.wk);
            board[white_king.first][white_king.second].setTag(R.drawable.wk);
        }

    }

    public void remove(Integer first, Integer second) {

        Log.d("important", "hello" + first + second);
        int index = 0;
        for (Pair<Integer, Integer> p : black_pawn) {
            // Log.d("important","remove  " + p.first + " " + p.second);
            if (p.first.equals(first) && p.second.equals(second)) {
                // Log.d("important","remove black " + index);
                black_pawn.remove(index);
                // Log.d("important", black_pawn.toString());
                return;
            }
            index++;
        }
//        for (Pair<Integer, Integer> p : black_pawn) {
//
//        }


        index = 0;
        for (Pair<Integer, Integer> p : white_pawn) {
            if (p.first.equals(first) && p.second.equals(second)) {
                white_pawn.remove(index);
                return;
            }
            index++;
        }

        index = 0;
        for (Pair<Integer, Integer> p : black_rook) {
            if (p.first.equals(first) && p.second.equals(second)) {
                black_rook.remove(index);
                return;
            }
            index++;
        }

        index = 0;
        for (Pair<Integer, Integer> p : white_rook) {
            if (p.first.equals(first) && p.second.equals(second)) {
                white_rook.remove(index);
                return;
            }
            index++;
        }

        index = 0;
        for (Pair<Integer, Integer> p : black_knight) {
            if (p.first.equals(first) && p.second.equals(second)) {
                black_knight.remove(index);
                return;
            }
            index++;
        }

        index = 0;
        for (Pair<Integer, Integer> p : white_knight) {
            if (p.first.equals(first) && p.second.equals(second)) {
                white_knight.remove(index);
                return;
            }
            index++;
        }

        index = 0;
        for (Pair<Integer, Integer> p : black_bishop) {
            if (p.first.equals(first) && p.second.equals(second)) {
                black_bishop.remove(index);
                return;
            }
            index++;
        }

        index = 0;
        for (Pair<Integer, Integer> p : white_bishop) {
            if (p.first.equals(first) && p.second.equals(second)) {
                white_bishop.remove(index);
                return;
            }
            index++;
        }

        if (white_queen.first.equals(first) && white_queen.second.equals(second)) {
            white_queen = new Pair<>(-1, -1);
        }

        if (black_queen.first.equals(first) && black_queen.second.equals(second)) {
            black_queen = new Pair<>(-1, -1);
        }

        if (white_king.first.equals(first) && white_king.second.equals(second)) {
            white_king = new Pair<>(-1, -1);
        }

        if (black_king.first.equals(first) && black_king.second.equals(second)) {
            black_king = new Pair<>(-1, -1);
        }

//        // display black queen on the chess board
//        board[black_queen.first][black_queen.second].setImageResource(R.drawable.queen_black);
//
//        // display white queen on the chess board
//        board[white_queen.first][white_queen.second].setImageResource(R.drawable.queen_white);
//
//        // display black king on the chess board
//        board[black_king.first][black_king.second].setImageResource(R.drawable.king_black);
//
//        // display white king on the chess board
//        board[white_king.first][white_king.second].setImageResource(R.drawable.king_white);

    }

    // return True if position is empty exist at that position otherwise false.
    public boolean check(Integer first, Integer second) {

        boolean result = false;

        for (Pair<Integer, Integer> p : black_pawn) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }


        for (Pair<Integer, Integer> p : white_pawn) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        for (Pair<Integer, Integer> p : black_rook) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        for (Pair<Integer, Integer> p : white_rook) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        for (Pair<Integer, Integer> p : black_knight) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        for (Pair<Integer, Integer> p : white_knight) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        for (Pair<Integer, Integer> p : black_bishop) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        for (Pair<Integer, Integer> p : white_bishop) {
            if (p.first.equals(first) && p.second.equals(second)) {
                result = true;
                break;
            }
        }

        if (black_queen.first.equals(first) && black_queen.second.equals(second)) {
            result = true;
        }

        if (white_queen.first.equals(first) && white_queen.second.equals(second)) {
            result = true;
        }

        if (black_king.first != -1 && black_king.second != -1) {
            if (black_king.first.equals(first) && black_king.second.equals(second)) {
                result = true;
            }
        }

        if (white_king.first != -1 && white_king.second != -1) {
            if (white_king.first.equals(first) && white_king.second.equals(second)) {
                result = true;
            }
        }

        return !result;
    }

    public String detect(Integer first, Integer second) {

        // detect black pawn on the chess board
        for (Pair<Integer, Integer> p : black_pawn) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "BLACK_PAWN";
            }
        }

        // detect white pawn on the chess board
        for (Pair<Integer, Integer> p : white_pawn) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "WHITE_PAWN";
            }
        }

        // detect black rook on the chess board
        for (Pair<Integer, Integer> p : black_rook) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "BLACK_ROOK";
            }
        }

        // detect white white on the chess board
        for (Pair<Integer, Integer> p : white_rook) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "WHITE_ROOK";
            }
        }

        // detect black knight on the chess board
        for (Pair<Integer, Integer> p : black_knight) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "BLACK_KNIGHT";
            }
        }

        // detect white knight on the chess board
        for (Pair<Integer, Integer> p : white_knight) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "WHITE_KNIGHT";
            }
        }

        // detect black bishop on the chess board
        for (Pair<Integer, Integer> p : black_bishop) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "BLACK_BISHOP";
            }
        }

        // detect white white on the chess board
        for (Pair<Integer, Integer> p : white_bishop) {
            if (p.first.equals(first) && p.second.equals(second)) {
                return "WHITE_BISHOP";
            }
        }

        // detect black queen on the chess board
        if (black_queen.first.equals(first) && black_queen.second.equals(second)) {
            return "BLACK_QUEEN";
        }

        // detect white queen on the chess board
        if (white_queen.first.equals(first) && white_queen.second.equals(second)) {
            return "WHITE_QUEEN";
        }

        // detect black king on the chess board
        if (black_king.first.equals(first) && black_king.second.equals(second)) {
            return "BLACK_KING";
        }

        // detect white king on the chess board
        if (white_king.first.equals(first) && white_king.second.equals(second)) {
            return "WHITE_KING";
        }

        return "EMPTY_STATE";
    }

    public boolean validPosition(Integer first, Integer second) {
        boolean result = false;

        if (first < 8 && first >= 0 && second < 8 && second >= 0) {
            result = true;
        }

        return  result;
    }

    public List<Pair<Integer, Integer>> findValidPawnMoves(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // valid moves
        if (first == 6 && type.equals("WHITE")) {
            if (check(5, second)) {
                validMoves.add(new Pair<>(5, second));
                if (check(4, second)) {
                    validMoves.add(new Pair<>(4, second));
                }
            }

        } else if (first == 1 && type.equals("BLACK")) {
            if (check(2, second)) {
                validMoves.add(new Pair<>(2, second));
                if (check(3, second)) {
                    validMoves.add(new Pair<>(3, second));
                }
            }
        } else if (type.equals("BLACK") && first + 1 < 8 ){
            if (check(first + 1, second)) {
                validMoves.add(new Pair<>(first + 1, second));
            }
        } else if (type.equals("WHITE") && first - 1 >= 0) {
            if (check(first - 1, second)) {
                validMoves.add(new Pair<>(first - 1, second));
            }
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidPawnAttackMove(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // valid attack moves
        if (type.equals("WHITE") && !check(first - 1, second - 1)) {
            Log.d("important","move");
            String Type = detect(first - 1, second - 1);
            String[] arrOfStr = Type.split("_", 2);
            if (arrOfStr[0].equals("BLACK")) {
                validMoves.add(new Pair<>(first - 1, second - 1));
            }
        }

        if (type.equals("WHITE") && !check(first - 1, second + 1)) {
            String Type = detect(first - 1, second + 1);
            String[] arrOfStr = Type.split("_", 2);
            if (arrOfStr[0].equals("BLACK")) {
                validMoves.add(new Pair<>(first - 1, second + 1));
            }
        }

        if (type.equals("BLACK") && !check(first + 1, second - 1)) {
            Log.d("important","move");
            String Type = detect(first + 1, second - 1);
            String[] arrOfStr = Type.split("_", 2);
            if (arrOfStr[0].equals("WHITE")) {
                validMoves.add(new Pair<>(first + 1, second - 1));
            }
        }

        if (type.equals("BLACK") && !check(first + 1, second + 1)) {
            String Type = detect(first + 1, second + 1);
            String[] arrOfStr = Type.split("_", 2);
            if (arrOfStr[0].equals("WHITE")) {
                validMoves.add(new Pair<>(first + 1, second + 1));
            }
        }

        return  validMoves;
    }

    public List<Pair<Integer, Integer>> findValidKnightMoves(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        if (validPosition(first + 2, second - 1) && check(first + 2, second - 1)) {
            validMoves.add(new Pair<>(first + 2, second - 1));
        }

        if (validPosition(first + 2, second + 1) && check(first + 2, second + 1)) {
            validMoves.add(new Pair<>(first + 2, second + 1));
        }

        if (validPosition(first - 2, second - 1) && check(first - 2, second - 1)) {
            validMoves.add(new Pair<>(first - 2, second - 1));
        }

        if (validPosition(first - 2, second + 1) && check(first - 2, second + 1)) {
            validMoves.add(new Pair<>(first - 2, second + 1));
        }

        if (validPosition(first + 1, second + 2) && check(first + 1, second + 2)) {
            validMoves.add(new Pair<>(first + 1, second + 2));
        }

        if (validPosition(first - 1, second + 2) && check(first - 1, second + 2)) {
            validMoves.add(new Pair<>(first - 1, second + 2));
        }

        if (validPosition(first - 1, second - 2) && check(first - 1, second - 2)) {
            validMoves.add(new Pair<>(first - 1, second - 2));
        }

        if (validPosition(first + 1, second - 2) && check(first + 1, second - 2)) {
            validMoves.add(new Pair<>(first + 1, second - 2));
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidKnightAttackMoves(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        if (validPosition(first + 2, second - 1) && !check(first + 2, second - 1)) {
            String Type = detect(first + 2, second - 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first + 2, second - 1));
            }

        }

        if (validPosition(first + 2, second + 1)  && !check(first + 2, second + 1)) {
            String Type = detect(first + 2, second + 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first + 2, second + 1));
            }
        }

        if (validPosition(first - 2, second - 1)  && !check(first - 2, second - 1)) {
            String Type = detect(first - 2, second - 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first - 2, second - 1));
            }
        }

        if (validPosition(first - 2, second + 1)  && !check(first - 2, second + 1)) {
            String Type = detect(first - 2, second + 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first - 2, second + 1));
            }
        }

        if (validPosition(first + 1, second + 2)  && !check(first + 1, second + 2)) {
            String Type = detect(first + 1, second + 2);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first + 1, second + 2));
            }
        }

        if (validPosition(first - 1, second + 2)  && !check(first - 1, second + 2)) {
            String Type = detect(first - 1, second + 2);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first - 1, second + 2));
            }
        }

        if (validPosition(first - 1, second - 2)  && !check(first - 1, second - 2)) {
            String Type = detect(first - 1, second - 2);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first - 1, second - 2));
            }
        }

        if (validPosition(first + 1, second - 2)  && !check(first + 1, second - 2)) {
            String Type = detect(first + 1, second - 2);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type)) {
                validMoves.add(new Pair<>(first + 1, second - 2));
            }
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidBishopMove(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        int posX = first + 1;
        int posY= second + 1;
        while (check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posX++;
            posY++;
        }

        posX = first + 1;
        posY  = second - 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posX++;
            posY--;
        }

        posX = first - 1;
        posY  = second - 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posX--;
            posY--;
        }

        posX = first - 1;
        posY  = second + 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posX--;
            posY++;
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidBishopAttackMove(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        int posX = first + 1;
        int posY= second + 1;
        while (check(posX, posY) && validPosition(posX, posY)) {
            // validMoves.add(new Pair<>(posX, posY));
            posX++;
            posY++;
        }

        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        posX = first + 1;
        posY  = second - 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            posX++;
            posY--;
        }

        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        posX = first - 1;
        posY  = second - 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            posX--;
            posY--;
        }

        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        posX = first - 1;
        posY  = second + 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            posX--;
            posY++;
        }

        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidRookMove(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        int posX = first + 1;
        int posY = second;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posX++;
        }

        posX = first - 1;
        posY = second;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posX--;
        }

        posX = first;
        posY = second - 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posY--;
        }

        posX = first;
        posY = second + 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            validMoves.add(new Pair<>(posX, posY));
            posY++;
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidRookAttackMove(Integer first, Integer second, String type) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        int posX = first + 1;
        int posY = second;
        while(check(posX, posY) && validPosition(posX, posY)) {
            // validMoves.add(new Pair<>(posX, posY));
            posX++;
        }
        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        posX = first - 1;
        posY = second;
        while(check(posX, posY) && validPosition(posX, posY)) {
            // validMoves.add(new Pair<>(posX, posY));
            posX--;
        }
        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        posX = first;
        posY = second - 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            // validMoves.add(new Pair<>(posX, posY));
            posY--;
        }
        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        posX = first;
        posY = second + 1;
        while(check(posX, posY) && validPosition(posX, posY)) {
            // validMoves.add(new Pair<>(posX, posY));
            posY++;
        }
        if (validPosition(posX, posY)) {
            String Type = detect(posX, posY);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(posX, posY));
            }
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidQueenMove(Integer first, Integer second, String type) {
        List <Pair<Integer, Integer>> validMoves = new ArrayList<>();
        List <Pair<Integer, Integer>> validMovesRook = new ArrayList<>();

        validMoves = findValidBishopMove(first, second, type);
        validMovesRook = findValidRookMove(first, second, type);

        validMoves.addAll(validMovesRook);

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidQueenAttackMove(Integer first, Integer second, String type) {
        List <Pair<Integer, Integer>> validMoves = new ArrayList<>();
        List <Pair<Integer, Integer>> validMovesRook = new ArrayList<>();

        validMoves = findValidBishopAttackMove(first, second, type);
        validMovesRook = findValidRookAttackMove(first, second, type);

        validMoves.addAll(validMovesRook);

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidKingMove(Integer first, Integer second, String type) {
        List <Pair<Integer, Integer>> validMoves = new ArrayList<>();

        if(check(first, second + 1) && validPosition(first, second + 1)) {
            validMoves.add(new Pair<>(first, second + 1));
        }

        if(check(first, second - 1) && validPosition(first, second - 1)) {
            validMoves.add(new Pair<>(first, second - 1));
        }

        if(check(first + 1, second) && validPosition(first + 1, second)) {
            validMoves.add(new Pair<>(first + 1, second));
        }

        if(check(first + 1, second + 1) && validPosition(first + 1, second + 1)) {
            validMoves.add(new Pair<>(first + 1, second + 1));
        }

        if(check(first + 1, second - 1) && validPosition(first + 1, second - 1)) {
            validMoves.add(new Pair<>(first + 1, second - 1));
        }

        if(check(first - 1, second) && validPosition(first - 1, second)) {
            validMoves.add(new Pair<>(first - 1, second));
        }

        if(check(first - 1, second - 1) && validPosition(first - 1, second - 1)) {
            validMoves.add(new Pair<>(first - 1, second - 1));
        }

        if(check(first - 1, second + 1) && validPosition(first - 1, second + 1)) {
            validMoves.add(new Pair<>(first - 1, second + 1));
        }

        return validMoves;
    }

    public List<Pair<Integer, Integer>> findValidKingAttackMove(Integer first, Integer second, String type) {
        List <Pair<Integer, Integer>> validMoves = new ArrayList<>();


        if(!check(first, second + 1) && validPosition(first, second + 1)) {
            String Type = detect(first, second + 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first, second + 1));
            }
        }

        if(!check(first, second - 1) && validPosition(first, second - 1)) {
            String Type = detect(first, second - 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first, second - 1));
            }
        }

        if(!check(first + 1, second) && validPosition(first + 1, second)) {
            String Type = detect(first + 1, second);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first + 1, second));
            }
        }

        if(!check(first + 1, second + 1) && validPosition(first + 1, second + 1)) {
            String Type = detect(first + 1, second + 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first + 1, second + 1));
            }
        }

        if(!check(first + 1, second - 1) && validPosition(first + 1, second - 1)) {
            String Type = detect(first + 1, second - 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first + 1, second - 1));
            }
        }

        if(!check(first - 1, second) && validPosition(first - 1, second)) {
            String Type = detect(first - 1, second);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first - 1, second));
            }
        }

        if(!check(first - 1, second - 1) && validPosition(first - 1, second - 1)) {
            String Type = detect(first - 1, second - 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first - 1, second - 1));
            }
        }

        if(!check(first - 1, second + 1) && validPosition(first - 1, second + 1)) {
            String Type = detect(first - 1, second + 1);
            String[] arrOfStr = Type.split("_", 2);

            if (!arrOfStr[0].equals(type) && !type.equals("EMPTY")) {
                validMoves.add(new Pair<>(first - 1, second + 1));
            }
        }

        return validMoves;
    }


    public boolean find(List<Pair<Integer, Integer>> attackMoves, Integer first, Integer second) {
        boolean res = false;
        for (Pair<Integer, Integer> p : attackMoves) {
            if (p.first.equals(first) && p.second.equals(second)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public List<Pair<String, Pair<Integer, Integer>>> detectAllPossibleMoveAfterCheck() {
        List<Pair<String, Pair<Integer, Integer>>> validMoves = new ArrayList<>();

        List<Pair<Integer, Integer>> possibleMove, possibleAttackMove;

        int index;
        if (this.black_turn) {
            index = 0;
            List<Pair<Integer, Integer>> tmpMove = black_pawn;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidPawnMoves(p.first, p.second, "BLACK");
                possibleAttackMove = findValidPawnAttackMove(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = black_pawn.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    black_pawn.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_pawn_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("WHITE_QUEEN")) {
                        cp = white_queen;
                        white_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("WHITE_PAWN")) {
                        for (int i = 0; i < white_pawn.size(); i++) {
                            if (white_pawn.get(i).first.equals(p1.first) &&
                                    white_pawn.get(i).second.equals(p1.second)) {
                                white_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        for (int i = 0; i < white_knight.size(); i++) {
                            if (white_knight.get(i).first.equals(p1.first) &&
                                    white_knight.get(i).second.equals(p1.second)) {
                                white_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        for (int i = 0; i < white_bishop.size(); i++) {
                            if (white_bishop.get(i).first.equals(p1.first) &&
                                    white_bishop.get(i).second.equals(p1.second)) {
                                white_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_ROOK")) {
                        for (int i = 0; i < white_rook.size(); i++) {
                            if (white_rook.get(i).first.equals(p1.first) &&
                                    white_rook.get(i).second.equals(p1.second)) {
                                white_rook.remove(i);
                            }
                        }
                    }

                    black_pawn.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_pawn_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("WHITE_QUEEN")) {
                        white_queen = cp;
                    }

                    if (type.equals("WHITE_PAWN")) {
                        white_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        white_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        white_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_ROOK")) {
                        white_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                black_pawn.set(index, tmp);
                index++;
            }

            index = 0;
            tmpMove = black_knight;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidKnightMoves(p.first, p.second, "BLACK");
                possibleAttackMove = findValidKnightAttackMoves(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = black_knight.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    black_knight.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_knight_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("WHITE_QUEEN")) {
                        cp = white_queen;
                        white_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("WHITE_PAWN")) {
                        for (int i = 0; i < white_pawn.size(); i++) {
                            if (white_pawn.get(i).first.equals(p1.first) &&
                                    white_pawn.get(i).second.equals(p1.second)) {
                                white_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        for (int i = 0; i < white_knight.size(); i++) {
                            if (white_knight.get(i).first.equals(p1.first) &&
                                    white_knight.get(i).second.equals(p1.second)) {
                                white_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        for (int i = 0; i < white_bishop.size(); i++) {
                            if (white_bishop.get(i).first.equals(p1.first) &&
                                    white_bishop.get(i).second.equals(p1.second)) {
                                white_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_ROOK")) {
                        for (int i = 0; i < white_rook.size(); i++) {
                            if (white_rook.get(i).first.equals(p1.first) &&
                                    white_rook.get(i).second.equals(p1.second)) {
                                white_rook.remove(i);
                            }
                        }
                    }

                    black_knight.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_knight_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("WHITE_QUEEN")) {
                        white_queen = cp;
                    }

                    if (type.equals("WHITE_PAWN")) {
                        white_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        white_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        white_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_ROOK")) {
                        white_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                black_knight.set(index, tmp);
                index++;
            }

            index = 0;
            tmpMove = black_rook;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidRookMove(p.first, p.second, "BLACK");
                possibleAttackMove = findValidRookAttackMove(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = black_rook.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    black_rook.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_rook_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("WHITE_QUEEN")) {
                        cp = white_queen;
                        white_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("WHITE_PAWN")) {
                        for (int i = 0; i < white_pawn.size(); i++) {
                            if (white_pawn.get(i).first.equals(p1.first) &&
                                    white_pawn.get(i).second.equals(p1.second)) {
                                white_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        for (int i = 0; i < white_knight.size(); i++) {
                            if (white_knight.get(i).first.equals(p1.first) &&
                                    white_knight.get(i).second.equals(p1.second)) {
                                white_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        for (int i = 0; i < white_bishop.size(); i++) {
                            if (white_bishop.get(i).first.equals(p1.first) &&
                                    white_bishop.get(i).second.equals(p1.second)) {
                                white_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_ROOK")) {
                        for (int i = 0; i < white_rook.size(); i++) {
                            if (white_rook.get(i).first.equals(p1.first) &&
                                    white_rook.get(i).second.equals(p1.second)) {
                                white_rook.remove(i);
                            }
                        }
                    }

                    black_rook.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_rook_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("WHITE_QUEEN")) {
                        white_queen = cp;
                    }

                    if (type.equals("WHITE_PAWN")) {
                        white_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        white_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        white_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_ROOK")) {
                        white_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                black_rook.set(index, tmp);
                index++;
            }

            index = 0;
            tmpMove = black_bishop;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidBishopMove(p.first, p.second, "BLACK");
                possibleAttackMove = findValidBishopAttackMove(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = black_bishop.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    black_bishop.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_bishop_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("WHITE_QUEEN")) {
                        cp = white_queen;
                        white_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("WHITE_PAWN")) {
                        for (int i = 0; i < white_pawn.size(); i++) {
                            if (white_pawn.get(i).first.equals(p1.first) &&
                                    white_pawn.get(i).second.equals(p1.second)) {
                                white_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        for (int i = 0; i < white_knight.size(); i++) {
                            if (white_knight.get(i).first.equals(p1.first) &&
                                    white_knight.get(i).second.equals(p1.second)) {
                                white_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        for (int i = 0; i < white_bishop.size(); i++) {
                            if (white_bishop.get(i).first.equals(p1.first) &&
                                    white_bishop.get(i).second.equals(p1.second)) {
                                white_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("WHITE_ROOK")) {
                        for (int i = 0; i < white_rook.size(); i++) {
                            if (white_rook.get(i).first.equals(p1.first) &&
                                    white_rook.get(i).second.equals(p1.second)) {
                                white_rook.remove(i);
                            }
                        }
                    }

                    black_bishop.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_bishop_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("WHITE_QUEEN")) {
                        white_queen = cp;
                    }

                    if (type.equals("WHITE_PAWN")) {
                        white_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_BISHOP")) {
                        white_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_KNIGHT")) {
                        white_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("WHITE_ROOK")) {
                        white_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                black_bishop.set(index, tmp);
                index++;
            }

            possibleMove = findValidQueenMove(black_queen.first, black_queen.second, "BLACK");
            possibleAttackMove = findValidQueenAttackMove(black_queen.first, black_queen.second, "BLACK");

            Pair<Integer, Integer> tmp = black_queen;

            // Log.d("important", "possible moves" + possibleMove.toString());
            for (Pair<Integer, Integer> p1 : possibleMove) {
                black_queen =  new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_queen_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }
            }

            for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                String type = detect(p1.first, p1.second);
                Pair<Integer, Integer> cp = new Pair<>(0, 0);

                if (type.equals("WHITE_QUEEN")) {
                    cp = white_queen;
                    white_queen = new Pair<>(-1, -1);
                }

                if (type.equals("WHITE_PAWN")) {
                    for (int i = 0; i < white_pawn.size(); i++) {
                        if (white_pawn.get(i).first.equals(p1.first) &&
                                white_pawn.get(i).second.equals(p1.second)) {
                            white_pawn.remove(i);
                        }
                    }

                }

                if (type.equals("WHITE_KNIGHT")) {
                    for (int i = 0; i < white_knight.size(); i++) {
                        if (white_knight.get(i).first.equals(p1.first) &&
                                white_knight.get(i).second.equals(p1.second)) {
                            white_knight.remove(i);
                        }
                    }
                }

                if (type.equals("WHITE_BISHOP")) {
                    for (int i = 0; i < white_bishop.size(); i++) {
                        if (white_bishop.get(i).first.equals(p1.first) &&
                                white_bishop.get(i).second.equals(p1.second)) {
                            white_bishop.remove(i);
                        }
                    }
                }

                if (type.equals("WHITE_ROOK")) {
                    for (int i = 0; i < white_rook.size(); i++) {
                        if (white_rook.get(i).first.equals(p1.first) &&
                                white_rook.get(i).second.equals(p1.second)) {
                            white_rook.remove(i);
                        }
                    }
                }

                black_queen = new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_queen_attack_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }

                if (type.equals("WHITE_QUEEN")) {
                    white_queen = cp;
                }

                if (type.equals("WHITE_PAWN")) {
                    white_pawn.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("WHITE_BISHOP")) {
                    white_bishop.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("WHITE_KNIGHT")) {
                    white_knight.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("WHITE_ROOK")) {
                    white_rook.add(new Pair<>(p1.first, p1.second));
                }
            }

            black_queen = tmp;

            possibleMove = findValidKingMove(black_king.first, black_king.second, "BLACK");
            possibleAttackMove = findValidKingAttackMove(black_king.first, black_king.second, "BLACK");

            tmp = black_king;

            // Log.d("important", "possible moves" + possibleMove.toString());
            for (Pair<Integer, Integer> p1 : possibleMove) {

                black_king =  new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_king_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }
            }

            for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                String type = detect(p1.first, p1.second);
                Pair<Integer, Integer> cp = new Pair<>(0, 0);

                if (type.equals("WHITE_QUEEN")) {
                    cp = white_queen;
                    white_queen = new Pair<>(-1, -1);
                }

                if (type.equals("WHITE_PAWN")) {
                    for (int i = 0; i < white_pawn.size(); i++) {
                        if (white_pawn.get(i).first.equals(p1.first) &&
                                white_pawn.get(i).second.equals(p1.second)) {
                            white_pawn.remove(i);
                        }
                    }

                }

                if (type.equals("WHITE_KNIGHT")) {
                    for (int i = 0; i < white_knight.size(); i++) {
                        if (white_knight.get(i).first.equals(p1.first) &&
                                white_knight.get(i).second.equals(p1.second)) {
                            white_knight.remove(i);
                        }
                    }
                }

                if (type.equals("WHITE_BISHOP")) {
                    for (int i = 0; i < white_bishop.size(); i++) {
                        if (white_bishop.get(i).first.equals(p1.first) &&
                                white_bishop.get(i).second.equals(p1.second)) {
                            white_bishop.remove(i);
                        }
                    }
                }

                if (type.equals("WHITE_ROOK")) {
                    for (int i = 0; i < white_rook.size(); i++) {
                        if (white_rook.get(i).first.equals(p1.first) &&
                                white_rook.get(i).second.equals(p1.second)) {
                            white_rook.remove(i);
                        }
                    }
                }

                black_king = new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }

                if (type.equals("WHITE_QUEEN")) {
                    white_queen = cp;
                }

                if (type.equals("WHITE_PAWN")) {
                    white_pawn.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("WHITE_BISHOP")) {
                    white_bishop.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("WHITE_KNIGHT")) {
                    white_knight.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("WHITE_ROOK")) {
                    white_rook.add(new Pair<>(p1.first, p1.second));
                }

            }

            black_king = tmp;

        } else {
            index = 0;
            List<Pair<Integer, Integer>> tmpMove = white_pawn;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidPawnMoves(p.first, p.second, "BLACK");
                possibleAttackMove = findValidPawnAttackMove(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = white_pawn.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    white_pawn.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_pawn_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("BLACk_QUEEN")) {
                        cp = black_queen;
                        black_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("BLACK_PAWN")) {
                        for (int i = 0; i < black_pawn.size(); i++) {
                            if (black_pawn.get(i).first.equals(p1.first) &&
                                    black_pawn.get(i).second.equals(p1.second)) {
                                black_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        for (int i = 0; i < black_knight.size(); i++) {
                            if (black_knight.get(i).first.equals(p1.first) &&
                                    black_knight.get(i).second.equals(p1.second)) {
                                black_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        for (int i = 0; i < black_bishop.size(); i++) {
                            if (black_bishop.get(i).first.equals(p1.first) &&
                                    black_bishop.get(i).second.equals(p1.second)) {
                                black_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_ROOK")) {
                        for (int i = 0; i < black_rook.size(); i++) {
                            if (black_rook.get(i).first.equals(p1.first) &&
                                    black_rook.get(i).second.equals(p1.second)) {
                                black_rook.remove(i);
                            }
                        }
                    }

                    white_pawn.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_pawn_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (!this.isCheck) {
                        valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("BLACK_QUEEN")) {
                        black_queen = cp;
                    }

                    if (type.equals("BLACK_PAWN")) {
                        black_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        black_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        black_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_ROOK")) {
                        black_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                white_pawn.set(index, tmp);
                index++;
            }

            index = 0;
            tmpMove = white_knight;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidKnightMoves(p.first, p.second, "BLACK");
                possibleAttackMove = findValidKnightAttackMoves(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = white_knight.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    white_knight.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_knight_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("BLACk_QUEEN")) {
                        cp = black_queen;
                        black_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("BLACK_PAWN")) {
                        for (int i = 0; i < black_pawn.size(); i++) {
                            if (black_pawn.get(i).first.equals(p1.first) &&
                                    black_pawn.get(i).second.equals(p1.second)) {
                                black_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        for (int i = 0; i < black_knight.size(); i++) {
                            if (black_knight.get(i).first.equals(p1.first) &&
                                    black_knight.get(i).second.equals(p1.second)) {
                                black_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        for (int i = 0; i < black_bishop.size(); i++) {
                            if (black_bishop.get(i).first.equals(p1.first) &&
                                    black_bishop.get(i).second.equals(p1.second)) {
                                black_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_ROOK")) {
                        for (int i = 0; i < black_rook.size(); i++) {
                            if (black_rook.get(i).first.equals(p1.first) &&
                                    black_rook.get(i).second.equals(p1.second)) {
                                black_rook.remove(i);
                            }
                        }
                    }

                    white_knight.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_knight_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (!this.isCheck) {
                        valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("BLACK_QUEEN")) {
                        black_queen = cp;
                    }

                    if (type.equals("BLACK_PAWN")) {
                        black_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        black_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        black_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_ROOK")) {
                        black_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                white_knight.set(index, tmp);
                index++;
            }

            index = 0;
            tmpMove = white_rook;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidRookMove(p.first, p.second, "BLACK");
                possibleAttackMove = findValidRookAttackMove(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = white_rook.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    white_rook.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_rook_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("BLACk_QUEEN")) {
                        cp = black_queen;
                        black_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("BLACK_PAWN")) {
                        for (int i = 0; i < black_pawn.size(); i++) {
                            if (black_pawn.get(i).first.equals(p1.first) &&
                                    black_pawn.get(i).second.equals(p1.second)) {
                                black_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        for (int i = 0; i < black_knight.size(); i++) {
                            if (black_knight.get(i).first.equals(p1.first) &&
                                    black_knight.get(i).second.equals(p1.second)) {
                                black_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        for (int i = 0; i < black_bishop.size(); i++) {
                            if (black_bishop.get(i).first.equals(p1.first) &&
                                    black_bishop.get(i).second.equals(p1.second)) {
                                black_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_ROOK")) {
                        for (int i = 0; i < black_rook.size(); i++) {
                            if (black_rook.get(i).first.equals(p1.first) &&
                                    black_rook.get(i).second.equals(p1.second)) {
                                black_rook.remove(i);
                            }
                        }
                    }

                    white_rook.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_rook_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (!this.isCheck) {
                        valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("BLACK_QUEEN")) {
                        black_queen = cp;
                    }

                    if (type.equals("BLACK_PAWN")) {
                        black_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        black_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        black_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_ROOK")) {
                        black_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                white_rook.set(index, tmp);
                index++;
            }

            index = 0;
            tmpMove = white_bishop;
            for (Pair<Integer, Integer> p : tmpMove) {
                possibleMove = findValidBishopMove(p.first, p.second, "BLACK");
                possibleAttackMove = findValidBishopAttackMove(p.first, p.second, "BLACK");

                Pair<Integer, Integer> tmp = white_bishop.get(index);
                Log.d("important", "possible moves" + possibleMove.toString());
                for (Pair<Integer, Integer> p1 : possibleMove) {
                    white_bishop.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_bishop_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }
                }

                for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                    String type = detect(p1.first, p1.second);
                    Pair<Integer, Integer> cp = new Pair<>(0, 0);

                    if (type.equals("BLACk_QUEEN")) {
                        cp = black_queen;
                        black_queen = new Pair<>(-1, -1);
                    }

                    if (type.equals("BLACK_PAWN")) {
                        for (int i = 0; i < black_pawn.size(); i++) {
                            if (black_pawn.get(i).first.equals(p1.first) &&
                                    black_pawn.get(i).second.equals(p1.second)) {
                                black_pawn.remove(i);
                            }
                        }

                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        for (int i = 0; i < black_knight.size(); i++) {
                            if (black_knight.get(i).first.equals(p1.first) &&
                                    black_knight.get(i).second.equals(p1.second)) {
                                black_knight.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        for (int i = 0; i < black_bishop.size(); i++) {
                            if (black_bishop.get(i).first.equals(p1.first) &&
                                    black_bishop.get(i).second.equals(p1.second)) {
                                black_bishop.remove(i);
                            }
                        }
                    }

                    if (type.equals("BLACK_ROOK")) {
                        for (int i = 0; i < black_rook.size(); i++) {
                            if (black_rook.get(i).first.equals(p1.first) &&
                                    black_rook.get(i).second.equals(p1.second)) {
                                black_rook.remove(i);
                            }
                        }
                    }

                    white_bishop.set(index, new Pair<>(p1.first, p1.second));

                    detectCheck();

                    if (!this.isCheck) {
                        valid_bishop_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (!this.isCheck) {
                        valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                        validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                        this.isCheck = true;
                    }

                    if (type.equals("BLACK_QUEEN")) {
                        black_queen = cp;
                    }

                    if (type.equals("BLACK_PAWN")) {
                        black_pawn.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_BISHOP")) {
                        black_bishop.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_KNIGHT")) {
                        black_knight.add(new Pair<>(p1.first, p1.second));
                    }

                    if (type.equals("BLACK_ROOK")) {
                        black_rook.add(new Pair<>(p1.first, p1.second));
                    }
                }

                white_bishop.set(index, tmp);
                index++;
            }

//            for (Pair<Integer, Integer> p : tmpMove) {
            possibleMove = findValidQueenMove(white_queen.first, white_queen.second, "BLACK");
            possibleAttackMove = findValidQueenAttackMove(white_queen.first, white_queen.second, "BLACK");

            Pair<Integer, Integer> tmp = white_queen;

            Log.d("important", "possible moves" + possibleMove.toString());
            for (Pair<Integer, Integer> p1 : possibleMove) {
                white_queen =  new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_queen_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }
            }

            for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                String type = detect(p1.first, p1.second);
                Pair<Integer, Integer> cp = new Pair<>(0, 0);

                if (type.equals("BLACk_QUEEN")) {
                    cp = black_queen;
                    black_queen = new Pair<>(-1, -1);
                }

                if (type.equals("BLACK_PAWN")) {
                    for (int i = 0; i < black_pawn.size(); i++) {
                        if (black_pawn.get(i).first.equals(p1.first) &&
                                black_pawn.get(i).second.equals(p1.second)) {
                            black_pawn.remove(i);
                        }
                    }

                }

                if (type.equals("BLACK_KNIGHT")) {
                    for (int i = 0; i < black_knight.size(); i++) {
                        if (black_knight.get(i).first.equals(p1.first) &&
                                black_knight.get(i).second.equals(p1.second)) {
                            black_knight.remove(i);
                        }
                    }
                }

                if (type.equals("BLACK_BISHOP")) {
                    for (int i = 0; i < black_bishop.size(); i++) {
                        if (black_bishop.get(i).first.equals(p1.first) &&
                                black_bishop.get(i).second.equals(p1.second)) {
                            black_bishop.remove(i);
                        }
                    }
                }

                if (type.equals("BLACK_ROOK")) {
                    for (int i = 0; i < black_rook.size(); i++) {
                        if (black_rook.get(i).first.equals(p1.first) &&
                                black_rook.get(i).second.equals(p1.second)) {
                            black_rook.remove(i);
                        }
                    }
                }

                white_queen = new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_queen_attack_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }

                if (!this.isCheck) {
                    valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }

                if (type.equals("BLACK_QUEEN")) {
                    black_queen = cp;
                }

                if (type.equals("BLACK_PAWN")) {
                    black_pawn.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("BLACK_BISHOP")) {
                    black_bishop.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("BLACK_KNIGHT")) {
                    black_knight.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("BLACK_ROOK")) {
                    black_rook.add(new Pair<>(p1.first, p1.second));
                }
            }

            white_queen = tmp;

            possibleMove = findValidKingMove(white_king.first, white_king.second, "BLACK");
            possibleAttackMove = findValidKingAttackMove(white_king.first, white_king.second, "BLACK");

            tmp = white_king;

            Log.d("important", "possible moves" + possibleMove.toString());
            for (Pair<Integer, Integer> p1 : possibleMove) {
                white_king =  new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_king_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }
            }

            for (Pair<Integer, Integer> p1 : possibleAttackMove) {
                String type = detect(p1.first, p1.second);
                Pair<Integer, Integer> cp = new Pair<>(0, 0);

                if (type.equals("BLACk_QUEEN")) {
                    cp = black_queen;
                    black_queen = new Pair<>(-1, -1);
                }

                if (type.equals("BLACK_PAWN")) {
                    for (int i = 0; i < black_pawn.size(); i++) {
                        if (black_pawn.get(i).first.equals(p1.first) &&
                                black_pawn.get(i).second.equals(p1.second)) {
                            black_pawn.remove(i);
                        }
                    }

                }

                if (type.equals("BLACK_KNIGHT")) {
                    for (int i = 0; i < black_knight.size(); i++) {
                        if (black_knight.get(i).first.equals(p1.first) &&
                                black_knight.get(i).second.equals(p1.second)) {
                            black_knight.remove(i);
                        }
                    }
                }

                if (type.equals("BLACK_BISHOP")) {
                    for (int i = 0; i < black_bishop.size(); i++) {
                        if (black_bishop.get(i).first.equals(p1.first) &&
                                black_bishop.get(i).second.equals(p1.second)) {
                            black_bishop.remove(i);
                        }
                    }
                }

                if (type.equals("BLACK_ROOK")) {
                    for (int i = 0; i < black_rook.size(); i++) {
                        if (black_rook.get(i).first.equals(p1.first) &&
                                black_rook.get(i).second.equals(p1.second)) {
                            black_rook.remove(i);
                        }
                    }
                }

                white_king = new Pair<>(p1.first, p1.second);

                detectCheck();

                if (!this.isCheck) {
                    valid_king_attack_moves.add(new Pair<>(p1.first, p1.second));
                    validMoves.add(new Pair<>("BLACK_PAWN", new Pair<>(p1.first, p1.second)));
                    this.isCheck = true;
                }

                if (type.equals("BLACK_QUEEN")) {
                    black_queen = cp;
                }

                if (type.equals("BLACK_PAWN")) {
                    black_pawn.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("BLACK_BISHOP")) {
                    black_bishop.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("BLACK_KNIGHT")) {
                    black_knight.add(new Pair<>(p1.first, p1.second));
                }

                if (type.equals("BLACK_ROOK")) {
                    black_rook.add(new Pair<>(p1.first, p1.second));
                }
            }

            white_king = tmp;
        }

        return validMoves;
    }

    public void detectCheck() {
        List<Pair<Integer, Integer>> possibleMove;

        this.isCheck = false;

        if (this.black_turn) {
            // for black pawn
            for (Pair<Integer, Integer> p1 : this.white_pawn) {
                possibleMove = findValidPawnAttackMove(p1.first, p1.second, "WHITE");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.black_king.first) && p.second.equals(this.black_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            for (Pair<Integer, Integer> p1 : this.white_bishop) {
                possibleMove = findValidBishopAttackMove(p1.first, p1.second, "WHITE");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.black_king.first) && p.second.equals(this.black_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            for (Pair<Integer, Integer> p1 : this.white_rook) {
                possibleMove = findValidRookAttackMove(p1.first, p1.second, "WHITE");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.black_king.first) && p.second.equals(this.black_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            for (Pair<Integer, Integer> p1 : this.white_knight) {
                possibleMove = findValidKnightAttackMoves(p1.first, p1.second, "WHITE");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.black_king.first) && p.second.equals(this.black_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            possibleMove = findValidQueenAttackMove(white_queen.first, white_queen.second, "WHITE");
            for (Pair<Integer, Integer> p : possibleMove) {
                if (p.first.equals(this.black_king.first) && p.second.equals(this.black_king.second)) {
                    this.isCheck = true;
                    break;
                }
            }

        } else {
            for (Pair<Integer, Integer> p1 : this.black_pawn) {
                possibleMove = findValidPawnAttackMove(p1.first, p1.second, "BLACK");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.white_king.first) && p.second.equals(this.white_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            for (Pair<Integer, Integer> p1 : this.black_bishop) {
                possibleMove = findValidBishopAttackMove(p1.first, p1.second, "BLACK");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.white_king.first) && p.second.equals(this.white_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            for (Pair<Integer, Integer> p1 : this.black_rook) {
                possibleMove = findValidRookAttackMove(p1.first, p1.second, "BLACK");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.white_king.first) && p.second.equals(this.white_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            for (Pair<Integer, Integer> p1 : this.black_knight) {
                possibleMove = findValidKnightAttackMoves(p1.first, p1.second, "BLACK");
                for (Pair<Integer, Integer> p : possibleMove) {
                    if (p.first.equals(this.white_king.first) && p.second.equals(this.white_king.second)) {
                        this.isCheck = true;
                        break;
                    }
                }
            }

            possibleMove = findValidQueenAttackMove(black_queen.first, black_queen.second, "BLACK");
            for (Pair<Integer, Integer> p : possibleMove) {
                if (p.first.equals(this.white_king.first) && p.second.equals(this.white_king.second)) {
                    this.isCheck = true;
                    break;
                }
            }
        }
    }
}