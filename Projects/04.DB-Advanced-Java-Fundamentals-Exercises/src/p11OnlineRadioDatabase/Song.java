package p11OnlineRadioDatabase;

import p11OnlineRadioDatabase.exeptions.*;

public class Song {
    private String artist;
    private String name;
    private int minutes;
    private int seconds;

    public Song(String artist, String name, String minutes, String seconds) throws InvalidSongException {
        this.setArtist(artist);
        this.setName(name);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    private void setArtist(String artist) throws InvalidArtistNameException {
        if (artist == null || artist.length() < 3 || artist.length() > 20){
            throw new InvalidArtistNameException("Artist name should be between 3 and 20 symbols.");
        }
        this.artist = artist;
    }

    private void setName(String name) throws InvalidSongNameException {
        if (name == null || name.length() < 3 || name.length() > 20){
            throw new InvalidSongNameException("Song name should be between 3 and 30 symbols.");
        }
        this.name = name;
    }

    private void setMinutes(String minutes) throws InvalidSongLengthException {
        int currentMinutes = this.checkLength(minutes);
        if (currentMinutes < 0 || currentMinutes > 14){
            throw new InvalidSongMinutesException("Song minutes should be between 0 and 14.");
        }
        this.minutes = currentMinutes;
    }

    private void setSeconds(String seconds) throws InvalidSongLengthException {
        int currentSeconds = this.checkLength(seconds);
        if (currentSeconds < 0 || currentSeconds > 59){
            throw new InvalidSongSecondsException("Song seconds should be between 0 and 59.");
        }
        this.seconds = currentSeconds;
    }

    public int getTotalSeconds() {
        return this.minutes * 60 + this.seconds;
    }

    private int checkLength(String minutesOrSeconds) throws InvalidSongLengthException {
        try {
            return Integer.parseInt(minutesOrSeconds);
        } catch (NumberFormatException nfe){
            throw new InvalidSongLengthException("Invalid song length.");
        }
    }
}
