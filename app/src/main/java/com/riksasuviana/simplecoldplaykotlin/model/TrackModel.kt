package com.riksasuviana.simplecoldplaykotlin.model

/**
 * Created by riksa on 06/12/2017.
 */
class TrackModel {

    var track:List<Track>? = null

    class Track{
        var idTrack:String? = null
        var idAlbum:String? = null
        var idArtist:String? = null
        var idLyric:String? = null
        var idIMVDB:String? = null
        var strTrack:String? = null
        var strAlbum:String? = null
        var strArtist:String? = null
        var strArtistAlternate:String? = null
        var intCD:String? = null
        var intDuration:String? = null
        var strGenre:String? = null
        var strMood:String? = null
        var strStyle:String? = null
        var strTheme:String? = null
        var strDescriptionEN:String? = null
        var strTrackThumb:String? = null
        var strTrackLyrics:String? = null
        var strMusicVid:String? = null
        var strMusicVidDirector:String? = null
        var strMusicVidCompany:String? = null
        var strMusicVidScreen1:String? = null
        var strMusicVidScreen2:String? = null
        var strMusicVidScreen3:String? = null
        var intMusicVidViews:String? = null
        var intMusicVidLikes:String? = null
        var intMusicVidDislikes:String? = null
        var intMusicVidFavorites:String? = null
        var intMusicVidComments:String? = null
        var intTrackNumber:String? = null
        var intLoved:String? = null
        var intScore:String? = null
        var intScoreVotes:String? = null
        var strMusicBrainzID:String? = null
        var strMusicBrainzAlbumID:String? = null
        var strMusicBrainzArtistID:String? = null
        var strLocked:String? = null
    }
}