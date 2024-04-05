package core;

import java.io.File;
import javafx.scene.media.Media;


public class VideoLoader extends FileLoader {

    private File path;
    private int height;
    private int width;

    public VideoLoader() {
        super();
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        }

        public File getPath() {
            return path;
        }

        public void setPath(File path) {
            this.path = path;
        }


        public void loadImage() {
            loadFiles();
            Media video = new Media(path.toURI().toString());
            width = (int) video.getWidth();
            height = (int) video.getHeight();
        }
}