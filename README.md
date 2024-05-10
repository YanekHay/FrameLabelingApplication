# FrameLabelingApplication

FrameLabelingApplication (FLA): An application for labeling images and videos for various purposes, such as:
- Segmentation
- Object Detection
- Image Classification
- Point Tracking
- etc...

For the class draft see [FLA_Class_Draft](FLA_Class_Draft.pdf).

# Getting Started with JavaFX
See this Doc for getting started with JavaFX ([link](https://app.clickup.com/9018160729/v/dc/8crcdjt-418/8crcdjt-258))

# Our Planning
- [x] Create a basic JavaFX application
- [x] Create a basic UI
- [x] Implement the basic functionalities
- [x] Implement the basic functionalities for image labeling
- [x] Implement the basic functionalities for video labeling
- [ ] Implement the saving mechanism
- [ ] Implement item selection, select multiple items with selectible area
- [ ] Implement layer ordering
- [ ] Implement selection through multiple layers (for overlaying items)
----
-  For more Details join our [Clickup.](https://app.clickup.com/9018160729/v/li/901800999977)
-  For seeing the UML Diagrams visit to [drawio.](https://drive.google.com/file/d/1SmMuc4sI7TkPgL6_7K9Wg95gj9D7FZ1a/view?usp=sharing)
# References
- 
- [JavaFX Homepage](https://openjfx.io/)
- [JavaFX API Documentation](https://openjfx.io/javadoc/22/)
- [JavaFX CSS Reference Guide](https://openjfx.io/javadoc/22/javafx.graphics/javafx/scene/doc-files/cssref.html)

# Setup for Development
- Install JavaFX SDK
- Download JavaCV binaries, extrct and reference the libraries from here ([Source](https://sourceforge.net/projects/javacv.mirror/) | [Download Link](https://master.dl.sourceforge.net/project/javacv.mirror/1.5.10/javacv-platform-1.5.10-bin.zip?viasf=1))

If you are using Visual Studio Code, you can use the following `launch.json` configuration to run the application:
```json
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Main",
            "request": "launch",
            "mainClass": "Main",
            "projectName": "[When you press F5, this will be the name of the project that will be run.]",
            "vmArgs": "--module-path '${env:PATH_TO_FX}' --add-modules javafx.graphics,javafx.media,javafx.controls,javafx.fxml,org.bytedeco.javacv,org.bytedeco.javacpp,org.bytedeco.ffmpeg,org.bytedeco.videoinput,org.bytedeco.libfreenect,org.bytedeco.flycapture,org.bytedeco.libdc1394,org.bytedeco.leptonica,org.bytedeco.librealsense,org.bytedeco.tesseract,org.bytedeco.openblas,org.bytedeco.artoolkitplus,org.bytedeco.librealsense2,org.bytedeco.libfreenect2"
        }
    ]
}
```