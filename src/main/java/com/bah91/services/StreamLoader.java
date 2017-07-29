package com.bah91.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.*;

/**
 * Created by bah91 on 29/07/17.
 */
@Service
public class StreamLoader {

    @Value("hashtag")
    private String hashtag;


    StreamLoader() {
        init();
    }

    StatusListener listener = new StatusListener() {
        @Override
        public void onStatus(Status status) {
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
            System.out.println("Got stall warning:" + warning);
        }

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






}
