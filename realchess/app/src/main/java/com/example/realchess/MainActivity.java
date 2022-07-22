package com.example.realchess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // ImageView[][] board;
    Chess chess = new Chess();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reset Button
        Button reset = findViewById(R.id.reset);

        TextView status = findViewById(R.id.status);
        TextView checkStatus = findViewById(R.id.check);

        if (chess.black_turn) {
            status.setText("BLACK TURN");
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chess.play();
                if (chess.black_turn) {
                    status.setText("BLACK TURN");
                    checkStatus.setText("");
                }
            }
        });

        // board = new ImageView[8][8];

        // Row 1
        chess.board[0][0] = findViewById(R.id.imageView1);
        chess.board[0][1] = findViewById(R.id.imageView2);
        chess.board[0][2] = findViewById(R.id.imageView3);
        chess.board[0][3] = findViewById(R.id.imageView4);
        chess.board[0][4] = findViewById(R.id.imageView5);
        chess.board[0][5] = findViewById(R.id.imageView6);
        chess.board[0][6] = findViewById(R.id.imageView7);
        chess.board[0][7] = findViewById(R.id.imageView8);

        // Row 2
        chess.board[1][0] = findViewById(R.id.imageView9);
        chess.board[1][1] = findViewById(R.id.imageView10);
        chess.board[1][2] = findViewById(R.id.imageView11);
        chess.board[1][3] = findViewById(R.id.imageView12);
        chess.board[1][4] = findViewById(R.id.imageView13);
        chess.board[1][5] = findViewById(R.id.imageView14);
        chess.board[1][6] = findViewById(R.id.imageView15);
        chess.board[1][7] = findViewById(R.id.imageView16);

        // Row 3
        chess.board[2][0] = findViewById(R.id.imageView17);
        chess.board[2][1] = findViewById(R.id.imageView18);
        chess.board[2][2] = findViewById(R.id.imageView19);
        chess.board[2][3] = findViewById(R.id.imageView20);
        chess.board[2][4] = findViewById(R.id.imageView21);
        chess.board[2][5] = findViewById(R.id.imageView22);
        chess.board[2][6] = findViewById(R.id.imageView23);
        chess.board[2][7] = findViewById(R.id.imageView24);

        // Row 4
        chess.board[3][0] = findViewById(R.id.imageView25);
        chess.board[3][1] = findViewById(R.id.imageView26);
        chess.board[3][2] = findViewById(R.id.imageView27);
        chess.board[3][3] = findViewById(R.id.imageView28);
        chess.board[3][4] = findViewById(R.id.imageView29);
        chess.board[3][5] = findViewById(R.id.imageView30);
        chess.board[3][6] = findViewById(R.id.imageView31);
        chess.board[3][7] = findViewById(R.id.imageView32);

        // Row 5
        chess.board[4][0] = findViewById(R.id.imageView33);
        chess.board[4][1] = findViewById(R.id.imageView34);
        chess.board[4][2] = findViewById(R.id.imageView35);
        chess.board[4][3] = findViewById(R.id.imageView36);
        chess.board[4][4] = findViewById(R.id.imageView37);
        chess.board[4][5] = findViewById(R.id.imageView38);
        chess.board[4][6] = findViewById(R.id.imageView39);
        chess.board[4][7] = findViewById(R.id.imageView40);

        // Row 6
        chess.board[5][0] = findViewById(R.id.imageView41);
        chess.board[5][1] = findViewById(R.id.imageView42);
        chess.board[5][2] = findViewById(R.id.imageView43);
        chess.board[5][3] = findViewById(R.id.imageView44);
        chess.board[5][4] = findViewById(R.id.imageView45);
        chess.board[5][5] = findViewById(R.id.imageView46);
        chess.board[5][6] = findViewById(R.id.imageView47);
        chess.board[5][7] = findViewById(R.id.imageView48);

        // Row 7
        chess.board[6][0] = findViewById(R.id.imageView49);
        chess.board[6][1] = findViewById(R.id.imageView50);
        chess.board[6][2] = findViewById(R.id.imageView51);
        chess.board[6][3] = findViewById(R.id.imageView52);
        chess.board[6][4] = findViewById(R.id.imageView53);
        chess.board[6][5] = findViewById(R.id.imageView54);
        chess.board[6][6] = findViewById(R.id.imageView55);
        chess.board[6][7] = findViewById(R.id.imageView56);

        // Row 8
        chess.board[7][0] = findViewById(R.id.imageView57);
        chess.board[7][1] = findViewById(R.id.imageView58);
        chess.board[7][2] = findViewById(R.id.imageView59);
        chess.board[7][3] = findViewById(R.id.imageView60);
        chess.board[7][4] = findViewById(R.id.imageView61);
        chess.board[7][5] = findViewById(R.id.imageView62);
        chess.board[7][6] = findViewById(R.id.imageView63);
        chess.board[7][7] = findViewById(R.id.imageView64);

        chess.play();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                int finalI = i;
                int finalJ =j;

                chess.board[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tmp = chess.detect(finalI, finalJ);
                        // Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();

                        List<Pair<String,Pair<Integer, Integer>>> validMovesAfterCheck = new ArrayList<>();


                        if (chess.isSelect) {
                            chess.isSelect = false;

                            if ((chess.previousIsSelectI + chess.previousIsSelectJ) % 2 == 0) {
                                Toast.makeText(MainActivity.this, "white" , Toast.LENGTH_SHORT).show();
                                chess.board[chess.previousIsSelectI][chess.previousIsSelectJ].
                                        setBackgroundColor(Color.parseColor("#E4E4E4"));
                            } else {
                                int result = (chess.previousIsSelectI + chess.previousIsSelectJ) % 2;
                                Toast.makeText(MainActivity.this, "purple" + chess.previousIsSelectI + " " +
                                        chess.previousIsSelectJ + " " + result, Toast.LENGTH_LONG).show();
                                chess.board[chess.previousIsSelectI][chess.previousIsSelectJ].
                                        setBackgroundColor(Color.parseColor("#923FB5"));
                            }
                            for (Pair<Integer, Integer> p: chess.validMoves) {
                                chess.board[p.first][p.second].setImageResource(android.R.color.transparent);
                            }

                            for (Pair<Integer, Integer> p: chess.attackMoves) {
                                chess.board[p.first][p.second].setImageResource(android.R.color.transparent);
                            }


                            for (Pair<Integer, Integer> p: chess.validMoves) {
                                if (p.first == finalI && p.second == finalJ) {

                                    chess.isCheck = false;

                                    if (chess.previousPieceType.equals("BLACK_PAWN")) {
                                        chess.black_pawn.add(new Pair<>(p.first, p.second));
                                        // chess.board[p.first][p.second].setImageResource(R.drawable.pawn_black);
                                        // chess.attackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_PAWN")) {
                                        chess.white_pawn.add(new Pair<>(p.first, p.second));
                                        //chess.board[p.first][p.second].setImageResource(R.drawable.pawn_white);
                                        // chess.attackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_KNIGHT")) {
                                        chess.black_knight.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_KNIGHT")) {
                                        chess.white_knight.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_BISHOP")) {
                                        chess.black_bishop.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_BISHOP")) {
                                        chess.white_bishop.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_ROOK")) {
                                        chess.black_rook.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidRookAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_ROOK")) {
                                        chess.white_rook.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidRookAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_QUEEN")) {
                                        chess.black_queen = new Pair<>(p.first, p.second);
                                        // chess.attackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_QUEEN")) {
                                        chess.white_queen = new Pair<>(p.first, p.second);
                                        // chess.attackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_KING")) {
                                        chess.black_king = new Pair<>(p.first, p.second);
                                        // chess.attackMoves = chess.findValidKingAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_KING")) {
                                        chess.white_king = new Pair<>(p.first, p.second);
                                    }

                                    chess.black_turn = !chess.black_turn;

                                    if (chess.black_turn) {
                                        status.setText("BLACK TURN");
                                    } else {
                                        status.setText("WHITE TURN");
                                    }


                                    chess.remove(chess.previousIsSelectI, chess.previousIsSelectJ);

                                    chess.detectCheck();

                                    if (chess.isCheck) {
                                        Log.d("important", "White queen position: " +
                                                chess.white_queen.first + " " + chess.white_queen.second);

                                        Log.d("important", "Black queen position: " +
                                                chess.black_queen.first + " " + chess.black_queen.second);

                                        validMovesAfterCheck = chess.detectAllPossibleMoveAfterCheck();
                                        if (validMovesAfterCheck.size() > 0) {
                                            checkStatus.setText("CHECK");
                                            // Log.d("important", "valid moves after check" + validMovesAfterCheck.toString());
                                        } else {
                                            checkStatus.setText("CHECKMATE");
                                        }

                                        Log.d("important", "White queen position: " +
                                                chess.white_queen.first + " " + chess.white_queen.second);

                                        Log.d("important", "Black queen position: " +
                                                chess.black_queen.first + " " + chess.black_queen.second);

                                    } else {
                                        checkStatus.setText("");
                                    }
                                    // chess.board[chess.previousIsSelectI][chess.previousIsSelectJ].setImageResource(android.R.color.transparent);
                                }
                            }

                            for (Pair<Integer, Integer> p: chess.attackMoves) {
                                Toast.makeText(MainActivity.this, "Previous Type" + chess.previousPieceType,
                                        Toast.LENGTH_LONG).show();

                                if (p.first == finalI && p.second == finalJ) {
                                    chess.isCheck = false;

                                    chess.remove(finalI, finalJ);

                                    if (chess.previousPieceType.equals("BLACK_PAWN")) {
                                        chess.black_pawn.add(new Pair<>(p.first, p.second));
                                        // chess.board[p.first][p.second].setImageResource(R.drawable.pawn_black);
                                        // chess.attackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_PAWN")) {
                                        chess.white_pawn.add(new Pair<>(p.first, p.second));
                                        //chess.board[p.first][p.second].setImageResource(R.drawable.pawn_white);
                                        // chess.attackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_KNIGHT")) {
                                        chess.black_knight.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_KNIGHT")) {
                                        chess.white_knight.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_BISHOP")) {
                                        chess.black_bishop.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_BISHOP")) {
                                        chess.white_bishop.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_ROOK")) {
                                        chess.black_rook.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidRookAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_ROOK")) {
                                        chess.white_rook.add(new Pair<>(p.first, p.second));
                                        // chess.attackMoves = chess.findValidRookAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_QUEEN")) {
                                        chess.black_queen = new Pair<>(p.first, p.second);
                                        // chess.attackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_QUEEN")) {
                                        chess.white_queen = new Pair<>(p.first, p.second);
                                        // chess.attackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    if (chess.previousPieceType.equals("BLACK_KING")) {
                                        chess.black_king = new Pair<>(p.first, p.second);
                                        //chess.attackMoves = chess.findValidKingAttackMove(finalI, finalJ, "BLACK");
                                    }

                                    if (chess.previousPieceType.equals("WHITE_KING")) {
                                        chess.white_king = new Pair<>(p.first, p.second);
                                        // chess.attackMoves = chess.findValidKingAttackMove(finalI, finalJ, "WHITE");
                                    }

                                    chess.black_turn = !chess.black_turn;

                                    if (chess.black_turn) {
                                        status.setText("BLACK TURN");
                                    } else {
                                        status.setText("WHITE TURN");
                                    }

                                    chess.detectCheck();

                                    if (chess.isCheck) {
                                        validMovesAfterCheck = chess.detectAllPossibleMoveAfterCheck();
                                        if (validMovesAfterCheck.size() > 0) {
                                            checkStatus.setText("CHECK");
                                        } else {
                                            checkStatus.setText("CHECKMATE");
                                        }
                                    } else {
                                        checkStatus.setText("");
                                    }

                                    chess.remove(chess.previousIsSelectI, chess.previousIsSelectJ);
                                    // chess.board[chess.previousIsSelectI][chess.previousIsSelectJ].setImageResource(android.R.color.transparent);
                                }
                            }

                            chess.display();

                            return;
                        }

                        if (chess.black_turn) {
                            if (tmp.equals("BLACK_PAWN")) {

                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "BLACK_PAWN";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));
                                if (chess.isCheck) {
//                                        checkStatus.setText("CHECK");
//
//                                        for (Pair<String, Pair<Integer, Integer>> v : validMovesAfterCheck) {
//
//                                            if (v.first.equals("BLACK_PAWN")) {
//
//                                            }
//                                        }
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidPawnMoves(finalI, finalJ, "BLACK");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "BLACK");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();
                                    Log.d("test", tmpValidMoves.toString());

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_pawn_moves) {
                                            Log.d("test", tmpV.toString() + " " + V.toString());
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_pawn_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidPawnMoves(finalI, finalJ, "BLACK");
                                    chess.attackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "BLACK");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Resources r = getResources();
                                    Drawable[] layers = new Drawable[2];
                                    layers[0] = ResourcesCompat.getDrawable(getResources(), (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // testimage.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                //chess.black_turn = false;
                                chess.isSelect = true;
                            }

                            if (tmp.equals(("BLACK_KNIGHT"))) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "BLACK_KNIGHT";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidKnightMoves(finalI, finalJ, "BLACK");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "BLACK");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_knight_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_knight_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidKnightMoves(finalI, finalJ, "BLACK");
                                    chess.attackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "BLACK");
                                }


                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(), (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // test image.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = false;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("BLACK_BISHOP")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "BLACK_BISHOP";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidBishopMove(finalI, finalJ, "BLACK");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "BLACK");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_bishop_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_bishop_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidBishopMove(finalI, finalJ, "BLACK");
                                    chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "BLACK");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // test image.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                //chess.black_turn = false;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("BLACK_ROOK")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "BLACK_ROOK";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidRookMove(finalI, finalJ, "BLACK");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidRookAttackMove(finalI, finalJ, "BLACK");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_rook_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_rook_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidRookMove(finalI, finalJ, "BLACK");
                                    chess.attackMoves = chess.findValidRookAttackMove(finalI, finalJ, "BLACK");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // test image.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = false;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("BLACK_QUEEN")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "BLACK_QUEEN";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidQueenMove(finalI, finalJ, "BLACK");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "BLACK");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_queen_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_queen_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidQueenMove(finalI, finalJ, "BLACK");
                                    chess.attackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "BLACK");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // test image.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = false;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("BLACK_KING")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "BLACK_KING";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidKingMove(finalI, finalJ, "BLACK");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidKingAttackMove(finalI, finalJ, "BLACK");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_king_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_king_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidKingMove(finalI, finalJ, "BLACK");
                                    chess.attackMoves = chess.findValidKingAttackMove(finalI, finalJ, "BLACK");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // test image.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = false;
                                chess.isSelect = true;
                            }
                        } else {

                            if (tmp.equals("WHITE_PAWN")) {

                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "WHITE_PAWN";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidPawnMoves(finalI, finalJ, "WHITE");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "WHITE");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_pawn_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_pawn_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidPawnMoves(finalI, finalJ, "WHITE");
                                    chess.attackMoves = chess.findValidPawnAttackMove(finalI, finalJ, "WHITE");
                                }


                                for (Pair<Integer, Integer> p : chess.validMoves) {
//                                Resources r = getResources();
//                                Drawable[] layers = new Drawable[2];
//                                layers[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.pawn_black, null);
//                                layers[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.circle, null);
//                                LayerDrawable layerDrawable = new LayerDrawable(layers);
//                                // testimage.setImageDrawable(layerDrawable);
//                                chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(), (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // testimage.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = true;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("WHITE_KNIGHT")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "WHITE_KNIGHT";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidKnightMoves(finalI, finalJ, "WHITE");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "WHITE");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_knight_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_knight_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidKnightMoves(finalI, finalJ, "WHITE");
                                    chess.attackMoves = chess.findValidKnightAttackMoves(finalI, finalJ, "WHITE");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(), (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    // testimage.setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = true;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("WHITE_BISHOP")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "WHITE_BISHOP";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidBishopMove(finalI, finalJ, "WHITE");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "WHITE");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_bishop_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_bishop_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidBishopMove(finalI, finalJ, "WHITE");
                                    chess.attackMoves = chess.findValidBishopAttackMove(finalI, finalJ, "WHITE");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    //  test image setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = true;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("WHITE_ROOK")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "WHITE_ROOK";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidRookMove(finalI, finalJ, "WHITE");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidRookAttackMove(finalI, finalJ, "WHITE");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_rook_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_rook_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidRookMove(finalI, finalJ, "WHITE");
                                    chess.attackMoves = chess.findValidRookAttackMove(finalI, finalJ, "WHITE");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    //  test image setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                ///chess.black_turn = true;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("WHITE_QUEEN")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "WHITE_QUEEN";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {

                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidQueenMove(finalI, finalJ, "WHITE");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "WHITE");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_queen_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_queen_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }
                                } else {
                                    chess.validMoves = chess.findValidQueenMove(finalI, finalJ, "WHITE");
                                    chess.attackMoves = chess.findValidQueenAttackMove(finalI, finalJ, "WHITE");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    //  test image setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = true;
                                chess.isSelect = true;
                            }

                            if (tmp.equals("WHITE_KING")) {
                                chess.previousIsSelectI = finalI;
                                chess.previousIsSelectJ = finalJ;
                                chess.previousPieceType = "WHITE_KING";

                                chess.board[finalI][finalJ].setBackgroundColor(Color.parseColor("#03fc24"));

                                if  (chess.isCheck) {
                                    List<Pair<Integer, Integer>> tmpValidMoves = chess.findValidKingMove(finalI, finalJ, "WHITE");
                                    List<Pair<Integer, Integer>> tmpAttackMoves = chess.findValidKingAttackMove(finalI, finalJ, "WHITE");

                                    chess.validMoves = new ArrayList<>();
                                    chess.attackMoves = new ArrayList<>();

                                    for (Pair<Integer, Integer> tmpV : tmpValidMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_king_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.validMoves.add(tmpV);
                                            }
                                        }
                                    }

                                    for (Pair<Integer, Integer> tmpV : tmpAttackMoves) {
                                        for (Pair<Integer, Integer>  V : chess.valid_king_attack_moves) {
                                            if (tmpV.first.equals(V.first) && tmpV.second.equals(V.second)) {
                                                chess.attackMoves.add(tmpV);
                                            }
                                        }
                                    }

                                } else {
                                    chess.validMoves = chess.findValidKingMove(finalI, finalJ, "WHITE");
                                    chess.attackMoves = chess.findValidKingAttackMove(finalI, finalJ, "WHITE");
                                }

                                for (Pair<Integer, Integer> p : chess.validMoves) {
                                    chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                for (Pair<Integer, Integer> p : chess.attackMoves) {
                                    Drawable[] layers = new Drawable[2];

                                    layers[0] = ResourcesCompat.getDrawable(getResources(),
                                            (Integer) chess.board[p.first][p.second].getTag(), null);
                                    layers[1] = ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.red_circle, null);
                                    LayerDrawable layerDrawable = new LayerDrawable(layers);
                                    //  test image setImageDrawable(layerDrawable);
                                    chess.board[p.first][p.second].setImageDrawable(layerDrawable);
                                    //chess.board[p.first][p.second].setImageResource(R.drawable.circle);
                                }

                                // chess.black_turn = true;
                                chess.isSelect = true;
                            }
                        }
                    }
                });
            }
        }

    }
}