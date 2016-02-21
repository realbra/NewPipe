package org.schabi.newpipe.extractor.youtube;

import android.test.AndroidTestCase;

import org.schabi.newpipe.Downloader;
import org.schabi.newpipe.extractor.ExctractionException;
import org.schabi.newpipe.extractor.ParsingException;
import org.schabi.newpipe.extractor.VideoInfo;
import org.schabi.newpipe.extractor.services.youtube.YoutubeStreamExtractor;

import java.io.IOException;

public class YoutubeStreamExtractorRestrictedTest extends AndroidTestCase {
    private YoutubeStreamExtractor extractor;

    public void setUp() throws IOException, ExctractionException {
        extractor = new YoutubeStreamExtractor("https://www.youtube.com/watch?v=i6JTvzrpBy0",
                new Downloader());
    }

    public void testGetInvalidTimeStamp() throws ParsingException {
        assertTrue(Integer.toString(extractor.getTimeStamp()),
                extractor.getTimeStamp() <= 0);
    }

    public void testGetValidTimeStamp() throws ExctractionException, IOException {
        YoutubeStreamExtractor extractor =
                new YoutubeStreamExtractor("https://youtu.be/FmG385_uUys?t=174", new Downloader());
        assertTrue(Integer.toString(extractor.getTimeStamp()),
                extractor.getTimeStamp() == 174);
    }

    public void testGetAgeLimit() throws ParsingException {
        assertTrue(extractor.getAgeLimit() == 18);
    }

    public void testGetTitle() throws ParsingException {
        assertTrue(!extractor.getTitle().isEmpty());
    }

    public void testGetDescription() throws ParsingException {
        assertTrue(extractor.getDescription() != null);
    }

    public void testGetUploader() throws ParsingException {
        assertTrue(!extractor.getUploader().isEmpty());
    }

    public void testGetLength() throws ParsingException {
        assertTrue(extractor.getLength() > 0);
    }

    public void testGetViews() throws ParsingException {
        assertTrue(extractor.getLength() > 0);
    }

    public void testGetUploadDate() throws ParsingException {
        assertTrue(extractor.getUploadDate().length() > 0);
    }

    public void testGetThumbnailUrl() throws ParsingException {
        assertTrue(extractor.getThumbnailUrl(),
                extractor.getThumbnailUrl().contains("https://"));
    }

    public void testGetUploaderThumbnailUrl() throws ParsingException {
        assertTrue(extractor.getUploaderThumbnailUrl(),
                extractor.getUploaderThumbnailUrl().contains("https://"));
    }

    public void testGetAudioStreams() throws ParsingException {
        assertTrue(!extractor.getAudioStreams().isEmpty());
    }

    public void testGetVideoStreams() throws ParsingException {
        for(VideoInfo.VideoStream s : extractor.getVideoStreams()) {
            assertTrue(s.url,
                    s.url.contains("https://"));
            assertTrue(s.resolution.length() > 0);
            assertTrue(Integer.toString(s.format),
                    0 <= s.format && s.format <= 4);
        }
    }
}
