import {
  requireNativeComponent,
  View
} from 'react-native';

const iface = {
  name: 'LineChart',
  propTypes: {
    ...View.propTypes
  }
};

export default requireNativeComponent('MPAndroidLineChart', iface);
