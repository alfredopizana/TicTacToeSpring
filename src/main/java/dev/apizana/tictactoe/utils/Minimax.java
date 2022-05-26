package dev.apizana.tictactoe.utils;

import dev.apizana.tictactoe.domain.dtos.MovementDto;
import dev.apizana.tictactoe.domain.models.MovementSymbol;

import java.util.ArrayList;

public class Minimax {

    public MovementDto NextMovement(MovementSymbol[] board , MovementSymbol currentPlayer){

        return null;
    }

    public static int[] checkForWinner(MovementSymbol[] board, MovementSymbol symbol){
        //Check vertical and horizontal
        /*
            [ 0, 1, 2,
              3, 4, 5,
              6, 7, 8 ]

            X and Y check
            Iteration test

            x=0
            (3*position) + 0 = (0) + 0 = 0
            (3*position) + 1 = (0) + 1 = 1
            (3*position) + 2 = (0) + 2 = 2
            [1,3,6]
            (position) + 0 = (0) + 0 = 0
            (position) + 3 = (0) + 3 = 3
            (position) + 6 = (0) + 6 = 6


            x=1
            (3*position) + 0 = (3) + 0 = 1
            (3*position) + 1 = (3) + 3 = 6
            (3*position) + 2 = (3) + 6 = 9

            [1,4,7]
            (position) + 0 = (1) + 0 = 0
            (position) + 3 = (1) + 1 = 4
            (position) + 6 = (1) + 2 = 7

            x=2
            (3*position) + 0 = (6) + 0 = 6
            (3*position) + 1 = (6) + 1 = 7
            (3*position) + 2 = (6) + 2 = 8

            [2,5,8]
            (position) + 0 = (2) + 0 = 0
            (position) + 3 = (2) + 3 = 5
            (position) + 6 = (2) + 6 = 8
         */


        for(int position =0; position < 3; position++){
            //check vertical win
            if(
                    board[position] == symbol &&
                    board[position + 3] == symbol &&
                    board[position + 6] == symbol
            )
                return new int[]{position, position + 3, position + 6} ;
            //check Horizontal win
            if(     board[(position*3)] == symbol &&
                    board[(position*3)+1] == symbol &&
                    board[(position*3)+2] == symbol)
                return new int[]{(position*3), (position*3)+1, (position*3)+2} ;

        }
        //check diagonal top-left to bottom right
        if(     board[0] == symbol &&
                board[4] == symbol &&
                board[8] == symbol)
            return new int[]{0,4,8};
        //check diagonal bottom-left to top-right
        if(     board[2] == symbol &&
                board[4] == symbol &&
                board[6] == symbol)
            return new int[]{2,4,6};
        //Check Cross
        return new int[]{};
    }

    public String bestMove(){
        return null;
    }

    public static MovementDto randomMove(MovementSymbol[] board , MovementSymbol currentPlayer){
        int startingPoint = getRandomNumber(0,8);
        for(int position =0; position < 9; position++) {
            if (board[(startingPoint + position) % 9] == MovementSymbol.none) {
                MovementDto randomMovement = new MovementDto();
                randomMovement.setSymbol(currentPlayer);
                randomMovement.setPosition((startingPoint + position) % 9);
                return randomMovement;
            }
        }
        return null;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
