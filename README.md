# MineLibs

<img src="https://img.shields.io/badge/Made%20with-java-orange?style=for-the-badge&logo=Oracle" alt="Made with Java">

MineLibs is not for everyone. The library contains many functions that are exactly adapted to my needs and my code style. However, I would be very happy if you would take a look at it. Maybe you are similar to me and can find a use for my work.

Many functions that I don't cover can be found in <a href="https://git.rytrox.de/shared/Spicy">Spicy</a>. MineLibs should best serve as an extension to <a href="https://git.rytrox.de/shared/Spicy">Spicy</a>.

<hr>

## Installation

#### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Asedem</groupId>
    <artifactId>MineLibs</artifactId>
    <version>f72e988397</version>
</dependency>
```

#### Gradle
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Asedem:MineLibs:f72e988397'
}
```

<hr>

## Features

- Custom ChatColor class
  - translate
  - gradiant
  - nonce
- BukkitMath
- DynamicPermissions
- PlayerUtils
- AdvancedYamlConfiguration
- TextComponentBuilder
- InventoryBuilder
- Map
  - CustomMap
  - CustomScaledMap
- BungeeMessageChannel
- ProxyUtils
- Updater
- ReverseMap
