/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * ratings that users assign to songs.
 */
public interface StreamingRadio {

	/*
	 * The abstract methods below are declared as void methods with no
	 * parameters. You need to expand each declaration to specify a return type
	 * and parameters, as necessary.
	 *
	 * You also need to include a detailed comment for each abstract method describing
	 * its effect, its return value, any corner cases that the client may need to
	 * consider, any exceptions the method may throw (including a description of the
	 * circumstances under which this will happen), and so on.
	 *
	 * You should include enough details that a client could use this data structure
	 * without ever being surprised or not knowing what will happen, even though they
	 * haven't read the implementation.
	 */

	/**
	 * Adds a new song to the system. If the song already exists in the system, 
	 * an "AlreadyExistsException" is thrown, as duplicate songs cannot exist.
	 * Ensure that the song is valid, and not a duplicate. If songToAdd == null,
	 * a "NullPointerException" is thrown.
	 * 
	 * @param songToAdd the song to be added.
	 * @throws AlreadyExistsException if song already exists in system.
	 * @throws NullPointerException if songToAdd == null;
	 */
	void addSong(Song songToAdd) throws AlreadyExistsException, NullPointerException;

	/**
	 * Removes an existing song from the system. Method will try to search
	 * for the song (based on artist and title). If there is no match in
	 * the system, a "NonexistentException" is thrown. If songToRemove == null,
	 * a "NullPointerException" is thrown.
	 * 
	 * @param songToRemove the song to be removed.
	 * @throws NonexistentException if song doesn't exist.
	 * @throws NullPointerException if songToRemove == null.
	 */
	void removeSong(Song songToRemove) throws NonexistentException, NullPointerException;

	/**
	 * Adds an existing song to the playlist for an existing radio station. This
	 * should check if the station exists first. If not, throw a "NonexistentException".
	 * Next, it should check to see if the song exists in the system. If it doesn't, then 
	 * throw a "NonExistentException". Next, check to see if the song exists in the station.
	 * If so, then throw an "AlreadyExistsException". If the songToAdd == null, then throw a 
	 * "NullPointerException". Ensure that a valid song object is provided. Once all checks
	 * pass, the song is added to the station's playlist.
	 * 
	 * @param songToAdd the song to be added.
	 * @param station the station being added to.
	 * @throws NonexistentException if station doesn't exist, or if song doesn't exist.
	 * @throws AlreadyExistsException if song already exists in station.

	 * */
	void addToStation(Song songToAdd, Station station);

	/**
	 * Removes an existing song from the playlist for an existing radio station. This
	 * should check if the station exists first. If not, throw a "NonexistentException".
	 * Next, it should check to see if the song exists in the system. If it doesn't, then 
	 * throw a "NonExistentException". Next, check to see if the song exists in the station.
	 * If not, then throw a "NonExistentException". If the songToRemove == null, then throw a 
	 * "NullPointerException". Once all checks pass, the song is removed from the station's playlist.	
	 * 
	 * @param songToRemove the song to be removed.
	 * @param station the station being removed from.
	 * @throws NonexistentException if station doesn't exist, or if song doesn't exist.

	 * */
	void removeFromStation(Song songToRemove, Station station) throws NonexistentException, NullPointerException, IllegalArgumentException;

	/**
	 * Sets a user's rating for a song, as a number of stars from 1 to 5. First,
	 * check to see if the song exists. If it does not, then throw a "NonexistentException."
	 * If songToRate == null or user == null, throw a "NullPointerException." If the user
	 * has not previously rated this song, proceed to assign the provided rating. If a rating
	 * already exists for this song by the user, override the existing rating with the new one.
	 * 
	 * @param theUser the user who is rating the song.
	 * @param songToRate the song to rate.
	 * @param stars the amount of stars the song gets.
	 * @throws NonexistentException if the song doesn't exist.
	 * @throws NullPointerException if songToRate == null.
	 * @throws IllegalArgumentException if stars is out of range.
	 */
	void rateSong(User theUser, Song songToRate, int stars);

	/**
	 * Clears a user's rating on a song. If this user has rated this song and
	 * the rating has not already been cleared, then the rating is cleared and
	 * the state will appear as if the rating was never made. If there is no
	 * such rating on record (either because this user has not rated this song,
	 * or because the rating has already been cleared), then this method will
	 * throw an IllegalArgumentException.
	 *
	 * @param theUser user whose rating should be cleared
	 * @param theSong song from which the user's rating should be cleared
	 * @throws IllegalArgumentException if the user does not currently have a
	 * rating on record for the song
	 * @throws NullPointerException if either the user or the song is null
	 */
	public void clearRating(User theUser, Song theSong)
		throws IllegalArgumentException, NullPointerException;

	/**
	 * Predicts the rating a user will assign to a song that they have not yet
	 * rated, as a number of stars from 1 to 5. First, check to see if the provided
	 * song to be rated exists in the first place. If it doesn't, throw a 
	 * "NonexistentException". If the user or song == null. throw a "NullPointerException".
	 * From then on, the method aims to analyze the user's past ratings, stations, playlists,
	 * or anything else related like maybe the song's popularity. Returns int of 1-5, the
	 * predicted rating.
	 * 
	 * @param theUser the user for whom the rating is being predicted.
	 * @param songToPredict the song's rating to predict.
	 * @throws NonexistentException if song does not exist.
	 * @throws NullPointerException if song == null, or user == null.
	 * @return predicted rating. (int)
	 */
	int predictRating(User theUser, Song songToPredict) throws NonexistentException, NullPointerException;

	/**
	 * Suggests a song for a user that they are predicted to like. The suggestion
	 * is based on the user's past ratings and preferences. Returns a song object
	 * that matches the user’s preferences.
	 *
	 * @param theUser the user for whom the suggestion is generated.
	 * @return a song that the user is likely to enjoy.
	 * @throws NullPointerException if theUser == null.
	 */
	Song suggestSong(User theUser) throws NullPointerException;
}