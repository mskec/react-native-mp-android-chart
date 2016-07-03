# React Native MPAndroidChart

`react-native-mp-android-chart` is react native wrapper of popular android charting library [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart).


## Table of contents
- [Setup](#setup)
- [Usage](#usage)
- [Example](#example-app)

## Setup
`npm i react-native-mp-android-chart --save`

**android/settings.gradle**
```
include ':reactNativeMPAndroidChart'
project(':reactNativeMPAndroidChart').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-mp-android-chart/android')
```

**android/app/build.gradle**
```
dependencies {
   ...
   compile project(':reactNativeMPAndroidChart')
}
```

**MainActivity.java**

On top where imports are:
```java
import com.github.reactNativeMPAndroidChart.MPAndroidChartPackage;
```

Add package in `getPackages` method:
```java
protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new MPAndroidChartPackage()             // <----- Add this
    );
}
```


## Usage
TODO

## Example app
Example Android application with source code and `apk` is available [here](https://github.com/mskec/react-native-mp-android-chart-example).
