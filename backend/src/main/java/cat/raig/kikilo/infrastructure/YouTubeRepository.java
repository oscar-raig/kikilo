package cat.raig.kikilo.infrastructure;

import cat.raig.kikilo.entities.Video;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class YouTubeRepository implements  cat.raig.kikilo.repository.YouTubeRepository{

  private static final String PROPERTIES_FILENAME = "youtube.properties";
  private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
  private  YouTube youtube;
  private Properties properties;

  public YouTubeRepository(HttpTransport transport, JsonFactory jsonFactory,
                           HttpRequestInitializer requestInitializer) {
   InitializeYouTube(transport, jsonFactory, requestInitializer);
  }


  @Override
  public List<Video> findByKeyWords(String keyWords)  {
    try {
      YouTube.Search.List search = youtube.search().list("id,snippet");
      String apiKey = properties.getProperty("youtube.apikey");
      search.setKey(apiKey);
      search.setQ(keyWords);
      search.setType("video");

      // To increase efficiency, only retrieve the fields that the
      // application uses.
      search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
      search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);


      SearchListResponse searchResponse = search.execute();
      List<SearchResult> searchResultList = searchResponse.getItems();
      if (searchResultList != null) {
        prettyPrint(searchResultList.iterator(), keyWords);
        return resulstToDto(searchResultList.iterator(),keyWords);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  private void InitializeYouTube(HttpTransport transport, JsonFactory jsonFactory,
                                 HttpRequestInitializer requestInitializer) {

    properties = new Properties();
    try {
      InputStream in = YouTubeRepository.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
      properties.load(in);

    } catch (IOException e) {
      System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
        + " : " + e.getMessage());
      System.exit(1);
    }

    youtube = new YouTube.Builder(transport, jsonFactory, requestInitializer).setApplicationName("youtube-cmdline-search-sample").build();

  }

  private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

    System.out.println("\n=============================================================");
    System.out.println(
      "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
    System.out.println("=============================================================\n");

    if (!iteratorSearchResults.hasNext()) {
      System.out.println(" There aren't any results for your query.");
    }

    while (iteratorSearchResults.hasNext()) {

      SearchResult singleVideo = iteratorSearchResults.next();
      ResourceId rId = singleVideo.getId();

      // Confirm that the result represents a video. Otherwise, the
      // item will not contain a video ID.
      if (rId.getKind().equals("youtube#video")) {
        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

        System.out.println(" Video Id" + rId.getVideoId());
        System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
        System.out.println(" Thumbnail: " + thumbnail.getUrl());
        System.out.println("\n-------------------------------------------------------------\n");
      }
    }
  }

  private  List<Video> resulstToDto(Iterator<SearchResult> iteratorSearchResults, String query) {

    List<Video> videos = new ArrayList<>();

    System.out.println("\n=============================================================");
    System.out.println(
      "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
    System.out.println("=============================================================\n");

    if (!iteratorSearchResults.hasNext()) {
      System.out.println(" There aren't any results for your query.");
    }

    while (iteratorSearchResults.hasNext()) {

      SearchResult singleVideo = iteratorSearchResults.next();
      ResourceId rId = singleVideo.getId();

      // Confirm that the result represents a video. Otherwise, the
      // item will not contain a video ID.
      if (rId.getKind().equals("youtube#video")) {
        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

        System.out.println(" Video Id" + rId.getVideoId());
        System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
        System.out.println(" Thumbnail: " + thumbnail.getUrl());
        System.out.println("\n-------------------------------------------------------------\n");
        Video video = new Video(singleVideo.getSnippet().getTitle(),rId.getVideoId());
        videos.add(video);
      }
    }
    return videos;
  }
}
