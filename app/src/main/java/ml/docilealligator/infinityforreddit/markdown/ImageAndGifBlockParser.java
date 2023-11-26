package ml.docilealligator.infinityforreddit.markdown;

import androidx.annotation.Nullable;

import org.commonmark.node.Block;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ml.docilealligator.infinityforreddit.MediaMetadata;

public class ImageAndGifBlockParser extends AbstractBlockParser {

    private ImageAndGifBlock imageAndGifBlock;

    ImageAndGifBlockParser(MediaMetadata mediaMetadata) {
        this.imageAndGifBlock = new ImageAndGifBlock(mediaMetadata);
    }

    @Override
    public Block getBlock() {
        return imageAndGifBlock;
    }

    @Override
    public BlockContinue tryContinue(ParserState parserState) {
        return null;
    }

    public static class Factory extends AbstractBlockParserFactory {
        private final Pattern redditPreviewPattern =  Pattern.compile("!\\[img]\\(https://preview.redd.it/\\w+.(jpg|png|jpeg)((\\?+[-a-zA-Z0-9()@:%_+.~#?&/=]*)|)\\)");
        private final Pattern iRedditPattern = Pattern.compile("!\\[img]\\(https://i.redd.it/\\w+.(jpg|png|jpeg|gif)\\)");
        private final Pattern gifPattern = Pattern.compile("!\\[gif]\\(giphy\\|\\w+(\\|downsized)?\\)");
        @Nullable
        private Map<String, MediaMetadata> mediaMetadataMap;
        private final int fromIndex = "![img](https://preview.redd.it/".length();
        private final int fromIndexIReddit = "![img](https://i.redd.it/".length();

        @Override
        public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
            if (mediaMetadataMap == null) {
                return BlockStart.none();
            }

            String line = state.getLine().toString();
            Matcher matcher = redditPreviewPattern.matcher(line);
            if (matcher.find()) {
                if (matcher.end() == line.length()) {
                    int endIndex = line.indexOf('.', fromIndex);
                    if (endIndex > 0) {
                        String id = line.substring(fromIndex, endIndex);
                        return mediaMetadataMap.containsKey(id) ? BlockStart.of(new ImageAndGifBlockParser(mediaMetadataMap.get(id))) : BlockStart.none();
                    }
                }
            }

            matcher = iRedditPattern.matcher(line);
            if (matcher.find()) {
                if (matcher.end() == line.length()) {
                    int endIndex = line.indexOf('.', fromIndexIReddit);
                    if (endIndex > 0) {
                        String id = line.substring(fromIndexIReddit, endIndex);
                        return mediaMetadataMap.containsKey(id) ? BlockStart.of(new ImageAndGifBlockParser(mediaMetadataMap.get(id))) : BlockStart.none();
                    }
                }
            }

            matcher = gifPattern.matcher(line);
            if (matcher.find()) {
                if (matcher.end() == line.length()) {
                    String id = line.substring("![gif](".length(), line.length() - 1);
                    return mediaMetadataMap.containsKey(id) ? BlockStart.of(new ImageAndGifBlockParser(mediaMetadataMap.get(id))) : BlockStart.none();
                }
            }
            return BlockStart.none();
        }

        public void setMediaMetadataMap(@Nullable Map<String, MediaMetadata> mediaMetadataMap) {
            this.mediaMetadataMap = mediaMetadataMap;
        }
    }
}
