package p11OnlineRadioDatabase;

import p11OnlineRadioDatabase.exeptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Playlist playlist = new Playlist();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] songTokens = reader.readLine().split(";");

            String artist = songTokens[0];
            String name = songTokens[1];
            String[] lengthTokens = songTokens[2].split(":");
            String minutes = lengthTokens[0];
            String seconds = lengthTokens[1];

            try {
                Song song = new Song(artist, name, minutes, seconds);
                playlist.addSong(song);
                System.out.println("Song added.");
            } catch (InvalidSongException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(String.format("Songs added: %d", playlist.getNumberOfSongs()));
        System.out.println(playlist);
    }
}
