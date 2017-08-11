package com.bah91.services;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.*;
import twitter4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.UUID;

/**
 * Created by bah91 on 29/07/17.
 */
@Service
public class StreamLoader {

    private String hashtag = "#BK2018";

    @Autowired
    FileService fileService;

    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

    StreamLoader() {
        init();
    }

    StatusListener listener = new StatusListener() {
        @Override
        public void onStatus(Status status) {
            log.debug("@" + status.getUser().getScreenName() + " - " + status.getText());
            processMediaEntities(status);
        }
        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
        @Override
        public void onScrubGeo(long userId, long upToStatusId) {}
        @Override
        public void onStallWarning(StallWarning warning) {};
        @Override
        public void onException(Exception ex) {
            ex.printStackTrace();
        }
    };

    private void init() {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery(hashtag));
    }

    private void processMediaEntities(Status status) {
        for (MediaEntity entity: status.getMediaEntities()) {
            fileService.processImage(entity.getMediaURL());
        }
    }



}
