package com.jack.md.table.test.utils;

import android.net.Uri;
import android.text.TextUtils;

import io.noties.markwon.image.destination.ImageDestinationProcessor;
import io.noties.markwon.image.destination.ImageDestinationProcessorRelativeToAbsolute;

public class GithubImageDestinationProcessor extends ImageDestinationProcessor {

    private final ImageDestinationProcessorRelativeToAbsolute processor;

    public GithubImageDestinationProcessor() {
        this("noties", "Markwon", "master");
    }

    public GithubImageDestinationProcessor(String username, String repository, String branch) {
        this.processor = new ImageDestinationProcessorRelativeToAbsolute(
            "https://github.com/" + username + "/" + repository + "/raw/" + branch + "/"
        );
    }

    @Override
    public String process(String destination) {
        // process only images without scheme information
        Uri uri = Uri.parse(destination);
        if (TextUtils.isEmpty(uri.getScheme())) {
            return processor.process(destination);
        } else {
            return destination;
        }
    }
}