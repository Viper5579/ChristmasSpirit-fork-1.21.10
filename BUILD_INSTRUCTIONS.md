# Christmas Spirit v1.0.15 - Build Instructions

## For Minecraft 1.21.1

This mod has been successfully updated from 1.16.4 to 1.21.1.

### Version Information
- **Mod Version:** 1.0.15
- **Minecraft Version:** 1.21.1 (compatible with 1.21.x series)
- **Forge Version:** 52.0.17
- **Java Version Required:** 21

### Prerequisites

#### Install Java 21
You **must** have Java 21 installed. Java 17 will not work.

**On macOS (using Homebrew):**
```bash
brew install --cask temurin21
```

**On macOS/Linux (using SDKMAN):**
```bash
sdk install java 21.0.1-tem
```

**Manual Download:**
- [Adoptium Temurin 21](https://adoptium.net/temurin/releases/?version=21)

Verify your Java version:
```bash
java -version
# Should show: openjdk version "21.x.x"
```

### Building the Mod

**Important:** Make sure you're on the correct branch!
```bash
git checkout claude/debug-arm-mac-build-019zgTs14y3YvMiPEa2FccNs
git pull origin claude/debug-arm-mac-build-019zgTs14y3YvMiPEa2FccNs
```

**Note:** This branch includes all the ARM Mac build fixes. Once merged, you'll use the main release branch.

#### Step 1: Set up LWJGL repository (Apple Silicon Macs only)
**CRITICAL FOR ARM MAC USERS:** If you're on an Apple Silicon (M-series) Mac, you MUST run this script BEFORE building:

```bash
chmod +x create-lwjgl-repo.sh
./create-lwjgl-repo.sh
```

This script:
- Downloads the ARM64 native libraries for LWJGL from Maven Central
- Saves them with the `natives-macos-patch` classifier that ForgeGradle expects
- Creates a local Maven repository in `.gradle-local-repo/`

**Why is this needed?** ForgeGradle 6.x requests LWJGL natives with classifier `natives-macos-patch`, but this classifier doesn't exist in any public Maven repository. The script downloads the correct `natives-macos-arm64` JARs and renames them so Gradle can find them.

**Without running this script, the build will fail with:**
```
Could not find lwjgl-*-3.3.3-natives-macos-patch.jar
```

#### Step 2: Build the mod
To build the mod JAR file, you need an internet connection for the first build to download dependencies.

**On Linux/Mac:**
```bash
chmod +x gradlew
./gradlew clean build
```

**On Windows:**
```cmd
gradlew.bat clean build
```

The build process will:
1. Download Gradle 8.10 (first time only)
2. Download Minecraft, Forge, and all dependencies
3. Compile the mod
4. Create the JAR file

The compiled JAR will be located in:
```
build/libs/ChristmasSpirit-1.0.15.jar
```

### Troubleshooting

#### Build fails with "Could not find net.minecraft:client:1.21.1"
**Symptoms:** Build fails during configuration with:
```
Could not find net.minecraft:client:1.21.1
Searched in the following locations:...
```

**Solution:** This is a repository configuration issue that has been fixed in the latest version. Make sure you have the latest changes:
```bash
git pull origin claude/debug-arm-mac-build-019zgTs14y3YvMiPEa2FccNs
./gradlew clean build --refresh-dependencies
```

If the issue persists, clear your Gradle cache:
```bash
rm -rf ~/.gradle/caches
./gradlew clean build
```

#### Apple Silicon (M-series) Macs - LWJGL natives-macos-patch Error
**Symptoms:** Build fails with errors like:
```
Could not find lwjgl-freetype-3.3.3-natives-macos-patch.jar
Searched in the following locations:
  https://repo.maven.apache.org/maven2/org/lwjgl/lwjgl-freetype/3.3.3/lwjgl-freetype-3.3.3-natives-macos-patch.jar
```

**Solution:** You MUST run the `create-lwjgl-repo.sh` script BEFORE building:
```bash
chmod +x create-lwjgl-repo.sh
./create-lwjgl-repo.sh
```

The script downloads ARM64 native libraries and saves them with the `natives-macos-patch` classifier that ForgeGradle expects.

**If you've already run the script but still see this error:**
1. Delete the `.gradle-local-repo` directory: `rm -rf .gradle-local-repo`
2. Run the script again: `./create-lwjgl-repo.sh`
3. Clear Gradle cache: `rm -rf ~/.gradle/caches`
4. Build again: `./gradlew clean build`

#### Java Version Error
If you see "Use Java 8" or Java version errors, make sure:
1. You're on the correct branch (claude/debug-arm-mac-build-019zgTs14y3YvMiPEa2FccNs)
2. Java 21 is installed and active
3. Run `java -version` to verify

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
