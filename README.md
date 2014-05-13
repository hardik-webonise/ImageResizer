ImageResizer
============

ImageResizer is a java project which creates different sized drawables for your android project.

The purpose of this project is to create image generator which allows us to quickly and easily generate the icons or drwables from existing base resolution's images.

##Input

You need to put all your drawables (All images should have been taken from the psd which is having width 1080 px, Algorithm is working on this assumption) into res folder.


##Output

You will be getting 3 folders as an output

* drawable-xhdpi
* drawable-hdpi
* drawable-mdpi


You can add one more output folder drawable-ldpi too if you want. I have skipped that in project as many of the developer's has stop providing support for low resolution devices now.
