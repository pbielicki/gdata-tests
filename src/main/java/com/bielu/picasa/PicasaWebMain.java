package com.bielu.picasa;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.UserFeed;

public class PicasaWebMain {

  public static void main(String[] args) throws Exception {
    PicasawebService myService = new PicasawebService("exampleCo-exampleApp-1");

    URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/pbielicki");

    UserFeed myUserFeed = myService.getFeed(feedUrl, UserFeed.class);

    /*@SuppressWarnings("rawtypes")
    Iterator<GphotoEntry> it = myUserFeed.getEntries().iterator();
    it.next();
    it.next();
    feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/pbielicki/albumid/" + it.next().getGphotoId());

    AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);

    for (GphotoEntry<?> photo : feed.getEntries()) {
      if (photo.getExtension(com.google.gdata.data.photos.GphotoVideoStatus.class) != null) {
        for (MediaContent content : ((MediaGroup) photo.getExtension(MediaGroup.class)).getContents()) {
          if ("video".equals(content.getMedium())) {
            System.out.println(content.getUrl());
          }
        }
      } else {
        System.out.println(((com.google.gdata.data.MediaContent) photo.getContent()).getUri());
      }
    }*/
    
    Set<String> albums = new HashSet<String>();
    for (GphotoEntry<?> entry : myUserFeed.getEntries()) {
      String title = entry.getHtmlLink().getHref();
      if (albums.add(title) == false) {
        System.err.println(title + " duplicate");
      } else {
        System.out.println(title);
      }
    }
  }
}
