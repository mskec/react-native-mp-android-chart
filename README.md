# React Native MPAndroidChart
This library is React Native wrapper of popular Android charting library [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart).

## Table of contents
- [Setup](#setup)
- [Usage](#usage)
- [Example](#example-application)

## Setup
Library can be easily installed using NPM:

`npm i react-native-mp-android-chart --save`

Additional setup is required because library is using native Android code.

**android/build.gradle**
```
allprojects {
    repositories {
        ...

        maven { url "https://jitpack.io" }    // used for MPAndroidChart
    }
}
```

**android/settings.gradle**
```
include ':reactNativeMPAndroidChart'
project(':reactNativeMPAndroidChart').projectDir = new File(
  rootProject.projectDir,
  '../node_modules/react-native-mp-android-chart/android'
)
```

**android/app/build.gradle**
```
dependencies {
    ...
    compile project(':reactNativeMPAndroidChart')
}
```
**MainApplication.java**

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
There are 8 supported charts with many configuration options.
Almost all configuration available in base MPAndroidChart library are available through this wrapper.
More details on available configuration can be found on their [wiki](https://github.com/PhilJay/MPAndroidChart/wiki).

Example of how charts are used and how to apply configuration can be found in example [Android application](#example-application).

Supported charts with examples:
- [Bar](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/BarChartScreen.js)
- [Bubble](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/BubbleChartScreen.js)
- [Candle stick](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/CandleStickChartScreen.js)
- [Line](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/LineChartScreen.js)
- [Discrete Line With Custom Marker Text](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/TimeSeriesLineChartScreen.js)
- [Pie](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/PieChartScreen.js)
- [Radar](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/RadarChartScreen.js)
- [Scatter](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/ScatterChartScreen.js)
- [Stacked bar](https://github.com/mskec/react-native-mp-android-chart-example/blob/master/app/StackedBarChartScreen.js)


### Example code
This is simple example of how `BarChart` is used.
```JavaScript
import {BarChart} from 'react-native-mp-android-chart';

class BarChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      data: {
        datasets: [{
          values: [{y:100}, {y:105}, {y:102}, {y:110}],
          label: 'Data set 1',
          config: {
            color: 'teal'
          }
        }, {
          values: [{y:110}, {y:100}, {y:105}, {y:108}],
          label: 'Data set 2',
          config: {
            color: 'orange'
          }
        }],
        xLabels: ['Q1', 'Q2', 'Q3', 'Q4']
      }
    };
  }

  render() {
    return (
      <View>
        <BarChart
          style={styles.chart}
          data={this.state.data}
          animation={{durationX: 2000}}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  chart: {
    height: 300,
    width: 300
  }
});
```

## Example application
Example Android application with source code and `apk` is available [here](https://github.com/mskec/react-native-mp-android-chart-example).

## License
The MIT License

Copyright (c) 2016 Martin Skec

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
