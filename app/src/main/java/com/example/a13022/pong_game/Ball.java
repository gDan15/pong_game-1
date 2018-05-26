package com.example.a13022.pong_game;


import android.graphics.RectF;

import java.util.Random;


/**
 * Created by Gaetan on 13-04-18.
 */
//TODO : implémenter fonction reset pour repositionner la balle
public class Ball {


    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private float mBallWidth;
    private float mBallHeight;
    public Ball(int screenX, int screenY){

        // Make the mBall size relative to the screen resolution
        mBallWidth = screenX / 70;
        mBallHeight = mBallWidth;

    /*
        Start the ball travelling straight up
        at a quarter of the screen height per second
    */
        mYVelocity = screenY / 4;
        mXVelocity = mYVelocity;

        // Initialize the Rect that represents the mBall
        mRect = new RectF();

    }
    // Give access to the Rect
    public RectF getRect(){
        return mRect;
    }
    // Change the position each frame
    public void update(long fps){
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);
        mRect.right = mRect.left + mBallWidth;
        mRect.bottom = mRect.top - mBallHeight;
    }
    // Reverse the vertical heading
    public void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }

    // Reverse the horizontal heading
    public void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    public void setRandomXVelocity(){

        // Generate a random number either 0 or 1
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }
    // Speed up by 10%
    // A score of over 20 is quite difficult
    // Reduce or increase 10 to make this easier or harder
    public void increaseVelocity(byte x){
        mXVelocity = mXVelocity + mXVelocity / 10*(float) x;
        mYVelocity = mYVelocity + mYVelocity / 10*(float) x;
    }
    public void clearObstacleY(float y){
        mRect.bottom = y;
        mRect.top = y - mBallHeight;
    }

    public void clearObstacleX(float x){
        mRect.left = x;
        mRect.right = x + mBallWidth;
    }
    public float getXCoordonate(){
        float averageX = (- mRect.left + mRect.right)/2 + mRect.left;
        return averageX;
    }
    public void reset(int x, int y){
        mRect.left = x / 2;
        mRect.top = y - 1000;
        mRect.right = x / 2 + mBallWidth;
        mRect.bottom = y - 1000 - mBallHeight;
    }
}
