/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import game.tarot.Score;

/**
 *
 * @author paul
 */
public abstract class PublicProfil {
    Score score;
    
    
    public Score getScore() {
        return new Score(score);
    }
}
