package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class PlayGamesAuthProvider {
    public static final String PLAY_GAMES_SIGN_IN_METHOD = "playgames.google.com";
    public static final String PROVIDER_ID = "playgames.google.com";

    private PlayGamesAuthProvider() {
    }

    public static AuthCredential getCredential(String serverAuthCode) {
        return new PlayGamesAuthCredential(serverAuthCode);
    }
}
