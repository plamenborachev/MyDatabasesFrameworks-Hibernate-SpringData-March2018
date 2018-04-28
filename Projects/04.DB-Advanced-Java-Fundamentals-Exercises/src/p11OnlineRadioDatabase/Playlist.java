package p11OnlineRadioDatabase;

import java.util.LinkedList;
import java.util.List;

public class Playlist {

    private List<Song> songs;

    public Playlist() {
        this.songs = new LinkedList<>();
    }

    public void addSong(Song song){
        this.songs.add(song);
    }

    public int getNumberOfSongs(){
        return this.songs.size();
    }

    private int playlistLengthInSec(){
        return this.songs.stream().mapToInt(Song::getTotalSeconds).sum();
    }

    @Override
    public String toString() {
        int hours = this.playlistLengthInSec() / 3600;
        int minutes = (this.playlistLengthInSec() - hours * 3600) / 60;
        int seconds = this.playlistLengthInSec() - hours * 3600 - minutes * 60;
        return String.format("Playlist length: %dh %dm %ds", hours, minutes, seconds);
    }
}
