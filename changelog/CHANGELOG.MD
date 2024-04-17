# 2.1.0:

This update has minimal changes for colors in the books. Now the GuidebookAPI proxy doesn't set a color if there is a custom texture in the guide.

In configurations now you can change like the book colors the color of the pages.

# 2.0.2:

Update item textures and logo.

# 2.0.1:

This update only remove the ExampleBook from the Jar

# 2.0.0:

The GuidebookAPI 2.0.0 just release! This update has some breaking changes for the old 1.0.0 API.

This changelog will have all the changes of the 2.0.0

## GuidebookApi

GuidebookAPI have a new ModId, now it have for mod id "gapi" instead of "guidebookapi".

The "@Guidebook" decorator now has for name "@GuidebookAPI"

## The NBT

The NBT of the mod has been remade, old versions of GuidebookAPI uses the **"PersistentData"** tag, now it uses **"guidebookapi"** tag in players and uses different tag for the initial books,
old version have a type of array tag of "hasInitial-{bookId}", now it have a tag "initial" inside the "guidebookapi" tag and register all the intiial gived books has "`'{bookNamespace}.{bookId}': 1b` (or 0b if its false).

## BookBuilder

BookBuilder have new functions and rename of others, this version has a new section called "information".

The information of the books can contain this info:
```
1.- ModName
2.- Title
3.- Credits
4.- Description
```

The old functions for textures and models that were in the "IGuidebook" interface are now found in the BookBuilder

## The Screens

The search screen has been removed and the information screen has been put in its place.

## Configuration

The configuration system of the mod has an update! The update made the configuration rewrite (or is supposed to do(report if this have an error please)) and new system for register loaded configurations, now without have to make Parse ( in case of developers or the mod ) for every one configuration value

## Textures

All the textures have been remade!