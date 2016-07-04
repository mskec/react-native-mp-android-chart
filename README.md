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

## License
The MIT License

Copyright (c) 2016 Martin Skec

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
