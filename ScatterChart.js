import {
  PropTypes,
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'ScatterChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(PropTypes.number),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,
          ...ChartDataSetConfig.lineScatterCandleRadar,

          scatterShapeSize: PropTypes.number,
          scatterShape: PropTypes.oneOf(['SQUARE', 'CIRCLE', 'TRIANGLE', 'CROSS', 'X']),
          scatterShapeHoleColor: PropTypes.string,
          scatterShapeHoleRadius: PropTypes.number
        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidScatterChart', iface);
