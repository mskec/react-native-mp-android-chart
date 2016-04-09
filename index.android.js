
import { NativeModules } from 'react-native';

import LineChart from './LineChart';

const MPAndroidChartModule = NativeModules.MPAndroidChartModule;

module.exports = {
  MPAndroidChartModule,
  LineChart
}
