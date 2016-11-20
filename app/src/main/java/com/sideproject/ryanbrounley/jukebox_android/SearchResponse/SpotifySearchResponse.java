
package com.sideproject.ryanbrounley.jukebox_android.SearchResponse;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class SpotifySearchResponse {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    /**
     * 
     * @return
     *     The tracks
     */
    public Tracks getTracks() {
        return tracks;
    }

    /**
     * 
     * @param tracks
     *     The tracks
     */
    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

}
