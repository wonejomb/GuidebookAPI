## **Before anything**

**This mod is a fork of the original GuideAPI by TehNut and Tombenpotter released under the MIT license.**

## **Some internal information**

1.- This mod uses a different mod id, this is "guidebookapi" instead of "guideapi"
2.- Uses differents packages
3.- "Remake" of the API

## Description

This mod allows the ease creation of guides for minecraft or other mods, with no hard dependency!.

The mod create the guides mostly in code instead of external files like other mods. E.g: Patchouly is mostly JSON based

## Developer Information
The original information from Guide-API can be found [here](http://guide-api.readthedocs.org/en/latest/)

The fork is very similar in how it works

## Setup
```gradle
repositories {
    maven {
        url "https://cursemaven.com"
        content {
            includeGroup "curse.maven"
        }
    }
}

// Link to cursemaven: https://www.cursemaven.com/
dependencies {    
    implementation 'curse.maven:guidebookapi-949923:<fileid>'
}
```

## Credits

Original mod: Tehnut and Tombenpotter
Fork: JoulsMB / WonejoMB

## License
MIT license, so if necessary you can create a fork.
Also feel free to include it in your modpack.