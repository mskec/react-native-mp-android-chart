import {
  PropTypes,
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'PieChart',
  propTypes: {
    ...ChartBase.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(PropTypes.number),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,

          sliceSpace: PropTypes.number,
          selectionShift: PropTypes.number
        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidPieChart', iface);
