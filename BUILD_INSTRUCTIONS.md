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
git checkout claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq
git pull origin claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq
```

#### Step 1: Set up LWJGL repository (Apple Silicon Macs only)
If you're on an Apple Silicon (M-series) Mac, run this script first:
```bash
chmod +x create-lwjgl-repo.sh
./create-lwjgl-repo.sh
```

This creates a local Maven repository that fixes LWJGL compatibility issues on ARM64 Macs.

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

#### Apple Silicon (M-series) Macs - LWJGL natives-macos-patch Error
**Symptoms:** Build fails with errors like:
```
Could not find lwjgl-freetype-3.3.3-natives-macos-patch.jar
```

**Solution:** This is fixed by running the `create-lwjgl-repo.sh` script BEFORE building:
```bash
./create-lwjgl-repo.sh
```

The script creates fake `natives-macos-patch` artifacts that redirect to the correct `natives-macos-arm64` versions. This workaround is necessary because ForgeGradle looks for a classifier that doesn't exist in Maven repositories.

#### Java Version Error
If you see "Use Java 8" or Java version errors, make sure:
1. You're on the correct branch (claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq)
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
