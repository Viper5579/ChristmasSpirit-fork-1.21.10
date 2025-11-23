# Christmas Spirit v1.0.15 - Build Instructions

## For Minecraft 1.21.1

This mod has been successfully updated from 1.16.4 to 1.21.1.

### Version Information
- **Mod Version:** 1.0.15
- **Minecraft Version:** 1.21.1 (compatible with 1.21.x series)
- **Forge Version:** 52.0.17
- **Java Version Required:** 21

### Building the Mod

To build the mod JAR file, you need an internet connection for the first build to download dependencies.

#### On Linux/Mac:
```bash
chmod +x gradlew
./gradlew build
```

#### On Windows:
```cmd
gradlew.bat build
```

The compiled JAR will be located in:
```
build/libs/ChristmasSpirit-1.0.15.jar
```

### What's New in v1.0.15
- Updated to Minecraft 1.21.1
- Updated to Forge 52.0.17
- Updated to Java 21
- Migrated all APIs to 1.21.1 standards
- Updated JEI integration for 1.21.1

### Installation
1. Install Minecraft Forge 52.0.17 or later for Minecraft 1.21.1
2. Place the `ChristmasSpirit-1.0.15.jar` file in your `mods` folder
3. Launch Minecraft with the Forge profile

### Changes from 1.16.4
This version includes major updates to work with Minecraft 1.21.1:
- Completely refactored codebase for 1.21.1 APIs
- Updated creative tabs system
- Updated entity registration and rendering
- Updated block and item systems
- Modernized build system (ForgeGradle 6.x)
